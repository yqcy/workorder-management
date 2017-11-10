package com.yq.controller;

import com.yq.service.WorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Controller
@RequestMapping(value = "workorder")
public class WorkorderController {

    @Autowired
    private WorkorderService workorderService;
    

}
