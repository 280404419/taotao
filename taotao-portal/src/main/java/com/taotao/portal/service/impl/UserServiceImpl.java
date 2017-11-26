package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理service
 * Created by ASUS on 2017/11/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;
    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            //从cookie中取token
            String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
            //判断token是否有值
            if(StringUtils.isBlank(token)){
                return null;
            }
            //调用sso的服务查询用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
            //需要把json转换成java对象
            TaotaoResult result = TaotaoResult.format(json);
            if(result.getStatus()!=200){
                return null;
            }
            //正常返回，取用户对象
            result = TaotaoResult.formatToList(json, TbUser.class);
            TbUser user = (TbUser)result.getData();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
}
