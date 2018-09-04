package com.shotana.himalayan;

import com.shotana.himalayan.domain.Notifier;
import com.shotana.himalayan.domain.TvProgramCrawler;
import com.shotana.himalayan.infrastructure.crawler.YahooTvCrawler;
import com.shotana.himalayan.infrastructure.notifier.LineNotifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public TvProgramCrawler tvProgramCrawler() {
        return new YahooTvCrawler();
    }

    @Bean
    public Notifier notifier(RestTemplateBuilder restTemplateBuilder) {
        return new LineNotifier(restTemplateBuilder);
    }
}
