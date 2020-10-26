package com.whd.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: pachong--com.whd.util
 * @author: WaHotDog 2020-10-23 17:25
 **/

public class OtherExcelUtils{

    public static HSSFWorkbook listxToExcel(String sheetName, LinkedHashMap<String, String> map, List<?> list) {
        Object[] fieldsName =  map.keySet().toArray();		//字段集合
        Object[] rowsName =  map.values().toArray();		//标题集合
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);				//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//垂直居中
        style.setWrapText( true);									//自动换行
        HSSFCell cell=null;
        //写入标题
        for (int i = 0; i < rowsName.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(rowsName[i].toString());
            cell.setCellStyle(style);
        }

        // 第五步，创建单元格，并设置值
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            for (int j = 0; j < fieldsName.length; j++) {
                cell = row.createCell(j);
                Object obj = list.get(i);
                String jsonString = JSONObject.toJSONString(obj);
                JSONObject jo = JSONObject.parseObject(jsonString);
                //jsonString = jo.get("columns").toString();
                jo = JSONObject.parseObject(jsonString);
                if (jo.get(fieldsName[j].toString())==null) {
                    cell.setCellValue("");
                }else{
                    cell.setCellValue(jo.get(fieldsName[j].toString()).toString());
                }
                cell.setCellStyle(style);
            }
		/*
		// img --start
		ByteArrayOutputStream outStream_item = new ByteArrayOutputStream();
		// 将图片写入流中
		BufferedImage bufferImg_item;
		try {
			bufferImg_item = ImageIO.read(getInputStream("https://www.baidu.com/img/bd_logo1.png"));
			//bufferImg_item = ImageIO.read(getInputStream("http://39.108.192.245"+list.get(i).getStr("goodsImg")));
			ImageIO.write(bufferImg_item, "PNG", outStream_item);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//设置图片路径
		HSSFClientAnchor anchor_item = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1+i, (short) 2, 2+i);
		patri_item.createPicture(anchor_item, wb.addPicture(outStream_item.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		// img -- end
		*/
        }
        return wb;
    }
}
