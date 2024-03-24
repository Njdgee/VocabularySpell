package com.njdge;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word {
    @Getter
    private static List<Word> words = new ArrayList<>();
    @Getter
    private String zh;
    @Getter
    private String en;
    @Getter
    @Setter
    private boolean Correct = false;
    @Getter
    @Setter
    private boolean isUsed = false;
    public Word(String zh, String en){
        this.zh = zh;
        this.en = en;
        words.add(this);
    }
    public static Word getRandomCard() {
        Collections.shuffle(words);
        Word randomWord = words.stream().filter(word -> !word.isUsed && !word.isCorrect()).findFirst().orElse(null);
        if (randomWord != null) {
            randomWord.setUsed(true);
        }
        return randomWord;
    }
    public static void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                 String[] parts = line.split("@");
                if (parts.length == 2) {
                    new Word(parts[1].trim(), parts[0].trim());
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
