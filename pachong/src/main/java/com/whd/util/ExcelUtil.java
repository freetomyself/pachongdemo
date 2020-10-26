package com.whd.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: pachong--com.whd.util
 * @author: WaHotDog 2020-10-23 14:05
 **/

/**
 * 导出Excel工具
 */
public  class  ExcelUtil {
    /**
     *
     * @param col 指定每列的个数
     * @param list 需要导出的数据集合
     * @param path 文件导出的具体位置
     * @throws IOException
     */
    public static void outExcel(int col,List<String> list, String path) throws IOException {
        //创建HSSFWorkbook对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //生成HSSFWorkbook对象
        HSSFSheet sheetSheet = hssfWorkbook.createSheet("sheet");
       //通过循环创建出每行每列的list
        List<HSSFCell> cells = new ArrayList<HSSFCell>();
        List<HSSFRow> rows = new ArrayList<HSSFRow>();
        //用于设定一共多少行
        for (int i = 0; i <list.size()/col; i++) {
            //用于设置第几行
            rows.add(sheetSheet.createRow(i));
            //用于设置具体单元格
            for (int j = 0; j < col; j++) {
                cells.add(rows.get(i).createCell(j));
            }
            //用于设置单元格中的数据
            for (int j = 0; j < cells.size(); j++) {
                cells.get(j).setCellValue(list.get(j));
            }
        }
        //导出到指定位置 追加式写入
        FileOutputStream fos = new FileOutputStream(path, true);
        hssfWorkbook.write(fos);
        fos.flush();
        fos.close();
    }

}
