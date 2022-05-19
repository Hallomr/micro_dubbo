package com.zxk.core.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelUtil {
    /**
     * 导出
     *
     * @param response
     * @param data
     * @param fileName
     * @param sheetName
     * @param clazz
     * @throws Exception
     */


    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) throws Exception {
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        //字体大小
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName)
                //设置拦截器或自定义样式
                .registerWriteHandler(horizontalCellStyleStrategy).doWrite(data);
    }

    /**
     *
     * @Description//动态表头导出
     * @Date
     * @Param[response,data,fileName,sheetName,clazz,head]
     * @return void
     */

    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName,
                                  String sheetName, Class clazz, List<String> head, SheetWriteHandler handler) throws Exception {
        List<List<String>> list = getHead(head);
        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).head(list).sheet(sheetName)
                //设置拦截器或自定义样式 例：new EasyExcelCustomCell()
                .registerWriteHandler(handler).doWrite(data);
    }

    private static List<List<String>> getHead(List<String> head) {
        List<List<String>> list = new ArrayList<List<String>>();
        head.forEach(one -> {
            ArrayList<String> headName = new ArrayList<>();
            headName.add(one);
            list.add(headName);
        });
        return list;
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }
}
