package com.yq.controller;

import com.yq.entity.Keyword;
import com.yq.service.KeywordService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Api(value = "API - KeywordController", description = "文档接口")
@RestController
@RequestMapping(value = "keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @ApiOperation(value = "添加关键词", notes = "支持PUT方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "关键词名称", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Object addKeyword(@RequestParam(value = "name") String name) {
        Keyword keyword = new Keyword(name);
        return this.keywordService.save(keyword);
    }

    @ApiOperation(value = "查询单个关键词", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "关键词名称", required = false, dataType = "String", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object queryKeyword(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "name", required = false) String name) {
        Keyword keyword = new Keyword(id, name);
        return this.keywordService.query(keyword);
    }

    @ApiOperation(value = "查询多个关键词", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页号", required = false, dataType = "Int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页的条数", required = false, dataType = "Int", paramType = "query", defaultValue = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object queryKeywords(@RequestParam(value = "page", required = false) Integer pageNum,
                                @RequestParam(value = "size", required = false) Integer pageSize) {
        return this.keywordService.query(pageNum, pageSize);
    }

    @ApiOperation(value = "删除某个关键词", notes = "支持DELETE方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Object removeKeyword(@RequestParam(value = "id") Long id) {
        this.keywordService.remove(id);
        return "success";
    }
}
