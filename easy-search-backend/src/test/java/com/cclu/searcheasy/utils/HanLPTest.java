package com.cclu.searcheasy.utils;

import com.hankcs.hanlp.summary.TextRankKeyword;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/7/15 21:07
 */
public class HanLPTest {
    @Test
    public void test() {
        String title = "Java编程实践：如何高效学习和应用HanLP";

        // 提取关键词
        TextRankKeyword textRankKeyword = new TextRankKeyword();
        List<String> keywords = textRankKeyword.getKeywordList(title, 4);

        // 打印提取的关键词作为标签
//        for (String keyword : keywords) {
//            System.out.println(keyword);
//        }
        System.out.println(keywords);
    }
}
