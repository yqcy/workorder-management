package com.yq.controller;

import com.yq.entity.Category;
import com.yq.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by YQ on 2017-11-08
 */
@Api(value = "API - CategoryController", description = "文档接口")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "添加新的工单的处理类型", notes = "支持POST方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "类型名称", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestParam(value = "name") String name) {
        Category category = new Category(name);
        Category result = this.categoryService.save(category);
        return result;
    }

    @ApiOperation(value = "查询所有工单的处理类型", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页号", required = false, dataType = "Int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示的条数", required = false, dataType = "Int", paramType = "query", defaultValue = "10")
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
    public Object queryCategorys(@RequestParam(value = "page", required = false) Integer pageNum,
                                 @RequestParam(value = "size", required = false) Integer pageSize) {
        List<Category> result = this.categoryService.query(pageNum, pageSize);
        return result;
    }

    @ApiOperation(value = "查询某个工单的处理类型", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类型的主键", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "类型的名称", required = false, dataType = "String", paramType = "query")
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
    public Object queryCategory(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "name", required = false) String name) {
        Category category = new Category(id, name);
        Category result = this.categoryService.query(category);
        return result;
    }

    @ApiOperation(value = "删除某个工单的处理类型", notes = "支持POST方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类型的主键", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Object deleteCategory(@RequestParam(value = "id") Long id) {
        this.categoryService.remove(id);
        return "success";
    }
}
