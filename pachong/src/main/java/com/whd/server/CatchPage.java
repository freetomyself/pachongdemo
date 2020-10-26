package com.whd.server;

import com.whd.util.ExcelUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: pachong--com.whd.server
 * @author: WaHotDog 2020-10-21 15:18
 **/


/**
 * 最终执行的地方
 */
public class CatchPage {

    public static void main(String[] args) throws Exception {
//        创建一个连接对网页惊醒请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
// 请求地址 响应请求
//        HttpGet httpGet = new HttpGet("https://xz.chsi.com.cn/occupation/occudetail.action?id=42bkddwlkkai92b5");
        HttpGet httpGet = new HttpGet("https://xz.chsi.com.cn/occupation/occudetail.action?id=dvwhi602rq6flvni");

        /**
         * 获取响应码
         * HttpResponseProxy{HTTP/1.1 200  [Server: Tengine, Date: Wed, 21 Oct 2020 07:42:02 GMT,
         * Content-Type: text/html;charset=utf-8, Transfer-Encoding: chunked, Connection: keep-alive,
         * Set-Cookie: XSRF-CCKTOKEN=38aba13fe87e0d4f9e60a3f221c2a1f7; Path=/; Secure; HttpOnly,
         * Set-Cookie: JSESSIONID=84171C06E7C17960F9318275DB03D3F6; Path=/; Secure; HttpOnly, Strict-Transport-Security: max-age=31536000] org.apache.http.client.entity.DecompressingEntity@701fc37a}
         * */
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

//        获取到响应的实体
        HttpEntity entity = httpResponse.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");


//        Jsoup html解析器 解析url地址 HTML文本内容
        Document document = Jsoup.parse(content);

        GetWebInfo getWebInfo = new GetWebInfo();
        //添加json数据地址
        List<String> geturls = GetWebInfo.geturls("https://xz.chsi.com.cn/ajax/occuindexqt.action");
        List<String> list = new ArrayList<String>();
        //指定文件位置
        String path = "F:\\face\\test.xls";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        //耗时统计
        long start = System.currentTimeMillis();
//        for (int i = 0; i < geturls.size(); i++) {
        for (int i = 0; i < 2; i++) {

            //调用js之后的页面
            String result = GetAjaxHtml.getAjaxContent(geturls.get(i));
//            Jsoup html解析器 解析url地址 HTML文本内容
            Document document2 = Jsoup.parse(result);

            String title = document2.select("h3.xz-occ-title").get(0).text();
            String context = document2.select("div.xz-occ-zydesc").get(0).text();
            list.add(title);
//            list.add(context);
            int size = document2.select("div.xz-part").size();
            for (int y = 0; y < 6; y++) {
//                    BufferedWriter bw2 = new BufferedWriter(new FileWriter("F:\\face\\职业百科数据.txt",true));
                String pageinfo = document2.select("div.xz-part").get(y).text();
                list.add(pageinfo);
                System.out.println(pageinfo);
            }
            String pageinfo = document2.select("div.xz-part").get(size - 2).text();
            list.add(pageinfo);
            System.out.println(pageinfo);
            System.out.println("共" + geturls.size() + "执行到第" + (i + 1));
        }
        //调用excel导出工具
        ExcelUtil.outExcel(2, list, path);
        long end = System.currentTimeMillis();
        System.out.println("===============" + "共" + geturls.size() + "条,耗时：" + (end - start) + "===============");

    }

}
