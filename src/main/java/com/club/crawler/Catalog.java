package com.club.crawler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by jibo on 2017/4/26.
 */
@Component
public class Catalog {
    @Value("${crawler.autohome.storage}")
    String autohomeStorage;
    @Value("${crawler.autohome.url}")
    String autohomeUrl;

    public String getAutohomeCaches() {
        return autohomeStorage + "caches";
    }

    public String getAutohomeUrl() {
        return autohomeUrl;
    }
}
