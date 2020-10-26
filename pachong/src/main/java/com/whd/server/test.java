package com.whd.server;

import com.whd.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: pachong--com.whd.server
 * @author: WaHotDog 2020-10-23 14:46
 **/


public class test {
    public static void main(String[] args) throws IOException {
        ExcelUtil excelUtil = new ExcelUtil();
//        for (int i = 0; i < 10 ; i++) {
//            excelUtil.outExcel(i,"123","F:\\face\\职业百科数据1.xls");
//        }
        String str ="123";
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("sheet");
        File file = new File("F:\\face\\职业百科数据1.xls");
        if (file.exists()){
            file.delete();
        }
        List<HSSFCell> cells = new ArrayList<HSSFCell>();
        List<HSSFRow> rows = new ArrayList<HSSFRow>();
        for (int i = 0; i < 11; i++) {
            rows.add(sheet.createRow(i));
            for (int j = 0; j < 5; j++) {
                cells.add(rows.get(i).createCell(j));
            }
            for (int j = 0; j < cells.size(); j++) {
                cells.get(j).setCellValue("123");
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}
