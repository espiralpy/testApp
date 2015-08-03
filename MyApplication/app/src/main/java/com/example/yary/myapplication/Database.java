/**
 * Created by YARY on 01/08/2015.
 */

package com.example.yary.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE IF NOT EXISTS User (code INTEGER, user TEXT, pass TEXT)";
    String user_column, pass_column;
    SQLiteDatabase db;

    public Database(Context contexto, String nombre,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table User by first time.
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL(sqlCreate);
    }

    public boolean insertUser() {
        //Insert default user.
       db = this.getWritableDatabase();
        if (db != null) {
            int code = 1;
            String user = "admin";
            String pass = "admin";
            db.execSQL("INSERT INTO User (code, user, pass) " +
                    "VALUES (" + code + ", '" + user + "', '" + pass + "')");
            db.close();
            return true;
        } else {
            return false;
        }
    }

    public boolean read(String user, String pass) {
        //Get query from database
        db = this.getReadableDatabase();
        String selectQuery = "SELECT user, pass FROM User WHERE user=? and pass=?";
        Cursor c = db.rawQuery(selectQuery, new String[]{user, pass});

        if (c.moveToFirst()) {
            user_column = c.getString(c.getColumnIndex("user"));
            pass_column = c.getString(c.getColumnIndex("pass"));
        }
        //Get positive number of rows to know if exist user.
        int result = c.getCount();

        if (result >= 1){
            return true;
        }else{
            return false;
        }
    }


}
