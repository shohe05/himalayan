package com.shotana.himalayan.domain;

import com.shotana.himalayan.domain.model.Keyword;
import com.shotana.himalayan.domain.model.TvPrograms;

import java.io.IOException;

public interface TvProgramCrawler {
    /**
     * キーワードに合致するテレビ番組情報を取得する
     *
     * @param keyword 検索キーワード
     * @return 番組情報のリスト
     */
    TvPrograms crawl(Keyword keyword) throws IOException;
}
