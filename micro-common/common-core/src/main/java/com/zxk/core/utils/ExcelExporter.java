package com.zxk.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class ExcelExporter {
    private int rowFlag;

    private String[] headerNames;
    private SXSSFWorkbook workBook;
    private SXSSFSheet sheet;

    /**
     *
     * @param headerNames
     *            表头
     * @param sheetName
     *            sheet的名称
     */
    public ExcelExporter(String[] headerNames, String sheetName) {
        this.headerNames = headerNames;
        // 处理07版本，但是适用于大数据量，导出之后数据不会占用内存
        workBook = new SXSSFWorkbook();
        // 创建一个工作表sheet
        sheet = workBook.createSheet(sheetName);
        initHeader();
    }

    /**
     * 初始化表头信息
     */
    private void initHeader() {
        //自动调整列宽
        sheet.trackAllColumnsForAutoSizing();
        rowFlag = 0;
        // 创建第一行
        Row row = sheet.createRow(0);
        Cell cell = null;
        // 创建表头
        for (int i = 0; i < headerNames.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerNames[i]);
            setCellStyle(cell);
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 设置单元格样式
     *
     * @param cell
     *            单元格
     */
    public void setCellStyle(Cell cell) {
        // 设置样式
        CellStyle cellStyle = workBook.createCellStyle();
        // 设置字体居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置字体
        Font font = workBook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 13);

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 创建行内容(每一行的数据装在list中)
     *
     * @param datas
     *            每一行的数据
     * @param rowIndex
     *            行号(从1开始)
     */
    public void createTableRow(List<String> datas, int rowIndex) {
        // 创建第i行
        SXSSFRow row = sheet.createRow(rowIndex);
        SXSSFCell cell = null;
        // 写入数据
        for (int index = 0, length = datas.size(); index < length; index++) {
            // 参数代表第几列
            cell = row.createCell(index);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(datas.get(index));
            setCellStyle(cell);
        }
    }

    /**
     *
     * @param datas
     *            数据,每一个map都是一行
     * @param keys
     *            key[i]代表从map中获取keys[i]的值作为第i列的值,如果传的是null默认取表头
     */
    public void createTableRows(List<Map<String, Object>> datas, String[] keys) {
        //sheet.trackAllColumnsForAutoSizing();
        for (int i = 0, length_1 = datas.size(); i < length_1; i++) {
            if (ArrayUtils.isEmpty(keys)) {
                keys = headerNames;
            }
            // 创建行(从第二行开始)
            Map<String, Object> data = datas.get(i);
            SXSSFRow row = sheet.createRow(++rowFlag);
            for (int j = 0, length_2 = keys.length; j < length_2; j++) {
                //sheet.autoSizeColumn(j);
                // 单元格获取map中的key
                String key = keys[j];
                String value = MapUtils.getString(data, key, "");
                SXSSFCell cell = row.createCell(j);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(value);
            }

        }
    }

    /**
     *
     * @param datas
     *   数据,每一个map都是一行
     */
    public void createTableRows(List<Map<String, Object>> datas) {
        for (int i = 0; i < datas.size(); i++) {
            // 创建行(从第二行开始)
            Map<String, Object> dataMap = datas.get(i);
            SXSSFRow row = sheet.createRow(++rowFlag);
            int j=0;
            for(String key : dataMap.keySet()) {
                String value = String.valueOf(dataMap.get(key));
                SXSSFCell cell = row.createCell(j);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(value);
                j++;
            }
        }
    }

    /**
     * 根据表头自动调整列宽度
     */
    public void autoAllSizeColumn() {
        // 如果是SXSSFSheet，需要调用trackAllColumnsForAutoSizing方法一次
        if (sheet instanceof SXSSFSheet) {
            SXSSFSheet tmpSheet =  sheet;
            tmpSheet.trackAllColumnsForAutoSizing();
        }
        for (int i = 0, length = headerNames.length; i < length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 将数据写出到excel中  (自动调整列宽)
     *
     * @param outputStream
     */
    public void exportExcel(OutputStream outputStream) {
        // 导出之前先自动设置列宽
        this.autoAllSizeColumn();
        try {
            workBook.write(outputStream);
        } catch (IOException e) {
            log.error(" exportExcel error", e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 将数据写出到excel中  (固定列宽)
     *
     * @param outputStream
     */
    public void exportExcel2(OutputStream outputStream) {
        // 导出之前先设置列宽
        sheet.setDefaultColumnWidth(22);
        try {
            workBook.write(outputStream);
        } catch (IOException e) {
            log.error(" exportExcel error", e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 合并单元格(起始行列都包括在里面)
     *
     * @param startRow
     *            起始行
     * @param endRow
     *            结束行
     * @param startCol
     *            起始列
     * @param endCol
     *            结束列
     */
    public void mergeCell(int startRow, int endRow, int startCol, int endCol) {
        CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
        sheet.addMergedRegion(region);
    }

    /**
     * 将数据写出到excel中
     *
     * @param outputFilePath
     */
    public void exportExcel(String outputFilePath) {
        // 导出之前先自动设置列宽
        this.autoAllSizeColumn();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFilePath);
            workBook.write(outputStream);
        } catch (IOException e) {
            log.error(" exportExcel error", e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        ExcelExporter hssfWorkExcel = new ExcelExporter(new String[] { "姓名", "年龄" }, "人员基本信息");
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map data = new HashMap<>();
            data.put("name", "test" + i);
            data.put("age", "age" + i);
            datas.add(data);
        }
        hssfWorkExcel.createTableRows(datas, new String[] { "name", "age" });
        hssfWorkExcel.mergeCell(1, 2, 0, 1);

        try {
            hssfWorkExcel.exportExcel(new FileOutputStream(new File("C:\\Users\\zeng\\Desktop\\alibaba\\test.xls")));
        } catch (FileNotFoundException e) {
            log.info(e.getMessage());
        }
    }

}