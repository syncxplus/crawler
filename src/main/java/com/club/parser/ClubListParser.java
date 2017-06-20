package com.club.parser;

import com.club.crawler.Catalog;
import com.club.data.ClubList;
import com.club.data.ClubListRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jibo on 2017/4/26.
 */
@Component
public class ClubListParser implements AutohomeParser {
    private static final Logger logger = LoggerFactory.getLogger(ClubListParser.class);

    @Autowired
    Catalog catalog;
    @Autowired
    ClubListRepository clubListRepository;

    @Override
    public List<File> getPageCaches() {
        File storage = new File(catalog.getAutohomeCaches() + File.separator + "club-list");
        return storage.isDirectory() ? Arrays.asList(storage.listFiles((dir, name) -> name.endsWith(".html"))) : new ArrayList<>();
    }

    @Override
    public void parse(File file) {
        if (file.isFile()) {
            try {
                String name = file.getName();
                String [] s = name.split("-");
                String refer = (s != null && s.length == 4) ? s[2] : "null";
                String referUrl = catalog.getAutohomeUrl() + "/carclub/" + name;
                Document document = Jsoup.parse(file, StandardCharsets.UTF_8.name());
                Elements clubs = document.select(".club-list-con li");
                clubs.forEach(element -> {
                    String title = element.select(".club-list-chara .title01 a").attr("title").trim();
                    String url = catalog.getAutohomeUrl() + element.select(".club-list-chara .title01 a").attr("href").trim();
                    String club = "";
                    if (url.indexOf("index-c-") != -1 && url.indexOf("-1.html") != -1) {
                        String sub = url.substring(url.indexOf("index-c-") + "index-c-".length(), url.indexOf("-1.html"));
                        String [] refers = sub.split("-");
                        if (refers.length == 2) {
                            club = refers[1];
                        }
                    }
                    String total = element.select(".club-list-chara p").get(1).text().trim();
                    String logo = element.select(".club-list-pic img").attr("src").trim();
                    if (clubListRepository.findTopByReferAndTitle(refer, title) == null) {
                        ClubList clubList = new ClubList(title, refer, club, url, referUrl, logo, total);
                        clubListRepository.save(clubList);
                    }
                });
            } catch (Exception e) {
                logger.error("club list parse error {}", file.getAbsoluteFile(), e);
            }
        }
    }
}
