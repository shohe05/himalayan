package com.shotana.himalayan.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor
public class TvProgram {

    /**
     * 番組タイトル
     */
    private final String title;

    /**
     * 番組説明
     */
    private final String description;

    /**
     * 放送開始日時
     */
    private final LocalDateTime startAt;

    /**
     * 放送終了日時
     */
    private final LocalDateTime endAt;

    /**
     * テレビ局
     */
    private final String station;

    /**
     * 番組詳細へのリンク
     */
    private final String detailUrl;

    public String display() {
        return "\n"
                + "【タイトル】" + title + "\n"
                + "【放送日時】"
                + startAt.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"))
                + "〜"
                + endAt.format(DateTimeFormatter.ofPattern("HH:mm")) + "\n"
                + "【説明】" + "\n" + description + "\n"
                + "【リンク】" + detailUrl
                ;
    }
}
