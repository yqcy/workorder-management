package com.yq.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Controller
public class ThymeleafViewHandler {

    @RequestMapping(value = "/index.html")
    public String index() {
        return "index";
    }
}
