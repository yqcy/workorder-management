package com.yq.controller;

import com.yq.entity.Keyword;
import com.yq.entity.Workorder;
import com.yq.service.ExcelService;
import com.yq.service.WorkorderService;
import com.yq.util.DateUtils;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Api(value = "API - WorkorderController", description = "文档接口")
@RestController
@RequestMapping(value = "/workorder")
public class WorkorderController {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private WorkorderService workorderService;

    @Autowired
    private ExcelService excelService;

    @ApiOperation(value = "添加新的处理工单", notes = "支持POST方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "工单号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "category", value = "类别的主键", required = false, dataType = "Long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "keywords", value = "关键词的主键集合", required = false, dataType = "Long", paramType = "query", defaultValue = "1")
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
    public Object addWorkorder(@RequestParam(value = "number") String number,
                               @RequestParam(value = "category", required = false) Long categoryId,
                               @RequestParam(value = "keywords", required = false) List<Long> keywords) {
        Workorder workorder = new Workorder(number, categoryId);
        return this.workorderService.save(workorder, keywords);
    }

    @ApiOperation(value = "查询某个工单", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "number", value = "工单号", required = false, dataType = "String", paramType = "query")
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
    public Object queryWorkorder(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "number", required = false) String number) {
        Workorder keyword = new Workorder(id, number);
        return this.workorderService.query(keyword);
    }

    @ApiOperation(value = "查询多个工单", notes = "支持GET方式", response = String.class)
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
    public Object queryWorkorders(@RequestParam(value = "page", required = false) Integer pageNum,
                                  @RequestParam(value = "size", required = false) Integer pageSize) {
        return this.workorderService.query(pageNum, pageSize);
    }

    @ApiOperation(value = "删除某个工单", notes = "支持POST方式", response = String.class)
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
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Object removeWorkorder(@RequestParam(value = "id") Long id) {
        this.workorderService.remove(id);
        return "success";
    }

    @ApiOperation(value = "展示饼状图", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/highcharts", method = RequestMethod.GET)
    public Object highcharts() {
        Date date = this.dateUtils.currentTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        String str = sb.append(year).append("-").append(month).append("-").append(day - 7).toString();
        List<Workorder> workorders = this.workorderService.query(this.dateUtils.parse(str, "yyyy-MM-dd"), date);
        List<List> result = new ArrayList();
        Map<String, Integer> map = new HashMap();
        for (Workorder workorder : workorders) {
            String categoryName = workorder.getCategory().getName().toLowerCase();
            Integer count = map.get(categoryName);
            if (count == null) {
                map.put(categoryName, 1);
            } else {
                map.put(categoryName, count + 1);
            }
        }
        int size = workorders.size();
        for (String s : map.keySet()) {
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(s);
            list.add(map.get(s) * 100 / size);
            result.add(list);
        }
        return result;
    }

    @ApiOperation(value = "下载Excel文档", notes = "支持GET方式", response = String.class)
    @ApiImplicitParams({})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 403, message = "服务器资源不可用，或无权访问"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源，或文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download() {
        Date date = this.dateUtils.currentTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        String str = sb.append(year).append("-").append(month).append("-").append(day - 7).toString();
        List<Workorder> workorders = this.workorderService.query(this.dateUtils.parse(str, "yyyy-MM-dd"), date);
        Workbook wb = this.excelService.createWorkbook();
        Sheet sheet = this.excelService.createSheet(wb, "工单上周处理情况");
        this.excelService.setTitleColumnWidth(sheet);
        Row titleRow = this.excelService.createRow(sheet, 0);
        Cell cell1 = this.excelService.createCell(titleRow, 0);
        Cell cell2 = this.excelService.createCell(titleRow, 1);
        Cell cell3 = this.excelService.createCell(titleRow, 2);
        Cell cell4 = this.excelService.createCell(titleRow, 3);
        Cell cell5 = this.excelService.createCell(titleRow, 4);
        CellStyle titleCellStyle = this.excelService.getTitleCellStyle(wb);
        this.excelService.setTitleFont(wb, titleCellStyle);
        this.excelService.setValue(cell1, titleCellStyle, "工单号");
        this.excelService.setValue(cell2, titleCellStyle, "工单类型");
        this.excelService.setValue(cell3, titleCellStyle, "工单关键字");
        this.excelService.setValue(cell4, titleCellStyle, "工单创建时间");
        this.excelService.setValue(cell5, titleCellStyle, "工单更新时间");
        CellStyle contentCellStyle = this.excelService.getContentCellStyle(wb);
        this.excelService.setContentFont(wb, contentCellStyle);
        for (int i = 0; i < workorders.size(); i++) {
            Workorder workorder = workorders.get(i);
            Row row = this.excelService.createRow(sheet, i + 1);
            Cell c1 = this.excelService.createCell(row, 0);
            Cell c2 = this.excelService.createCell(row, 1);
            Cell c3 = this.excelService.createCell(row, 2);
            Cell c4 = this.excelService.createCell(row, 3);
            Cell c5 = this.excelService.createCell(row, 4);
            this.excelService.setValue(c1, contentCellStyle, workorder.getNumber());
            this.excelService.setValue(c2, contentCellStyle, workorder.getCategory().getName());
            //转成name数组
            List<Keyword> keywords = workorder.getKeywords();
            StringBuilder builder = new StringBuilder();
            if (keywords == null || keywords.size() == 0) {
                builder.append("无");
            } else {
                for (Keyword keyword : keywords) {
                    builder.append(keyword.getName()).append(",");
                }
                builder.deleteCharAt(builder.length() - 1);
            }
            this.excelService.setValue(c3, contentCellStyle, builder.toString());
            this.excelService.setValue(c4, contentCellStyle, workorder.getCreateTime());
            this.excelService.setValue(c5, contentCellStyle, workorder.getUpdateTime());
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.excelService.write(wb, outputStream);
        byte[] bytes = outputStream.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attchement;filename=" + "工单处理表.xls");//TODO 无法显示中文
        return new ResponseEntity(bytes, httpHeaders, HttpStatus.OK);
    }

}
