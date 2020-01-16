package com.novi.model;

import java.util.*;

public class Game {
    public static Scanner INPUT = new Scanner(System.in);

    private String answer;
    private List<String> listOfAnswers = Arrays.asList("Sport", "Exist", "Amber", "Flame", "Index");

    public Game(int difficulty) {
        // for handling difficulty
        // int range = 5 * (difficulty + 1);
        this.answer = generateAnswer();
    }

    public boolean play(Player player) {
        System.out.println("Guess a 5 letter word");
        System.out.println(answer);

        System.out.print(player.getName() + " > ");

        String playerAnswer = INPUT.nextLine();

        if (checkPlayerAnswer(playerAnswer)) {
            System.out.println("Het antwoord is goed!");
            player.addScore();
            return true;
        } else {
            System.out.println("Antwoord is fout.");

            if (playerAnswer.length() > answer.length()) {
                System.out.println("Answer is longer than " + answer.length() + " characters");
                return false;
            }

            ArrayList<Character> cows = getCowsFromAnswer(playerAnswer);
            System.out.println("Cows: " + cows.size() + " " + cows);

            ArrayList<Character> bulls = getBullsFromAnswer(playerAnswer);
            System.out.println("Bulls: " + bulls.size() + " " + bulls);

            return false;
        }
    }

    private boolean checkPlayerAnswer(String playerAnswer) {
        return answer.equals(playerAnswer);
    }

    private ArrayList<Character> getCowsFromAnswer(String playerAnswer) {
        ArrayList<Character> matches = new ArrayList<>();

        for (int i = 0; i < playerAnswer.length(); i++) {
            if (!stringContinuesWith(playerAnswer, answer, i) && answer.contains(playerAnswer.charAt(i) + "")) {
                matches.add(playerAnswer.charAt(i));
            }
        }

        return matches;
    }

    private ArrayList<Character> getBullsFromAnswer(String playerAnswer) {
        ArrayList<Character> matches = new ArrayList<>();

        for (int i = 0; i < playerAnswer.length(); i++) {
            if (stringContinuesWith(playerAnswer, answer, i)) {
                matches.add(playerAnswer.charAt(i));
            }
        }

        return matches;
    }

    // if string has correct position (bulls)
    private boolean stringContinuesWith(String a, String b, int index) {
        return a.charAt(index) == b.charAt(index);
    }

    private String generateAnswer() {
        // get random key from List
        int rndKey = new Random().nextInt(this.listOfAnswers.size());

        return this.listOfAnswers.get(rndKey);
    }
}