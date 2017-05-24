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
    private static final String KEY_DAYCOUNT = "daycount";
    private static final String KEY_MAXDAY = "maxday";

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
                + KEY_DAYCOUNT + " INT" + KEY_MAXDAY + " INT)";

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
        values.put(KEY_DAYCOUNT, user.getDayCount());
        values.put(KEY_MAXDAY, user.getMaxDay());
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

    public void updateUser(String id, int maxDay) {

        String selectQuery = "UPDATE " + TABLE_USER + " SET " + KEY_MAXDAY + "='"
                + Integer.toString(maxDay) + "' WHERE id='" + id + "'";
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
                    user.setDayCount(cursor.getInt(5));
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


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
