package com.learningbee.quizbee;

import android.provider.BaseColumns;

public final class QuizSet {

    private QuizSet() {}

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "Questions";
        public static final String COLUMN_TEXT = "qtext";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CORRECT = "correct";
        public static final String COLUMN_HINT = "hint";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_FOOT = "footnote";
        public static final String COLUMN_REFERENCE = "reference";
    }

    public static class ScoreTable implements BaseColumns {
        public static final String TABLE_NAME = "Scores";
        public static final String COLUMN_SCORE = "attemptscore";
    }
}
