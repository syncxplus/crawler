package com.club.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jibo on 2017/4/24.
 */
public class AutoHome extends WebCrawler {
    private static final Map<String, Integer> FILTERS = new HashMap<String, Integer>(){
        {
            put("forum", 1);
            put("club-list", 2);
            put("club-index", 3);
        }
    };

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        logger.debug("shouldVisit? {}", url.getURL());
        return !identifyUrl(url.getURL()).equals(0);
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        Integer i = identifyUrl(url);
        String filter = getFilterName(i);
        if (!i.equals(0) && StringUtils.isNotEmpty(filter)) {
            logger.debug("visit {}", url);
            HtmlParseData data = (HtmlParseData) page.getParseData();
            try {
                File location = new File(getCachesPath(), filter);
                if (!location.isDirectory()) {
                    location.mkdirs();
                }
                String name = new File(page.getWebURL().getPath()).getName();
                File file = new File(location, name);
                if (!file.isFile()) {
                    Files.write(Paths.get(file.getAbsolutePath()), data.getHtml().getBytes());
                }
            } catch (IOException e) {
                logger.error("Failed to save {}", url, e);
            }
        }
    }

    private Integer identifyUrl(String url) {
        logger.debug("identify url {}", url);
        if (StringUtils.isEmpty(url)) {
            return 0;
        } else if (url.startsWith("http://club.autohome.com.cn/bbs/forum-c") && url.endsWith("-1.html")) {
            return FILTERS.get("forum");
        } else if (url.startsWith("http://club.autohome.com.cn/carclub/search-c") && url.contains(".html")) {
            return FILTERS.get("club-list");
        } else if (url.startsWith("http://club.autohome.com.cn/carclub/index") && url.contains("-1.html")) {
            return FILTERS.get("club-index");
        }
        return 0;
    }

    private String getFilterName(Integer i) {
        for(String name : FILTERS.keySet()) {
            if (FILTERS.get(name).equals(i)) {
                return name;
            }
        }
        return null;
    }

    private String getCachesPath() {
        String path = "/crawler/autohome/caches/";
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/application.properties"));
            properties.entrySet().forEach(name -> logger.info("name: {}", properties.get(name)));
            String storage = String.valueOf(properties.get("crawler.autohome.storage"));
            if (!storage.equals("null")) {
                path = new File(storage, "caches").getPath();
            }
        } catch (IOException e) {
            logger.error("caches path error", e);
        } finally {
            return path;
        }
    }
}
