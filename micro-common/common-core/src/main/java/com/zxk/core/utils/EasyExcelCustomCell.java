package com.zxk.core.utils;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 自定义表头拦截器
 */
public class EasyExcelCustomCell implements SheetWriteHandler {
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        //设置标题,设置首行表头样式
        Row row = sheet.createRow(0);
        // 设置行高
        row.setHeight((short) 900);
        //cell 特殊处理
        Cell cell = row.createCell(0);
        cell.setCellValue("cell 内容");
        // 设置水平、垂直居中
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置字体样式，bold加粗、fontHeight设置字体大小
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 500);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        // CellRangeAddress(0, 0, 0, 10) 用于合并表格，其中四个参数含义起始行号、终止行号、起始列号、终止列号
        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 10));
        //其他row自定义
    }
}