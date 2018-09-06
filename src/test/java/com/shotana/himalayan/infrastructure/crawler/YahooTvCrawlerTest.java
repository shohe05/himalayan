package com.shotana.himalayan.infrastructure.crawler;

import com.shotana.himalayan.domain.model.Keyword;
import com.shotana.himalayan.domain.model.TvProgram;
import com.shotana.himalayan.domain.model.TvPrograms;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class YahooTvCrawlerTest {

    YahooTvCrawler target;

    @Before
    public void setUp() {
        target = new YahooTvCrawler();
    }

    @Test
    public void crawl() throws IOException {
        List<TvProgram> actual = target.crawl(new Keyword("情熱大陸")).asList();
        assertThat(actual.get(0).getTitle(), containsString("情熱大陸"));
        assertThat(actual.get(0).getStation(), containsString("TBS"));
    }
}