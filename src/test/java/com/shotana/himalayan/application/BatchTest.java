package com.shotana.himalayan.application;

import com.shotana.himalayan.infrastructure.crawler.YahooTvCrawler;
import com.shotana.himalayan.infrastructure.notifier.LineNotifier;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;

import static org.junit.Assert.*;

public class BatchTest {

    private Batch target;

    @Before
    public void setUp(){
        target = new Batch(new YahooTvCrawler(), new LineNotifier(new RestTemplateBuilder()));
    }

    @Test
    public void execute() throws IOException {
        target.execute();
    }
}