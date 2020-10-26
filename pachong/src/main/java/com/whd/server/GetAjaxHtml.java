package com.whd.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 用于获取js之后的页面
 */
public class GetAjaxHtml {
    public static String getAjaxContent(String url) throws Exception {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("D:/whdIDEASpace/pachong/target/classes/phantomjs.exe D:/whdIDEASpace/pachong/target/classes/Phantomjs2.js " + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while((tmp=br.readLine())!=null) {
            sbf.append(tmp + "\n");
        }
        return sbf.toString();
    }

}