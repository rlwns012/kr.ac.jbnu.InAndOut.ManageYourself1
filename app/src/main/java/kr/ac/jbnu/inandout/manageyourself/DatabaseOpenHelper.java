package kr.ac.jbnu.inandout.manageyourself;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rlwns on 2017-05-21.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "memory";

    // tasks table name
    private static final String TABLE_DIARY = "diary";
    private static final String TABLE_SWOT = "swot";
    private static final String TABLE_PYRAMID = "pyramid";
    private static final String TABLE_QUESTION = "question";

    // tasks Table Columns names
    private static final String KEY_IDX = "idx";
    private static final String KEY_ID = "id";

    //diary Columns
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";
    private static final String KEY_DATE = "date";
    private static final String KEY_DAYQUES = "dayques";
    private static final String KEY_DAYCOUNT = "daycount";

    //swot Columns
    private static final String KEY_STRENGTH = "strength";
    private static final String KEY_OPPORTUNITY = "opportunity";
    private static final String KEY_WEAKNESS = "weakness";
    private static final String KEY_THREAT = "threat";
    private static final String KEY_SO = "so";
    private static final String KEY_ST = "st";
    private static final String KEY_WO = "wo";
    private static final String KEY_WT = "wt";

    //pyramid Columns
    private static final String KEY_MISSION = "mission";
    private static final String KEY_VISION = "vision";
    private static final String KEY_TACTIC = "tactic";
    private static final String KEY_ACTIONTASK = "actiontask";
    private static final String KEY_ACTIONPLAN = "actionplan";

    private static final String KEY_QUESTION = "question";

    private SQLiteDatabase database;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        String diarysql = "CREATE TABLE IF NOT EXISTS " + TABLE_DIARY + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ID
                + " TEXT, " + KEY_TITLE + " TEXT, " + KEY_BODY + " TEXT, " + KEY_DATE + " TEXT, "
                + KEY_DAYCOUNT + " INT, " + KEY_DAYQUES + " TEXT)";

        database.execSQL(diarysql);

        String swotsql = "CREATE TABLE IF NOT EXISTS " + TABLE_SWOT + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ID
                + " TEXT, " + KEY_TITLE + " TEXT, " + KEY_SO + " TEXT, " + KEY_ST + " TEXT, "
                + KEY_WO + " TEXT, " + KEY_WT + " TEXT, " + KEY_STRENGTH + " TEXT, " + KEY_OPPORTUNITY
                + " TEXT, " + KEY_WEAKNESS + " TEXT, " + KEY_THREAT + " TEXT)";

        database.execSQL(swotsql);

        String pyramidsql = "CREATE TABLE IF NOT EXISTS " + TABLE_PYRAMID + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ID
                + " TEXT, " + KEY_MISSION + " TEXT, " + KEY_VISION + " TEXT, " + KEY_TACTIC + " TEXT, "
                + KEY_ACTIONTASK + " TEXT, " + KEY_ACTIONPLAN + " TEXT)";

        database.execSQL(pyramidsql);

        String questionsql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION + " TEXT)";

        database.execSQL(questionsql);

        addQuestion();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SWOT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PYRAMID);

        // Create tables again
        onCreate(db);

    }

    public void creatDiary(String id, String title, String question, String body, String date, int dayCount) {
        //새로운 다이어리 작성
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_TITLE, title);
        values.put(KEY_BODY, body);
        values.put(KEY_DATE, date);
        values.put(KEY_DAYQUES, question);
        values.put(KEY_DAYCOUNT, dayCount);
        database.insert(TABLE_DIARY, null, values);


    }

    public ArrayList<DiaryContainer> readDiary(String id) {
        // 모든 다이어리의 정보를 가져온다.
        database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DIARY + " where id='" + id + "'"; // 아이디만 생각하는게 아니라 몇번째 게시물인지도 파악해야함
        Cursor cursor = database.rawQuery(selectQuery, null);    // dayCount를 사용하면 되지 않을까??
        ArrayList diaryList = new ArrayList();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    String title = cursor.getString(2);
                    String body = cursor.getString(3);
                    String date = cursor.getString(4);
                    int dayCount = cursor.getInt(5);
                    String daysQues = cursor.getString(6);

                    DiaryContainer diaryContainer = new DiaryContainer(title, body, date, daysQues, dayCount);
                    diaryList.add(diaryContainer);
                } while (cursor.moveToNext());
            }
        }
        return diaryList;
    }

    public DiaryContainer readDiary(String id, int dayCount) {
        DiaryContainer diaryContainer = new DiaryContainer("", "", "", "", 1);
        database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DIARY + " where id='" + id + "', " + KEY_DAYCOUNT + " = '" + String.valueOf(dayCount) + "'"; // 아이디만 생각하는게 아니라 몇번째 게시물인지도 파악해야함
        Cursor cursor = database.rawQuery(selectQuery, null);    // dayCount를 사용하면 되지 않을까??

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    String title = cursor.getString(2);
                    String body = cursor.getString(3);
                    String date = cursor.getString(4);
                    int dayCount1 = cursor.getInt(5);
                    String daysQues = cursor.getString(6);

                    diaryContainer.setBody(body);
                    diaryContainer.setTitle(title);
                    diaryContainer.setDate(date);
                    diaryContainer.setDayCount(dayCount1);
                    diaryContainer.setDayQues(daysQues);

                } while (cursor.moveToNext());
            }
        }
        return diaryContainer;
    }


    public void creatSWOT(String id, String SO, String ST, String WO, String WT, String oppotunity,
                          String weakness, String strength, String treat) {
        // 새로운 SWOT 분석 작성
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_SO, SO);
        values.put(KEY_ST, ST);
        values.put(KEY_WO, WO);
        values.put(KEY_WT, WT);
        values.put(KEY_STRENGTH, strength);
        values.put(KEY_OPPORTUNITY, oppotunity);
        values.put(KEY_THREAT, treat);
        values.put(KEY_WEAKNESS, weakness);
        database.insert(TABLE_SWOT, null, values);
    }

    public ArrayList<SWOTContainer> readSWOT(String id, int idx) {
        database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_SWOT + " where id='" + id + "', "
                + KEY_IDX + "='" + String.valueOf(idx) + "'"; // 아이디만 생각하는게 아니라 몇번째 게시물인지도 파악해야함
        Cursor cursor = database.rawQuery(selectQuery, null);                       // 우선 여기서는 swot의 리스트를 받기 위해 모든 것을 다 가져옴

        ArrayList swotList = new ArrayList();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    // SWOT의 모든 정보를 가지고 온다.
                    String title = cursor.getString(2);
                    String so = cursor.getString(3);
                    String st = cursor.getString(4);
                    String wo = cursor.getString(5);
                    String wt = cursor.getString(6);
                    String strength = cursor.getString(7);
                    String opportunity = cursor.getString(8);
                    String threat = cursor.getString(9);
                    String weakness = cursor.getString(10);

                    SWOTContainer swotContainer = new SWOTContainer(idx, title, strength, weakness,
                            opportunity, threat, so, st, wt, wo);
                    swotList.add(swotContainer);
                } while (cursor.moveToNext());
            }
        }
        return swotList;
    }

    public void updateSWOT(SWOTContainer swotContainer) {
        //SWOT 분석 수정
        String sqlUpdate = "UPDATE " + TABLE_SWOT + " SET " + KEY_OPPORTUNITY + "='" + swotContainer.getOpportunity() + "', " +
                KEY_SO + "='" + swotContainer.getSo() + "', " +
                KEY_ST + "='" + swotContainer.getSt() + "', " +
                KEY_WO + "='" + swotContainer.getWo() + "', " +
                KEY_WT + "='" + swotContainer.getWt() + "', " +
                KEY_THREAT + "='" + swotContainer.getThreat() + "', " +
                KEY_TITLE + "='" + swotContainer.getTitle() + "', " +
                KEY_STRENGTH + "='" + swotContainer.getStrength() + "', " +
                KEY_WEAKNESS + "='" + swotContainer.getWeakness() + "', " +
                " WHERE " + KEY_IDX + "='" + String.valueOf(swotContainer.getIdx()) + "'";

        database.execSQL(sqlUpdate);
    }

    public void deleteSWOT(int idx) {
        //SWOT 레코드 지우기 idx값을 이용해서 찾아가 지운다.
        String sqlDelete = "DELETE FROM " + TABLE_SWOT + " WHERE " + KEY_IDX + "='" + String.valueOf(idx) + "'";

        database.execSQL(sqlDelete);

    }

    public void creatPyramid(String id, String mission, String vision, String tactic,
                             String actionplan, String actiontask) {
        // 첫 파리미드 생성
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_MISSION, mission);
        values.put(KEY_VISION, vision);
        values.put(KEY_TACTIC, tactic);
        values.put(KEY_ACTIONPLAN, actionplan);
        values.put(KEY_ACTIONTASK, actiontask);
        database.insert(TABLE_PYRAMID, null, values);

    }

    public PyramidContainer readPyramid(String id) {
        // 피라미드의 정보를 가져온다.
        database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PYRAMID + " where id='" + id + "'"; // 아이디만 생각하는게 아니라 몇번째 게시물인지도 파악해야함
        Cursor cursor = database.rawQuery(selectQuery, null);    // dayCount를 사용하면 되지 않을까??
        PyramidContainer pyramidContainer = new PyramidContainer();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    String mission = cursor.getString(2);
                    String vision = cursor.getString(3);
                    String tactic = cursor.getString(4);
                    String actionTask = cursor.getString(5);
                    String actionPlan = cursor.getString(6);

                    pyramidContainer = new PyramidContainer(mission, vision, tactic, actionTask, actionPlan);
                } while (cursor.moveToNext());
            }
        }
        return pyramidContainer;

    }

    public void updatePyramid(PyramidContainer pyramidContainer, String id) {
        //피라미드의 내용 수정
        String sqlUpdate = "UPDATE " + TABLE_PYRAMID + " SET " + KEY_VISION + "='" + pyramidContainer.getVision() + "', " +
                KEY_ACTIONPLAN + "='" + pyramidContainer.getActionPlan() + "', " +
                KEY_ACTIONTASK + "='" + pyramidContainer.getActionTask() + "', " +
                KEY_MISSION + "='" + pyramidContainer.getMission() + "', " +
                KEY_TACTIC + "='" + pyramidContainer.getTactic() + "', " +
                " WHERE " + KEY_ID + "='" + id + "'";

        database.execSQL(sqlUpdate);

    }

    public void deletePyramid(String id) {
        String sqlDelete = "DELETE FROM " + TABLE_PYRAMID + " WHERE " + KEY_ID + "='" + id + "'";
        database.execSQL(sqlDelete);
    }

    public void addQuestion(String question) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question);
        database.insert(TABLE_QUESTION, null, values);
    }

    public void addQuestion() {
        String q1 = "오늘 날씨는 어때요?";
        addQuestion(q1);
        String q2 = "오늘 하루 어떠셨나요?";
        addQuestion(q2);
        String q3 = "기분 좋은 하루였나요?";
        addQuestion(q3);
        String q4 = "즐거웠던 일을 적어보아요!";
        addQuestion(q4);
        String q5 = "술이 생각나는 하루네요.";
        addQuestion(q5);
        String q6 = "오늘 하루 힘들지는 안으셨어요?";
        addQuestion(q6);
        String q7 = "지친 하루 다이어리에 털어놔 봐요";
        addQuestion(q7);
        String q8 = "우리 오늘 어땠는지 생각해봐요!";
        addQuestion(q8);
        String q9 = "재미있는 일 있으셨나요?";
        addQuestion(q9);
        String q10 = "즐거웠던 일 하나만 저랑 얘기해봐요!";
        addQuestion(q10);
    }

    public String readQuestion() {
        database = this.getReadableDatabase();
        Random rand = new Random();
        int idx = rand.nextInt(10);
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION + " where " + KEY_IDX + "='" + idx + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String question = cursor.getString(1).toString();
                    return question;
                } while (cursor.moveToNext());
            }
        }
        return null;
    }

}
