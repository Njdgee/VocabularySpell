package com.njdge;

import com.njdge.stack.Stack;
import com.njdge.word.Word;
import com.njdge.word.WordTags;

import java.util.Scanner;

import static com.njdge.stack.Stack.loadWords;

public class Game {
    public Game() {

    }
public void start(Stack stack) {
    while (stack.getWords() != null) {
        startTask(false, stack);
        if (stack.getRandomCard(true) == null) {
            System.out.println("Congratulations! You have finished all the words!");
            break;
        } else {
            // Check if all words are not NOT_USED
            boolean allWordsUsed = stack.getWords().stream()
                .noneMatch(word -> word.getTag().equals(WordTags.NOT_USED));
            if (allWordsUsed) {
                System.out.println("Incorrect words review:");
                startTask(true, stack);
            }
        }
    }
    System.out.println("No words to review!");
}

    public void startTask(boolean isIncorrectCard, Stack stack) {
        Scanner scanner = new Scanner(System.in);
        Word word = stack.getRandomCard(isIncorrectCard);

        System.out.println(Util.getHint(word.getEn()) + " | " + word.getZh());
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(word.getEn())) {
            System.out.println("Correct!");
            word.setTag(WordTags.CORRECT);
        } else {
            System.out.println(word.getEn() + " You are wrong!");
            word.setTag(WordTags.INCORRECT);
        }
    }
}
