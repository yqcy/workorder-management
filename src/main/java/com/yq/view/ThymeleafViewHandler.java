package com.yq.view;

import com.yq.entity.Category;
import com.yq.entity.Keyword;
import com.yq.entity.Workorder;
import com.yq.service.CategoryService;
import com.yq.service.KeywordService;
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
    private CategoryService categoryService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private WorkorderService workorderService;

    @RequestMapping(value = "/index.html")
    public String index(Model model) {
        List<Workorder> list = this.workorderService.query(null, null);
        model.addAttribute("workorders", list);
        return "index";
    }

    @RequestMapping(value = "/workorder_add.html")
    public String workorderAdd(Model model) {
        List<Category> categories = this.categoryService.query(null, null);
        List<Keyword> keywords = this.keywordService.query(null, null);
        model.addAttribute("categories", categories);
        model.addAttribute("keywords", keywords);
        return "workorder_add";
    }
}
