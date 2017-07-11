package com.mock.crawler.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mock.crawler.Model.Table;
import com.mock.crawler.dao.TableDao;
import com.mock.crawler.toolBox.PageBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11273 on 2017/7/4.
 */
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableDao tableDao;

    /**
     * 获取省
     *
     * @return
     * @throws IOException
     */
    public List<Table> getPro() throws IOException {
        List<Table> tableList = new ArrayList<>();

        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/")
                .timeout(50000)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                .get();
        Elements trs = document.select("tr.provincetr");
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            for (Element td : tds) {
                Element a = td.select("a[href]").get(0);
                String name = a.text();
                String url = a.attr("href");
                Table t = new Table();
                t.setUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/" + url);

                String code = url.substring(0, url.lastIndexOf("."));
                t.setAdministrativeDistrict(name);
                t.setAreaNumber(code + "0000000000");
                t.setType("province");
                tableList.add(t);
            }
        }
        return tableList;
    }

    /**
     * 获取村
     *
     * @param hrefs
     * @param type
     * @return
     */
    public List<Table> getVillagetr(List<Table> hrefs, String type) {
        List<Table> tableList = new ArrayList<>();
        List<Table> errorHref = new ArrayList<>();

        for (Table table : hrefs) {
            String aHref = table.getUrl();
            if (aHref == null || aHref.length() == 0) {
                continue;
            }
            Document document = null;
            try {
                document = Jsoup.connect(aHref)
                        .timeout(1200000)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                        .get();
            } catch (Exception e) {
                System.out.println("发生错误了 mark一下 == " + aHref);
                System.out.println("原因：" + e.getMessage());
                errorHref.add(table);
                continue;
                //e.printStackTrace();
            }
//            String type = jsonObject.getString("type");
            System.out.println("当前url:：" + aHref);
            Elements trs = document.select("tr." + type);
            for (Element e : trs) {
                Elements tr = e.getElementsByTag("td");
                //市级行政区
                Element codeElement = tr.get(0);
                String code = "";
                String name = "";
                String typeCode = "";
                //获取Code
                code = codeElement.text();
//                System.out.println("code=" + code);
                //获取城乡分类代码
                Element typeCodeElement = tr.get(1);
                typeCode = typeCodeElement.text();
//                System.err.println("typeCode = "+typeCode);
                //获取名称
                Element nameElement = tr.get(2);
                name = nameElement.text();
//                System.err.println("name = "+name);

                Table t = new Table();

                t.setAdministrativeDistrict(name);
                t.setAreaNumber(code);
                t.setTypeCode(typeCode);
                t.setParent(table.getAreaNumber());
                t.setType(type);
                tableList.add(t);
            }


//            for (Element element : tr) {
//                Elements allElements = element.select("a[href]");
//                for (int i=0;i<allElements.size();i++) {
//                    if (allElements.get(i).text().length() > 0 && allElements.get(i).text() != "") {
//                        name.add(allElements.get(i).text());
//                        href.add(allElements.get(i).html("a").attr("href"));
//                    }
//                }
//            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("=="+aHref);
            ///
        }

        if (errorHref.size() > 0) {
            tableList.addAll(getVillagetr(errorHref, type));
        }

        return tableList;
    }

    /**
     * 填充到数据库
     *
     * @param jsonObject
     * @throws IOException
     */
    @Override
    public void insert(JSONObject jsonObject) throws IOException {

        //获取父类的Url
        new PageBox<Table>().getPage(jsonObject);
        List<Table> hrefs = tableDao.findUrlList(jsonObject);

        String type = jsonObject.getString("type");
        String flag = jsonObject.getString("flag");
        System.out.println("type = " + type);
        List<Table> tableList = null;
        if (flag.equals("1")) {
            tableList = getPro();
        } else if (flag.equals("2")) {
            tableList = getSonTables(hrefs, type);
        } else {
            tableList = getVillagetr(hrefs, type);
        }


        for (Table t : tableList) {
            System.err.println("=======>>>名称:" + t.getAdministrativeDistrict() + "code" + t.getAreaNumber()+"typeCode"+t.getTypeCode());
            tableDao.insert(t);
        }
    }

    /**
     * 获取2-4
     *
     * @param hrefs
     * @param type
     * @return
     */
    public List<Table> getSonTables(List<Table> hrefs, String type) {
        List<Table> tableList = new ArrayList<>();
        List<Table> errorHref = new ArrayList<>();

        for (Table table : hrefs) {
            String aHref = table.getUrl();
            if (aHref == null || aHref.length() == 0) {
                continue;
            }
            Document document = null;
            try {
                document = Jsoup.connect(aHref)
                        .timeout(50000)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                        .get();
            } catch (Exception e) {
                System.out.println("发生错误了 mark一下 == " + aHref);
                System.out.println("原因：" + e.getMessage());
                errorHref.add(table);
                continue;
                //e.printStackTrace();
            }
//            String type = jsonObject.getString("type");
            System.out.println("当前url:：" + aHref);
            Elements trs = document.select("tr." + type);
            for (Element e : trs) {
                Elements tr = e.getElementsByTag("td");
                //市级行政区
                Element codeElement = tr.get(0);
                String code = "";
                if (codeElement.select("a[href]").size() == 0) {
                    // 没有 a
                    code = codeElement.text();
                } else {
                    code = codeElement.select("a[href]").get(0).text();
                }
                String name = "";
                String url = "";
                System.out.println("code=" + code);
                Element nameElement = tr.get(1);
                if (nameElement.select("a[href]").size() == 0) {
                    // 没有 a
                    name = nameElement.text();
                    url = "";
                } else {
                    name = nameElement.select("a[href]").get(0).text();
                    url = nameElement.select("a[href]").get(0).html("a").attr("href");
                }
                Table t = new Table();
                t.setAdministrativeDistrict(name);
                if (url.length() != 0) {
                    url = table.getUrl().substring(0, table.getUrl().lastIndexOf("/") + 1) + url;
                    System.out.println("url = " + url);
                    t.setUrl(url);
                } else {
                    System.out.println("没有 下一级的url了");
                }

                t.setAreaNumber(code);
                t.setParent(table.getAreaNumber());
                t.setType(type);
                tableList.add(t);
            }


//            for (Element element : tr) {
//                Elements allElements = element.select("a[href]");
//                for (int i=0;i<allElements.size();i++) {
//                    if (allElements.get(i).text().length() > 0 && allElements.get(i).text() != "") {
//                        name.add(allElements.get(i).text());
//                        href.add(allElements.get(i).html("a").attr("href"));
//                    }
//                }
//            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("=="+aHref);
            ///
        }

        if (errorHref.size() > 0) {
            tableList.addAll(getSonTables(errorHref, type));
        }

        return tableList;
    }
}
