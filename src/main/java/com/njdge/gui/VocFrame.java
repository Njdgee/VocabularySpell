package com.njdge.gui;

import com.njdge.stack.Stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VocFrame extends JFrame implements ActionListener {
    JButton startButton;
    JButton submitButton;
    JComboBox comboBox;
    JTextField inputField;
    String[] stackNames;
    Thread gameThread;
    public JLabel question;
    public static VocFrame instance;

    public VocFrame() {
        instance = this;
        setTitle("Vocabulary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 0, 0));

        Stack.loadStacks();
        stackNames = Stack.stacks.keySet().toArray(new String[0]);

        // select game panel
        {
            JPanel selectGamePanel = new JPanel();
            selectGamePanel.setLayout(new FlowLayout());

            comboBox = new JComboBox(stackNames);
            comboBox.addActionListener(this);
            comboBox.setPreferredSize(new Dimension(200, 30));
            comboBox.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));

            startButton = new JButton("Start");
            startButton.addActionListener(this);
            startButton.setPreferredSize(new Dimension(80, 30));
            startButton.setFocusable(false);
            startButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));

            selectGamePanel.add(comboBox);
            selectGamePanel.add(startButton);

            add(selectGamePanel);
        }

        question = new JLabel("(Select a game first)");
        question.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        question.setHorizontalTextPosition(JLabel.CENTER);
        question.setHorizontalAlignment(JLabel.CENTER);
        question.setVisible(true);
        add(question);

        // input panel
        {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());

            inputField = new JTextField();
            inputField.addActionListener(this);
            inputField.setPreferredSize(new Dimension(200, 30));
            inputField.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));

            submitButton = new JButton("Submit");
            submitButton.addActionListener(this);
            submitButton.setPreferredSize(new Dimension(80, 30));
            submitButton.setFocusable(false);
            submitButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));

            inputPanel.add(inputField);
            inputPanel.add(submitButton);

            add(inputPanel);
        }


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gameThread != null) {
                    gameThread.interrupt();
                }
            }
        });

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (gameThread != null && gameThread.isAlive()) {
                gameThread.interrupt();
            }
            String name = comboBox.getSelectedItem().toString();
            Stack stack = Stack.getStack(name);
            GUIGame game = new GUIGame();
            gameThread = new Thread(() -> game.start(stack));
            gameThread.start();
        } else if (e.getSource() == submitButton) {
            String answer = inputField.getText();
        }
    }
}