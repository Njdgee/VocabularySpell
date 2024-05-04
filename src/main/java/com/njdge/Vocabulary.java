package com.njdge;

import com.njdge.stack.Stack;
import lombok.Getter;

import java.util.Scanner;

@Getter
public class Vocabulary {
    public static void main(String[] args){
        Stack.loadStacks();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (command.startsWith("start")) {
            String[] parts = command.split("\\s+");
            if (parts.length > 1) {
                String name = parts[1];
                Stack stack = Stack.getStack(name);
                Game game = new Game();
                game.start(stack);
            } else {
                System.out.println("Please specify a name after 'start'.");
            }
        }
    }
}
