package com.njdge.word;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Word {
    @Getter private static List<Word> words = new ArrayList<>();
    @Getter private String zh;
    @Getter private String en;
    @Getter @Setter private WordTags tag = WordTags.NOT_USED;
    public Word(String zh, String en){
        this.zh = zh;
        this.en = en;
        words.add(this);
    }

}
