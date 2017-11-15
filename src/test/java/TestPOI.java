import com.yq.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * created by wb-yq264139 on 2017/11/14
 */
public class TestPOI {

    private String filePath = "C:\\Users\\wb-yq264139\\Desktop\\TestPOI.xls";

    @Test
    public void testWrite() throws Exception {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        String sheetName = WorkbookUtil.createSafeSheetName("吊销");
        Sheet sheet = wb.createSheet(sheetName);
        //设置列宽
        sheet.setColumnWidth(0, 20 * 150);
        sheet.setColumnWidth(1, 20 * 100);
        sheet.setColumnWidth(2, 20 * 300);
        sheet.setColumnWidth(3, 20 * 150);
        //创建row
        Row row = sheet.createRow((short) 0);//row从下标0开始
        //创建标题的cellStyle
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.index);//设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置填充
        cellStyle.setWrapText(false);//自动换行
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        //创建四周表格的cellStyle
        CellStyle tableStyle = wb.createCellStyle();
        tableStyle.setBorderLeft(BorderStyle.MEDIUM);
        tableStyle.setBorderRight(BorderStyle.MEDIUM);
        tableStyle.setBorderTop(BorderStyle.MEDIUM);
        tableStyle.setBorderBottom(BorderStyle.MEDIUM);
        //创建标题字体
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("楷体");
        font.setItalic(false);//斜体
        font.setStrikeout(false);//中划线
        font.setBold(true);//粗体
        cellStyle.setFont(font);
        // cell从下标0开始
        Cell cell1 = row.createCell(0);//工单号
        Cell cell2 = row.createCell(1);//类型
        Cell cell3 = row.createCell(2);//关键词
        Cell cell4 = row.createCell(3);//创建时间
        cell1.setCellValue(new HSSFRichTextString("工单号"));
        cell1.setCellStyle(cellStyle);
        cell2.setCellValue(new HSSFRichTextString("类型"));
        cell2.setCellStyle(cellStyle);
        cell3.setCellValue(new HSSFRichTextString("关键词"));
        cell3.setCellStyle(cellStyle);
        cell4.setCellValue(new HSSFRichTextString("创建时间"));
        cell4.setCellStyle(cellStyle);
        //写入文件
        FileOutputStream fileOut = new FileOutputStream(filePath);
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void testExcelService() {
        ExcelService service = new ExcelService();
        Workbook wb = service.createWorkbook();
        Sheet sheet = service.createSheet(wb, "咨询");
        Row row = service.createRow(sheet, 0);
        Cell cell1 = service.createCell(row, 0);
        Cell cell2 = service.createCell(row, 1);
        CellStyle titleCellStyle = service.getTitleCellStyle(wb);
        service.setTitleColumnWidth(sheet);
        service.setTitleFont(wb, titleCellStyle);
        cell1.setCellValue("工单号");
        cell1.setCellStyle(titleCellStyle);
        cell2.setCellValue("类型");
        cell2.setCellStyle(titleCellStyle);
        Row row1 = service.createRow(sheet, 1);
        Cell cell3 = service.createCell(row1, 0);
        Cell cell4 = service.createCell(row1, 1);
        CellStyle contentCellStyle = service.getContentCellStyle(wb);
        service.setContentFont(wb, contentCellStyle);
        cell3.setCellValue("number1");
        cell3.setCellStyle(contentCellStyle);
        cell4.setCellValue("退款");
        cell4.setCellStyle(contentCellStyle);
        service.write(wb, filePath);
    }
}
