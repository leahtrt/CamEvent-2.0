package ca.uwaterloo.camevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serena on 2016-10-15.
 */
public class UserDBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "UserInfo";

    //Contacts table name
    private static final String TABLE_USERINFO = "user";

    //shops Table Columns Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_USERINFO + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT" + ");";

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERINFO + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                + KEY_PASSWORD + " TEXT" + ");";
        */
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);

        // Create tables again
        onCreate(db);
    }

    // Add new user
    public void addUserinfo(Userinfo userinfo) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String countQuery = "SELECT * FROM " + TABLE_USERINFO;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        values.put(KEY_ID, count);
        values.put(KEY_NAME, userinfo.getUsername()); // username
        values.put(KEY_PASSWORD, userinfo.getPassword()); // password

        db.insert(TABLE_USERINFO, null, values);
        db.close();
    }

    public String searchPassword(String _username) {
        String res_password, uname;
        res_password = "NOT FOUND, NO SUCH USERNAME";
        db = this.getReadableDatabase();
        String query = "SELECT USERNAME, PASSWORD FROM " + TABLE_USERINFO;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if(uname.equals(_username)) {
                    res_password = cursor.getString(1);
                    break;
                }
            } while(cursor.moveToNext());
        }
        return res_password;
    }

    public void deleteUser(Userinfo userinfo) {
        db = this.getWritableDatabase();
        String sqlStr = "DELETE FROM " + TABLE_USERINFO + " WHERE " + KEY_NAME + " = ?";
        db.execSQL(sqlStr, new String[] {userinfo.getUsername()});
    }

    public List<Userinfo> getAllUserInfos() {
        List<Userinfo> usersInfo = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERINFO, null);
        if(cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
                Userinfo userinfo = new Userinfo(username, password);
                usersInfo.add(userinfo);
            } while(cursor.moveToNext());
        }
        return usersInfo;
    }

    public List<String> getUsernamesList() {
        List<String> usernames = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERINFO, null);
        if(cursor.moveToFirst()) {
            do {
                usernames.add(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            } while(cursor.moveToNext());
        }
        return usernames;
    }

}

