package com.cclu.searcheasy.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cclu.searcheasy.model.entity.Picture;
import com.cclu.searcheasy.model.entity.Post;
import com.cclu.searcheasy.service.PostService;
import com.hankcs.hanlp.summary.TextRankKeyword;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChangCheng Lu
 * @date 2023/7/15 15:20
 */
@SpringBootTest
public class CrawlerTest {

    @Resource
    private PostService postService;

    @Test
    public void fetchPost() {
        String url = "https://spa4.scrape.center/api/news/?limit=10&offset=0";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("limit", 10);
        paramMap.put("offset", 0);

        String result = HttpUtil.get(url, paramMap);
//        System.out.println(result);
        Map<String, Object> resultMap = JSONUtil.toBean(result, HashMap.class);
        JSONArray postJsonArray = (JSONArray) resultMap.get("results");

        List<Post> postList = new ArrayList<>();
        for (Object postJson : postJsonArray) {
            JSONObject postMap = (JSONObject) postJson;
            // 获取标题
            String title = postMap.get("title", String.class);
            String contentUrl = postMap.get("url", String.class);
            // 获取内容
            String contentString = null;
            try {
                Document document = Jsoup.connect(contentUrl).get();
                Elements paragraphs = document.select("p");
                StringBuilder sb = new StringBuilder();
                for (Element paragraph : paragraphs) {
                    sb.append(paragraph.text()).append("\n");
                }
                contentString = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 分词，获取 标签
            TextRankKeyword textRankKeyword = new TextRankKeyword();
            List<String> keywords = textRankKeyword.getKeywordList(title, 3);
            String tagList = keywords.toString();
            // 保存到数据库
            Post post = new Post();
            post.setTitle(title);
            post.setContent(contentString);
            post.setTags(tagList);
            post.setUserId(1L);
            postList.add(post);
        }

        boolean b = postService.saveBatch(postList);
        Assertions.assertTrue(b);
    }

    @Test
    public void fetchPicture() {
        int page = 1;
        String url = String.format("https://ssr1.scrape.center/page/%d", page);

        List<Picture> pictureList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".el-card.item.m-t.is-hover-shadow");
            for (Element element : elements) {
                // 获取 图片 URL
                Element imgCardElement = element.select(".el-col.el-col-24.el-col-xs-8.el-col-sm-6.el-col-md-4").get(0);
                String imgUrl = imgCardElement.select("img").get(0).attr("src");
                // 获取 图片 标题
                String title = element.select("h2.m-b-sm").get(0).text();
                // 创建 图片对象，保存到列表中
                Picture picture = new Picture();
                picture.setTitle(title);
                picture.setUrl(imgUrl);
                pictureList.add(picture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Picture picture : pictureList) {
            System.out.println(picture);
        }
    }

}
