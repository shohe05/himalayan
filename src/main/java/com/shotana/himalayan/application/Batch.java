package com.shotana.himalayan.application;

import com.shotana.himalayan.domain.Notifier;
import com.shotana.himalayan.domain.TvProgramCrawler;
import com.shotana.himalayan.domain.model.Keyword;
import com.shotana.himalayan.domain.model.Token;
import com.shotana.himalayan.domain.model.TvPrograms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Batch {
    private final TvProgramCrawler tvProgramCrawler;
    private final Notifier notifier;

    public void execute() throws IOException {
        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("情熱大陸"));

        for (Keyword keyword : keywords) {
            TvPrograms tvPrograms = tvProgramCrawler.crawl(keyword);

            if (!tvPrograms.asList().isEmpty()) {
                notifier.send(new Token("vVzWb3SlL7B83ioerdYdVsLWlK5w47rDXF5M69kt1zq"), "\n" + keyword.getText() + "の番組情報があるよ！");
            }

            tvPrograms.asList().forEach(tvProgram -> {
                notifier.send(new Token("vVzWb3SlL7B83ioerdYdVsLWlK5w47rDXF5M69kt1zq"), tvProgram.display());
            });
        }
    }
}
