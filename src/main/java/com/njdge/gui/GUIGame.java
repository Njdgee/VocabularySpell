package com.njdge.gui;

import com.njdge.Util;
import com.njdge.stack.Stack;
import com.njdge.word.Word;
import com.njdge.word.WordTags;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.lang.constant.ModuleDesc;
import java.util.Scanner;


public class GUIGame {

    public GUIGame() {

    }
    String wrong = "/wrong.wav";
    String right = "/right.wav";
    /*Media rightSound = new Media(right);
    MediaPlayer rightPlayer = new MediaPlayer(rightSound);
    Media wrongSound = new Media(wrong);
    MediaPlayer wrongPlayer = new MediaPlayer(wrongSound);*/
    public void start(Stack stack) {

        while (stack.getWords() != null) {
            startTask(false, stack);
            if (stack.getRandomCard(true) == null) {
                VocFrame.instance.question.setText("No words to review!");
                break;
            } else {
                // Check if all words are not NOT_USED
                boolean allWordsUsed = stack.getWords().stream()
                        .noneMatch(word -> word.getTag().equals(WordTags.NOT_USED));
                if (allWordsUsed) {
                    VocFrame.instance.question.setText("Incorrect words review:");
                    startTask(true, stack);
                }
            }
        }
        VocFrame.instance.question.setText("No words to review!");
    }

    public void startTask(boolean isIncorrectCard, Stack stack) {
        Scanner scanner = new Scanner(System.in);
        Word word = stack.getRandomCard(isIncorrectCard);

        VocFrame.instance.question.setText(Util.getHint(word.getEn()) + " | " + word.getZh());
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(word.getEn())) {
            System.out.println("Correct!");
            playSound(right);
            word.setTag(WordTags.CORRECT);
        } else {
            System.out.println(word.getEn() + " You are wrong!");
            playSound(wrong);
            word.setTag(WordTags.INCORRECT);
        }
    }
    public static synchronized void playSound(final String sound) {

        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        GUIGame.class.getResourceAsStream(sound));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }
}
