package com.cclu.searcheasy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.model.dto.picture.PictureQueryRequest;
import com.cclu.searcheasy.model.entity.Picture;
import com.cclu.searcheasy.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/7/16 8:43
 */
@Service
public class PictureServiceImpl implements PictureService {

    public static final int pageSize = 10;

    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        String url = String.format("https://ssr1.scrape.center/page/%d", pageNum);

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
        if (searchText != null && !"".equals(searchText)) {
            pictureList = pictureList.stream().filter(picture -> picture.getTitle().contains(searchText)).collect(Collectors.toList());
        }
        Page<Picture> picturePage = new Page<>(pageNum, pageSize);
        picturePage.setRecords(pictureList);
        return picturePage;
    }

    @Override
    public Page<Picture> listPictureByPage(PictureQueryRequest pictureQueryRequest) {
        long pageNum = pictureQueryRequest.getCurrent();
        String searchText = pictureQueryRequest.getSearchText();

        String url = String.format("https://ssr1.scrape.center/page/%d", pageNum);

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
        if (searchText != null && !"".equals(searchText)) {
            pictureList = pictureList.stream().filter(picture -> picture.getTitle().contains(searchText)).collect(Collectors.toList());
        }
        Page<Picture> picturePage = new Page<>(pageNum, pageSize);
        picturePage.setRecords(pictureList);
        return picturePage;
    }
}
