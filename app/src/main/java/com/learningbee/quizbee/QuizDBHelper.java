package com.learningbee.quizbee;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.learningbee.quizbee.QuizSet.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizBeeDB.db";
    private static final int DATABASE_VERSION = 1;
    private String DATABASE_PATH;
    private SQLiteDatabase quizdb;
    private InputStream inputStream;
    Context context;

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.inputStream = inputStream;
        this.context = context;
        this.DATABASE_PATH = "/data/data/" + "com.learningbee.quizbee" + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.quizdb = db;
    }

    private void addQuestions(Question question){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionTable.COLUMN_TEXT, question.getText());
        contentValues.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        contentValues.put(QuestionTable.COLUMN_CORRECT, question.getCorrectOption());
        contentValues.put(QuestionTable.COLUMN_HINT, question.getHint());
        contentValues.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        contentValues.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        contentValues.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        contentValues.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        contentValues.put(QuestionTable.COLUMN_FOOT, question.getAnswerFootnote());
        contentValues.put(QuestionTable.COLUMN_REFERENCE, question.getRef());
        quizdb.insert(QuestionTable.TABLE_NAME, null, contentValues);
    }

/*    public void ScoreCreation(){
        final String SQL_CREATE_SCORE_TABLE = "CREATE TABLE [IF NOT EXISTS] " +
                ScoreTable.TABLE_NAME + " ( " +
                ScoreTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScoreTable.COLUMN_SCORE + " INTEGER " +
                ");";
        quizdb.execSQL(SQL_CREATE_SCORE_TABLE);
    }*/

    public void addScores(Score score){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ScoreTable.COLUMN_SCORE, score.getScore());

        quizdb.insert(ScoreTable.TABLE_NAME, null, contentValues);
    }

    public int getHighScore() {
        int score = 0;
        quizdb = getReadableDatabase();
        Cursor cursor = quizdb.rawQuery("SELECT * FROM " + ScoreTable.TABLE_NAME + " ORDER BY " + ScoreTable.COLUMN_SCORE + " DESC", null);

        if(cursor.moveToFirst()) {
            score = cursor.getInt(cursor.getColumnIndex(ScoreTable.COLUMN_SCORE));
        }
        cursor.close();
        return score;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME;

        db.execSQL(SQL_DROP_TABLE);*/
        onCreate(db);
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        quizdb = getReadableDatabase();
        Cursor cursor = quizdb.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if(cursor.moveToFirst()) {
          do {
            Question question = new Question();
            question.setText(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_TEXT)));
            question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
            question.setCorrectOption(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CORRECT)));
            question.setHint(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_HINT)));
            question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
            question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
            question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
            question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
            question.setAnswerFootnote(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_FOOT)));
            question.setRef(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_REFERENCE)));
            questions.add(question);
          } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

    public List<Question> getAllEasyQuestions() {
        List<Question> questions = new ArrayList<>();
        quizdb = getReadableDatabase();
        Cursor cursor = quizdb.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.COLUMN_DIFFICULTY + "= 'EASY'", null);

        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setText(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_TEXT)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCorrectOption(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CORRECT)));
                question.setHint(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_HINT)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerFootnote(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_FOOT)));
                question.setRef(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_REFERENCE)));
                questions.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

    public List<Question> getAllMediumQuestions() {
        List<Question> questions = new ArrayList<>();
        quizdb = getReadableDatabase();
        Cursor cursor = quizdb.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.COLUMN_DIFFICULTY + "= 'MEDIUM'", null);

        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setText(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_TEXT)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCorrectOption(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CORRECT)));
                question.setHint(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_HINT)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerFootnote(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_FOOT)));
                question.setRef(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_REFERENCE)));
                questions.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

    public List<Question> getAllHardQuestions() {
        List<Question> questions = new ArrayList<>();
        quizdb = getReadableDatabase();
        Cursor cursor = quizdb.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.COLUMN_DIFFICULTY + "= 'HARD'", null);

        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setText(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_TEXT)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCorrectOption(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CORRECT)));
                question.setHint(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_HINT)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerFootnote(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_FOOT)));
                question.setRef(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_REFERENCE)));
                questions.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

    public void CheckDatabaseExistance(){
        SQLiteDatabase checkDatabase = null;
        String filePath = DATABASE_PATH + DATABASE_NAME;
        try{
            checkDatabase = SQLiteDatabase.openDatabase(filePath,null,0);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (checkDatabase != null){
            //make Toast String
        }
        else {
            CopyDatabase();
        }
    }

    private void CopyDatabase() {
        this.getReadableDatabase();

        try{
            InputStream externalInputStream = this.context.getAssets().open(DATABASE_NAME);
            OutputStream outputDatabae = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);

            byte[] buffer = new byte[1024];

            int len;
            while ((len = externalInputStream.read(buffer)) > 0){
                outputDatabae.write(buffer,0,len);
            }
            outputDatabae.flush();
            externalInputStream.close();
            outputDatabae.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("CopyDatabase", "Database Copied");
    }

    public void OpenDatabase(){
        String filePath = DATABASE_PATH + DATABASE_NAME;
        this.quizdb = SQLiteDatabase.openDatabase(filePath,null,0);
    }
}
