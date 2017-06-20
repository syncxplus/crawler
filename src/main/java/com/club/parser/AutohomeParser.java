package com.club.parser;

import java.io.File;
import java.util.List;

/**
 * Created by jibo on 2017/4/26.
 */
public interface AutohomeParser {
    List<File> getPageCaches();
    void parse(File file);
}
