package com.taotao.search.controller;

import com.taotao.common.pojo.ExceptionUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ASUS on 2017/11/14.
 * 导入商品数据controller
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("importall")
    @ResponseBody
    public TaotaoResult importAll(){
        try {
           TaotaoResult result = itemService.importItems();
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
