package com.whd.server;

import com.whd.util.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: pachong--com.whd.server
 * @author: WaHotDog 2020-10-26 08:21
 **/


public class test2 {
    public static void main(String[] args) throws IOException {

        String path="D:\\whdIDEASpace\\pachong\\test.xls";
        List<String> list = new ArrayList<String>();
        list.add("姓名");
        list.add("年龄");
        list.add("地址");
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf((i*1)));
            list.add(String.valueOf((i*2)));
            list.add(String.valueOf((i*3)));
        }
        ExcelUtil.outExcel(3,list,path);

    }
}
