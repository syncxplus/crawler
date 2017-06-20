package com.club.task;

import com.club.crawler.AutoHome;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Crawler {
    private static final Logger logger = LoggerFactory.getLogger(Crawler.class);

    @Value("${crawler.autohome.storage}")
    String storage;

    @Scheduled(cron = "${crawler.autohome.task}")
    public synchronized void cron() {
        logger.info("crawler task");

        int numberOfCrawlers = 1;

        try {
            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(storage);
            config.setMaxDepthOfCrawling(3);
            config.setPolitenessDelay(5000);
            config.setResumableCrawling(true);
            config.setUserAgentString("Mozilla/5.0 AppleWebKit/537.36 Chrome/57.0.2987.133 Safari/537.36");

            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
            controller.addSeed("http://club.autohome.com.cn");
            controller.startNonBlocking(AutoHome.class, numberOfCrawlers);
        } catch (Exception e) {
            logger.error("crawler error", e);
        }
    }
}
