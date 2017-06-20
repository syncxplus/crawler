package com.club.parser;

import com.club.Bootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jibo on 2017/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
public class ParserTest {
    @Autowired
    ForumParser forumParser;
    @Autowired
    ClubListParser clubListParser;
    @Autowired
    ClubIndexParser clubIndexParser;


    @Test
    public void parse() {
        forumParser.getPageCaches().forEach(file -> {
            System.out.println(file);
            forumParser.parse(file);
        });
        clubListParser.getPageCaches().forEach(file -> {
            System.out.println(file);
            clubListParser.parse(file);
        });
        clubIndexParser.getPageCaches().forEach(file -> {
            System.out.println(file);
            clubIndexParser.parse(file);
        });
    }
}
