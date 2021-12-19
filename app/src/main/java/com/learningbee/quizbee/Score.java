package com.learningbee.quizbee;

public class Score {
    public Score(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public Score(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int id;
    private int score;

    public Score() {}
}
