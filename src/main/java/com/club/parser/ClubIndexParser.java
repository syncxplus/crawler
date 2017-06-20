package com.club.parser;

import com.club.crawler.Catalog;
import com.club.data.ClubIndex;
import com.club.data.ClubIndexRepository;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by jibo on 2017/4/26.
 */
@Component
public class ClubIndexParser implements AutohomeParser {
    private static final Logger logger = LoggerFactory.getLogger(ClubIndexParser.class);

    @Autowired
    Catalog catalog;
    @Autowired
    ClubIndexRepository clubIndexRepository;

    @Override
    public List<File> getPageCaches() {
        File storage = new File(catalog.getAutohomeCaches() + File.separator + "club-index");
        return storage.isDirectory() ? Arrays.asList(storage.listFiles((dir, name) -> name.endsWith("-1.html"))) : new ArrayList<>();
    }

    @Override
    public void parse(File file) {
        if (file.isFile()) {
            try {
                String name = file.getName();
                String [] s = name.split("-");
                String refer = (s != null && s.length == 5) ? s[2] : "null";
                String club = (s != null && s.length == 5) ? s[3] : "null";

                if (clubIndexRepository.findTopByClub(club) != null) {
                    return;
                }

                Document document = Jsoup.parse(file, StandardCharsets.UTF_8.name());
                String title = document.select(".club-left-chara h1").attr("title").trim();
                String url = catalog.getAutohomeUrl() + "/carclub/" + name;
                String chief = document.select(".club-left-chara p").get(0).select("a").attr("title").trim();
                String chiefUrl = document.select(".club-left-chara p").get(0).select("a").attr("href").trim();
                String assistant = "";
                Elements elements = document.select(".club-left-chara p").get(1).select("a");
                if (elements.size() != 0) {
                    List<Map<String, String>> list = new ArrayList<>();
                    elements.forEach(element -> {
                        list.add(new HashMap<String, String>(){
                            {
                                put("name", element.attr("title").trim());
                                put("url", element.attr("href").trim());
                            }
                        });
                    });
                    assistant = JSONObject.valueToString(list);
                }

                clubIndexRepository.save(new ClubIndex(title, refer, club, url, chief, chiefUrl, assistant));
            } catch (Exception e) {
                logger.error("club index parse error {}", file.getAbsoluteFile(), e);
            }
        }
    }
}
