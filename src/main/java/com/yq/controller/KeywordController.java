package com.yq.controller;

import com.yq.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@RestController
@RequestMapping(value = "keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;


}
