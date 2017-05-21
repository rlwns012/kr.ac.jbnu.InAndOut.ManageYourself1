package kr.ac.jbnu.inandout.manageyourself;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlwns on 2017-04-05.
 */

public class UserDBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "user";
    // tasks table name
    private static final String TABLE_USER = "user";
    // tasks Table Columns names
    private static final String KEY_IDX = "IDX";
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_BIRTH = "birth";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "socre";
    private static final String KEY_TIMEPERGAME = "timepergame";
    private static final String KEY_PLAYTIME = "playtime";
    private static final String KEY_PLAYCOUNT = "playcount";

    private SQLiteDatabase database;

    public UserDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( "
                + KEY_IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ID
                + " TEXT, " + KEY_PASSWORD + " TEXT, " + KEY_NAME + " TEXT, " + KEY_BIRTH + " TEXT, "
                + KEY_SCORE + " INT, " + KEY_TIMEPERGAME + " INT, " + KEY_PLAYTIME + " INT, " + KEY_PLAYCOUNT + " INT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);

    }

    public void addUser(User user) {

        // 회원으로 작성한 정보를 DB에 등록
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_BIRTH, user.getbirth());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SCORE, user.getScore());
        values.put(KEY_PLAYTIME, user.getPlayTime());
        values.put(KEY_TIMEPERGAME, user.getTimePerGame());
        values.put(KEY_PLAYCOUNT, user.getPlayCount());
        database.insert(TABLE_USER, null, values);

    }

    public boolean checkIdDuplication(String id) {

        // 회원가입 작성시에 중복된 아이디가 있는지 확인
        String selectQuery = "SELECT * FROM " + TABLE_USER + " where id='" + id + "'";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {        // 등록된 정보가 없다면 바로 사용가능
            if (cursor.moveToFirst()) {
                String storedID = cursor.getString(1);
                if (id.equals(storedID)) {  // 중복된 아아디가 있다면 true를 리턴해 사용 불가하게 함
                    return true;
                } else {
                    return false;           // 중복된 아이디가 없다면 false
                }
            }
        }
        return false;
    }

    public void updateUser(String id, int score, int playCount, int playTime, int timePerGame) {

        // 게임이 끝난 후 점수,플레이 시간, 플레이 횟수 등을 갱신 해준다.
        String selectQuery = "UPDATE " + TABLE_USER + " SET " + KEY_SCORE + "='"
                + Integer.toString(score) + "', " + KEY_TIMEPERGAME + "='" + Integer.toString(timePerGame) + "', " + KEY_PLAYCOUNT + "='" + Integer.toString(playCount)
                + "', " + KEY_PLAYTIME + "='" + Integer.toString(playTime) + "' WHERE id='" + id + "'";
        database = this.getWritableDatabase();
        database.execSQL(selectQuery);
    }

    public boolean checkUser(String id, String password, User user) {
        // 로그인시에 회원 등록이 되어있는지 확인한다.
        String selectQuery = "SELECT * FROM " + TABLE_USER + " where id='" + id + "'";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {            // 회원정보가 있는지 판단
            if (cursor.moveToFirst()) {
                String storedID = cursor.getString(1);
                String storedPass = cursor.getString(2);
                if (id.equals(storedID) && password.equals(storedPass)) { // 아이디와 패스워드가 일치하면
                    user.setId(storedID);                                 // 유저의 정보를 가져와 준다.
                    user.setPassword(storedPass);
                    user.setName(cursor.getString(3));
                    user.setbirth(cursor.getString(4));
                    user.setScore(cursor.getInt(5));
                    user.setTimePerGame(cursor.getInt(6));
                    user.setPlayTime(cursor.getInt(7));
                    user.setPlayCount(cursor.getInt(8));
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

   /* public ArrayList<RankInfoContainer> getScoreRanking() {
        // DB의 점수와 게임당 시간의 순위를 가져온다.
        String selectQuery = "SELECT * FROM " + TABLE_USER + " ORDER BY "
                + KEY_SCORE + " DESC ," + KEY_TIMEPERGAME + " LIMIT 10";
        // 점수는 내림차순, 게임당 시간은 오름차순 10개를 가지고 온다.
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // 랭크의 정보를 담을 ArrayList 선언
        ArrayList<RankInfoContainer> rankInfoList = new ArrayList<RankInfoContainer>();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    // 랭크 정보를 생성
                    RankInfoContainer ric = new RankInfoContainer(cursor.getString(1),
                            cursor.getInt(5), cursor.getInt(6), 0, 0); // id, score, timePerGame을 입력한다.
                    rankInfoList.add(ric);  // ArrayList에 넣어준다.

                } while (cursor.moveToNext());

            }
        }
        return rankInfoList; // 정보를 넘겨줌
    }

    public ArrayList<RankInfoContainer> getPlayTimeRanking() {
        String selectQuery = "SELECT * FROM " + TABLE_USER + " ORDER BY " + KEY_PLAYTIME + " DESC LIMIT 10";
        // 플레이 타임 하나의 변수만 내림차순으로 가져온다.
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        ArrayList<RankInfoContainer> rankInfoList = new ArrayList<RankInfoContainer>();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    RankInfoContainer ric = new RankInfoContainer(cursor.getString(1),
                            0, 0, 0, cursor.getInt(7));  // id, playTime 값 입력
                    rankInfoList.add(ric);

                } while (cursor.moveToNext());

            }
        }
        return rankInfoList;
    }

    public ArrayList<RankInfoContainer> getPlayCountRanking() {
        String selectQuery = "SELECT * FROM " + TABLE_USER + " ORDER BY "
                + KEY_PLAYCOUNT + " DESC LIMIT 10";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        ArrayList<RankInfoContainer> rankInfoList = new ArrayList<RankInfoContainer>();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    RankInfoContainer ric = new RankInfoContainer(cursor.getString(1),
                            0, 0, cursor.getInt(8), 0); // id, playCount 입력
                    rankInfoList.add(ric);

                } while (cursor.moveToNext());

            }
        }
        return rankInfoList;
    }*/

    public ArrayList<String> findId(String name, String birth) {
        database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " where name='" + name + "' AND birth='" + birth + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        ArrayList<String> storeids = new ArrayList<String>();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(1);
                    storeids.add(id);
                } while (cursor.moveToNext());

            }
        }
        return storeids;
    }

    public ArrayList<String> findPs(String id, String name, String birth) {
        database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " where id ='" + id +
                "' And name='" + name + "' AND birth='" + birth + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        ArrayList<String> storepss = new ArrayList<String>();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String ps = cursor.getString(2);
                    storepss.add(ps);
                } while (cursor.moveToNext());

            }
        }
        return storepss;
    }
}
