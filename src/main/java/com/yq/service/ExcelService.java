package com.yq.service;

import com.yq.util.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * created by wb-yq264139 on 2017/11/14
 */
@Service
public class ExcelService {

    @Autowired
    private DateUtils dateUtils;

    /**
     * 获得标题的单元格样式
     *
     * @param wb
     * @return
     */
    public CellStyle getTitleCellStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.index);//设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置填充
        cellStyle.setWrapText(false);//自动换行
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        return cellStyle;
    }

    /**
     * 设置标题的字体样式
     *
     * @param wb
     * @return
     */
    public void setTitleFont(Workbook wb, CellStyle cellStyle) {
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("楷体");
        font.setItalic(false);//斜体
        font.setStrikeout(false);//中划线
        font.setBold(true);//粗体
        cellStyle.setFont(font);
    }

    /**
     * 设置标题的列宽
     *
     * @param sheet
     */
    public void setTitleColumnWidth(Sheet sheet) {
        sheet.setColumnWidth(0, 20 * 150);
        sheet.setColumnWidth(1, 20 * 200);
        sheet.setColumnWidth(2, 20 * 600);
        sheet.setColumnWidth(3, 20 * 300);
        sheet.setColumnWidth(4, 20 * 300);
    }


    /**
     * 获得正文的单元格样式
     *
     * @param wb
     * @return
     */
    public CellStyle getContentCellStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.index);//设置背景色
//        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置填充
        cellStyle.setWrapText(true);//自动换行
        return cellStyle;
    }

    /**
     * 设置正文的字体样式
     *
     * @param wb
     * @return
     */
    public void setContentFont(Workbook wb, CellStyle cellStyle) {
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        font.setItalic(false);//斜体
        font.setStrikeout(false);//中划线
        font.setBold(false);//粗体
        cellStyle.setFont(font);
    }

    public void write(Workbook wb, String path) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(path);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Workbook wb, OutputStream out) {
        try {
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setValue(Cell cell, CellStyle cellStyle, String value) {
        cell.setCellStyle(cellStyle);
        cell.setCellValue(new HSSFRichTextString(value));
    }

    public void setValue(Cell cell, CellStyle cellStyle, Date value) {
        String format = this.dateUtils.format(value, "yyyy-MM-dd HH:mm:ss");
        this.setValue(cell, cellStyle, format);
    }

    public Workbook createWorkbook() {
        return new HSSFWorkbook();
    }

    public Sheet createSheet(Workbook wb, String sheetName) {
        return wb.createSheet(WorkbookUtil.createSafeSheetName(sheetName));
    }

    public Row createRow(Sheet sheet, int row) {
        return sheet.createRow(row);
    }

    public Cell createCell(Row row, int cell) {
        return row.createCell(cell);
    }
}
