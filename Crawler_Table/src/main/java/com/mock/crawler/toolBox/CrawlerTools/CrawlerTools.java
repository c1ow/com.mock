package com.mock.crawler.toolBox.CrawlerTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.sql.rowset.serial.SerialRef;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11273 on 2017/7/4.
 * 层级获取
 */
public class CrawlerTools {
    public static JSONObject crawlerTable(JSONObject jsonObject) throws IOException {
        List<String> hrefs = (List<String>) jsonObject.get("href");
        List<String> href = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for (String aHref : hrefs) {
            System.err.println("----href----"+aHref);
            Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/" + aHref).get();
            Elements tr = document.select("tr." + jsonObject.getString("clazz"));
            //市级行政区
            for (Element element : tr) {
                Elements allElements = element.select("a[href]");
                for (int i=0;i<allElements.size();i++) {
                    if (allElements.get(i).text().length() > 0 && allElements.get(i).text() != "") {
                        name.add(allElements.get(i).text());
                        href.add(allElements.get(i).html("a").attr("href"));
                    }
                }
            }
        }
        List<String> tempList = new ArrayList<>();
        for(String i:href){
            if(!tempList.contains(i)){
                tempList.add(i);
            }
        }
        if (jsonObject.getInteger("flag")==1){
            for (int k=0;k<name.size();k++){
                System.err.println("=======>>>名称:"+name.get(k));
                System.err.println("=======>>>链接："+tempList.get(k));
            }
        }
        if (jsonObject.getInteger("flag")==2){
            for (int k=0;k<name.size();k+=2){
                if (k>0){
                     System.err.println("=======>>>统计用区划代码:"+name.get(k)+",====>>>名称:"+name.get(k+1)+"======>>>链接"+tempList.get(k/2));
                }else {
                    System.err.println("=======>>>统计用区划代码:"+name.get(0)+",====>>>名称:"+name.get(1)+"======>>>链接"+tempList.get(0));
                }
            }
        }
        if (jsonObject.getInteger("flag")==3){
            for (int k=0;k<name.size();k+=3){
                if (k>0){
                    System.err.println("=======>>>统计用区划代码:"+name.get(k)+",====>>>城乡分类代码:"+name.get(k+1)+",====>>>名称:"+name.get(k+2)+"======>>>链接"+tempList.get(k/3));
                }else {
                    System.err.println("=======>>>统计用区划代码:"+name.get(0)+",====>>>城乡分类代码:"+name.get(1)+",====>>>名称:"+name.get(2)+"======>>>链接"+tempList.get(0));
                }
            }
        }
        jsonObject.clear();
        jsonObject.put("href", tempList);
        return jsonObject;
    }
}
