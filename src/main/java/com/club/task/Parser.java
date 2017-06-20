package com.club.task;

import com.club.parser.ClubIndexParser;
import com.club.parser.ClubListParser;
import com.club.parser.ForumParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jibo on 2017/4/26.
 */
@Component
public class Parser {
    private static final Logger logger = LoggerFactory.getLogger(Parser.class);

    @Autowired
    ForumParser forumParser;
    @Autowired
    ClubListParser clubListParser;
    @Autowired
    ClubIndexParser clubIndexParser;

    @Scheduled(cron = "${crawler.autohome.parse}")
    public void parse() {
        logger.info("parse task");
        forumParser.getPageCaches().forEach(file -> {
            logger.debug("parse {}", file);
            forumParser.parse(file);
            file.delete();
        });
        clubListParser.getPageCaches().forEach(file -> {
            logger.debug("parse {}", file);
            clubListParser.parse(file);
            file.delete();
        });
        clubIndexParser.getPageCaches().forEach(file -> {
            logger.debug("parse {}", file);
            clubIndexParser.parse(file);
            file.delete();
        });
    }
}
