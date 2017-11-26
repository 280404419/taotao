package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.OrderService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 订单处理service
 * Created by ASUS on 2017/11/26.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;
    @Override
    public String createOrder(OrderInfo orderInfo) {
        //把ordreInfo转换成json
        String json = JsonUtils.objectToJson(orderInfo);
        //提交订单数据
        String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
        //取订单号
        String orderId = taotaoResult.getData().toString();
        return orderId;
    }
}
