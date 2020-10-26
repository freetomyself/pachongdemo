package com.whd.server;



import com.alibaba.fastjson.JSON;
import com.whd.bean.ResultJson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析json
 */

public class GetWebInfo {

    /**
     * 页面是通过json里面的id值拼接超链接
     * @param url
     * @return 返回id拼接后的所有连接
     */
    public static List<String> geturls(String url){
        String urlContent = GetWebInfo.getURLContent(url);
        //用于存放请求页面url
        List<String> urls= new ArrayList<String>();
        List<ResultJson> jsonObject1 = JSON.parseArray(urlContent, ResultJson.class);
        //获取json中数据长度
        int size1 = jsonObject1.size();
        for (int i = 0; i < size1; i++) {
            //获取json中子类数个数
            int size2 = jsonObject1.get(i).getOccInfos().size();
            for (int j = 0; j < size2; j++) {
                //去除对应页面的id
                String id = jsonObject1.get(i).getOccInfos().get(j).getId();
                //url拼接
                String href= "https://xz.chsi.com.cn/occupation/occudetail.action?id="+id;
                urls.add(href);
            }
        }
        return  urls;
    }

    /**
     * 程序中访问http数据接口
     */
    public static String getURLContent(String urlStr) {
        /** 网络的url地址 */
        URL url = null;
        /** http连接 */
        HttpURLConnection httpConn = null;
        /**//** 输入流 */
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ex) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        String result = sb.toString();
        return result;
    }

}
