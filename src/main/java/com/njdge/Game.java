package com.njdge;

import java.util.Scanner;

public class Game {
    public Game() {
        Word.loadWords();
    }
    public void start() {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Scanner scanner = new Scanner(System.in);
            Word word = Word.getRandomCard();
            if (word == null) {
                System.out.println("No more words to guess.");
                break;
            }
            System.out.println(Util.getHint(word.getEn()) + " | " +word.getZh());
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(word.getEn())) {
                System.out.println("Correct!");
                word.setCorrect(true);
            } else {
                System.out.println(word.getEn() + " You are wrong!");
            }
        }

    }
}
