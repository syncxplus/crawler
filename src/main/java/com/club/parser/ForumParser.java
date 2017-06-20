package com.club.parser;

import com.club.crawler.Catalog;
import com.club.data.Forum;
import com.club.data.ForumRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class ForumParser implements AutohomeParser {
    private static final Logger logger = LoggerFactory.getLogger(ForumParser.class);
    private static final String dumb = "论坛";

    @Autowired
    Catalog catalog;
    @Autowired
    ForumRepository forumRepository;

    @Override
    public List<File> getPageCaches() {
        File storage = new File(catalog.getAutohomeCaches() + File.separator + "forum");
        return storage.isDirectory() ? Arrays.asList(storage.listFiles((dir, name) -> name.endsWith(".html"))) : new ArrayList<>();
    }

    @Override
    public void parse(File file) {
        if (file.isFile()) {
            try {
                String name = file.getName();
                String [] s = name.split("-");
                String refer = (s != null && s.length == 4) ? s[2] : "null";

                if (forumRepository.findTopByRefer(refer) != null) {
                    return;
                }

                String url = catalog.getAutohomeUrl() + "/bbs/" + name;
                Document document = Jsoup.parse(file, StandardCharsets.UTF_8.name());
                String title = document.select(".cbinfo h1").attr("title");
                if (title.endsWith(dumb)) {
                    title = title.substring(0, title.lastIndexOf(dumb));
                }

                forumRepository.save(new Forum(title, refer, url));
            } catch (Exception e) {
                logger.error("Forum parse error {}", file.getAbsoluteFile(), e);
            }
        }
    }
}
