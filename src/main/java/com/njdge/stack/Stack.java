package com.njdge.stack;

import com.njdge.word.Word;
import com.njdge.word.WordTags;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Stack {
    @Getter private static HashMap<String,Stack> stacks = new HashMap<>();
    @Getter private List<Word> words;
    @Getter private String name;
    @Getter private String filename;
    public Stack(String name,List<Word> words) {
        this.words = words;
        this.name = name;
        stacks.put(name,this);
    }
    public Word getRandomCard(boolean isIncorrectCard) {
        if (words.isEmpty()) {
            return null;
        }
        Collections.shuffle(words);
        if (isIncorrectCard) {
            return words.stream().filter(word -> word.getTag().equals(WordTags.INCORRECT)).findFirst().orElse(words.getFirst());
        }else {
            return words.stream().filter(word -> word.getTag().equals(WordTags.NOT_USED)).findFirst().orElse(words.getFirst());
        }
    }

    public static void loadWords(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
    public static List<Word> getWords(String fileName) {
        List<Word> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@");
                if (parts.length == 2) {
                    words.add(new Word(parts[1].trim(), parts[0].trim()));
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return words;
    }
    public static void loadStacks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stacks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@");
                if (parts.length == 2) {
                    List<Word> words;
                    words = getWords(parts[1].trim());
                    new Stack(parts[0].trim(),words);
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public static Stack getStack(String name) {
        return stacks.get(name);
    }
}
