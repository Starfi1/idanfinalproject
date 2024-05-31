package com.example.idansubs;

import java.util.Random;

public class User {
    private String name;
    private int score;
    private int randNum;
    public int getRandNum() {
        return randNum;
    }

    public User(String name, int score) {
        this.name = name;
        this.score = score;

        Random r = new Random();
        randNum = r.nextInt(4);
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
