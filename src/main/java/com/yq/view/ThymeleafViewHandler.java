package com.yq.view;

import com.yq.entity.Workorder;
import com.yq.service.WorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Controller
public class ThymeleafViewHandler {

    @Autowired
    private WorkorderService workorderService;

    @RequestMapping(value = "/index.html")
    public String index(Model model) {
        List<Workorder> list = this.workorderService.query(null, null);
        model.addAttribute("workorders", list);
        return "index";
    }
}
