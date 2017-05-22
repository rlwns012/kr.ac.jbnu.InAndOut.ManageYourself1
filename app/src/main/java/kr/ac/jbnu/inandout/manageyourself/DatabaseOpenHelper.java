package kr.ac.jbnu.inandout.manageyourself;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    // tasks Table Columns names
    private static final String KEY_IDX = "IDX";
    private static final String KEY_ID = "id";

    //diary Columns
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";
    private static final String KEY_DATE = "date";
    private static final String KEY_DAYQUES = "dayques";

    //sowt Columns
    private static final String KEY_STRENGTH = "strength";
    private static final String KEY_OPPORTUNITY = "opportunity";
    private static final String KEY_WEAKNESS = "weakness";
    private static final String KEY_TREAT = "treat";
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
                + KEY_DAYQUES + " TEXT)";

        database.execSQL(diarysql);

        String swotsql = "CREATE TABLE IF NOT EXISTS " + TABLE_SWOT + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ID
                + " TEXT, " + KEY_TITLE + " TEXT, " + KEY_SO + " TEXT, " + KEY_ST + " TEXT, "
                + KEY_WO + " TEXT, " + KEY_WT + " TEXT, " + KEY_STRENGTH + " TEXT, " + KEY_OPPORTUNITY
                + " TEXT, " + KEY_WEAKNESS + " TEXT, " + KEY_TREAT + " TEXT)";

        database.execSQL(swotsql);

        String pyramidsql = "CREATE TABLE IF NOT EXISTS " + TABLE_PYRAMID + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ID
                + " TEXT, " + KEY_MISSION + " TEXT, " + KEY_VISION + " TEXT, " + KEY_TACTIC + " TEXT, "
                + KEY_ACTIONTASK + " TEXT, " + KEY_ACTIONPLAN + " TEXT)";

        database.execSQL(pyramidsql);

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

    public void creatDiary(String id, String title, String question, String body, String date) {
        //새로운 다이어리 작성
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_TITLE, title);
        values.put(KEY_BODY, body);
        values.put(KEY_DATE, date);
        values.put(KEY_DAYQUES, question);
        database.insert(TABLE_DIARY, null, values);

    }

    public void readDiary(String id) {
        database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DIARY + " where id='" + id + "'" ; // 아이디만 생각하는게 아니라 몇번째 게시물인지도 파악해야함
        Cursor cursor = database.rawQuery(selectQuery,null);    // dayCount를 사용하면 되지 않을까??

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    cursor.getString(2);


                } while (cursor.moveToNext());

            }
        }
    }

    public void updateDiary() {

    }

    public void deleteDiary() {

    }

    public void creatSWOT(String id, String SO, String ST, String WO, String WT, String oppotunity,
                          String weakness, String strength, String treat) {
        // 새로운 SWOT분석 작성
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_SO, SO);
        values.put(KEY_ST, ST);
        values.put(KEY_WO, WO);
        values.put(KEY_WT, WT);
        values.put(KEY_STRENGTH, strength);
        values.put(KEY_OPPORTUNITY, oppotunity);
        values.put(KEY_TREAT, treat);
        values.put(KEY_WEAKNESS, weakness);
        database.insert(TABLE_SWOT, null, values);

    }

    public void readSWOT() {

    }

    public void updateSWOT() {

    }

    public void deleteSWOT() {

    }

    public void creatPyramid(String id, String mission, String vision, String tactic,
                             String actionplan, String actiontask) {

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_MISSION,mission);
        values.put(KEY_VISION, vision);
        values.put(KEY_TACTIC, tactic);
        values.put(KEY_ACTIONPLAN, actionplan);
        values.put(KEY_ACTIONTASK, actiontask);
        database.insert(TABLE_PYRAMID, null, values);

    }

    public void readPyramid() {

    }

    public void updatePyramid() {

    }

}
