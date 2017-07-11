package com.mock.crawler.toolBox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by 11273 on 2017/7/4.
 */
public class CrawlerTable {
    public static void main(String[] args) throws IOException {
        //获取document对象
        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/index.html").get();
        //省级和直辖市
        Elements body = document.select("tr.provincetr");
        for (Element element : body) {
            Elements allElements = element.select("a[href]");
            for (Element element1:allElements){
                if (element1.text()!=""&&element1.text().length()>0) {
                    System.err.println("---->[省级和直辖市]"+element1.text());
                    System.err.println("----><a>"+element1.html("a").attr("href"));
                    String href = element1.html("a").attr("href");
                }
            }

        }
    }
}
