package com.learningbee.quizbee;

public class Question {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswerFootnote() {
        return answerFootnote;
    }

    public void setAnswerFootnote(String answerFootnote) {
        this.answerFootnote = answerFootnote;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    private int id;
    private String text;
    private String difficulty;
    private int correctOption;
    private String hint;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answerFootnote;
    private String ref;

    public Question() {}

    public Question(int id, String text, String difficulty, int correctOption, String hint, String option1, String option2, String option3, String option4, String answerFootnote, String ref) {
        this.id = id;
        this.text = text;
        this.difficulty = difficulty;
        this.correctOption = correctOption;
        this.hint = hint;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerFootnote = answerFootnote;
        this.ref = ref;
    }
}
