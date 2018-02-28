package com.cartoon.tinytips.DbUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 许锦鹏 on 2018/2/23.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys='ON';");
        }
    }

    private static final String CREATE_PERSONALINFORMATION="create table PersonalInformation("
            +"headPortrait BLOB,"
            +"nickName TEXT PRIMARY KEY NOT NULL UNIQUE,"
            +"account TEXT NOT NULL UNIQUE,"
            +"school TEXT,"
            +"sex TEXT,"
            +"resume TEXT,"
            +"signature TEXT);";

    //应该是这里出错
    private static final String CREATE_NOTE="create table Note("
            +"title TEXT,"
            +"date TEXT,"
            /*+"FOREIGN KEY(author) REFERENCES PersonalInformation(nickName),"
            +"ON DELETE CASCADE"
            +"ON UPDATE CASCADE,"*/
            +"imageDetails BLOB,"
            +"details BLOB,"
            +"classify TEXT,"
            +"isCollect INTEGER);";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTE);
        sqLiteDatabase.execSQL(CREATE_PERSONALINFORMATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
