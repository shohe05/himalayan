package com.shotana.himalayan.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class TvPrograms {
    private List<TvProgram> tvPrograms = new ArrayList<>();

    public void add(TvProgram tvProgram) {
        tvPrograms.add(tvProgram);
    }

    public List<TvProgram> asList() {
        return Collections.unmodifiableList(tvPrograms);
    }
}
