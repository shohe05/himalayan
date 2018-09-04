package com.shotana.himalayan.infrastructure.crawler;

import com.shotana.himalayan.domain.TvProgramCrawler;
import com.shotana.himalayan.domain.model.Keyword;
import com.shotana.himalayan.domain.model.TvProgram;
import com.shotana.himalayan.domain.model.TvPrograms;
import org.apache.tomcat.jni.Local;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class YahooTvCrawler implements TvProgramCrawler {

    private static final String YAHOO_TV_URL = "https://tv.yahoo.co.jp/search/?q=";

    public TvPrograms crawl(Keyword keyword) throws IOException {
        TvPrograms tvPrograms = new TvPrograms();

        Document document = Jsoup.connect(YAHOO_TV_URL + keyword.getText()).get();

        List<Element> elements = document.select("ul.programlist > li");

        for (Element element : elements) {
            TvProgram tvProgram = new TvProgram(
                    getTitle(element),
                    getDescription(element),
                    getStartAt(element),
                    getEndAt(element),
                    getStation(element),
                    getDeteilUrl(element)
            );

            tvPrograms.add(tvProgram);
        }

        return tvPrograms;
    }

    private String getTitle(Element element) {
        return element
                .getElementsByClass("rightarea").get(0)
                .getElementsByTag("p").get(0)
                .getElementsByTag("a").get(0)
                .text();
    }

    private String getDescription(Element element) {
        return element
                .getElementsByClass("rightarea").get(0)
                .getElementsByTag("p").get(2)
                .text();
    }

    private LocalDateTime getStartAt(Element element) {
        // m/d形式の日付
        String date = element
                .getElementsByClass("leftarea").get(0)
                .getElementsByTag("p").get(0)
                .getElementsByTag("em").get(0)
                .text();

        String time = element
                .getElementsByClass("leftarea").get(0)
                .getElementsByTag("p").get(1)
                .getElementsByTag("em").get(0)
                .text();
        time = time.substring(0, time.indexOf("～"));

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startAt = LocalDateTime.parse(now.getYear() + "/" + date + " " + time,
                DateTimeFormatter.ofPattern("yyyy/M/d H:mm"));

        if (startAt.isBefore(now)) {
            startAt = startAt.plusYears(1);
        }

        return startAt;
    }

    private LocalDateTime getEndAt(Element element) {
        // m/d形式の日付
        String date = element
                .getElementsByClass("leftarea").get(0)
                .getElementsByTag("p").get(0)
                .getElementsByTag("em").get(0)
                .text();

        String time = element
                .getElementsByClass("leftarea").get(0)
                .getElementsByTag("p").get(1)
                .getElementsByTag("em").get(0)
                .text();
        time = time.substring(time.indexOf("～") + 1);

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime endAt = LocalDateTime.parse(now.getYear() + "/" + date + " " + time,
                DateTimeFormatter.ofPattern("yyyy/M/d H:mm"));

        if (endAt.isBefore(now)) {
            endAt = endAt.plusYears(1);
        }

        return endAt;
    }

    private String getStation(Element element) {
        return element
                .getElementsByClass("rightarea").get(0)
                .getElementsByTag("p").get(1)
                .getElementsByTag("span").get(0)
                .text();
    }

    private String getDeteilUrl(Element element) {
        return "https://tv.yahoo.co.jp"
                + element
                .getElementsByClass("rightarea").get(0)
                .getElementsByTag("p").get(0)
                .getElementsByTag("a").get(0)
                .attr("href");
    }
}
