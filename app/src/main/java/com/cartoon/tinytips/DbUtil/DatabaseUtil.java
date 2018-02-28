package com.cartoon.tinytips.DbUtil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.cartoon.tinytips.data.TableNote.Note;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by 许锦鹏 on 2018/2/23.
 */

public class DatabaseUtil {
    private MyDatabaseHelper databaseHelper;
    public static void insert(Note note, SQLiteDatabase db){
        ContentValues values=new ContentValues();
        values.put("title",note.getTitle());
        values.put("date",note.getDate());
        values.put("author",note.getAuthor());
        values.put("imageDetails",note.getImageDetails());
        values.put("details",note.getDetails());
        values.put("classify",note.getClassify()[0]+","+note.getClassify()[1]+","+note.getClassify()[2]);
        values.put("isCollect",note.isCollect());
        db.insert("Note",null,values);

    }
    public static List<Note> queryNote(SQLiteDatabase db,String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit){
        Cursor cursor=db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy,limit);
        Note note;
        List<Note> noteList = new ArrayList<>();
        Boolean isCollect;
        if (cursor.moveToFirst()){
            do {
                if (cursor.getInt(cursor.getColumnIndex("isCollect"))==0){
                    isCollect=false;
                }
                else {
                    isCollect=true;
                }
                note=new Note(cursor.getString(cursor.getColumnIndex("title")),
                              cursor.getString(cursor.getColumnIndex("date")),
                              cursor.getString(cursor.getColumnIndex("nickName")),
                              cursor.getString(cursor.getColumnIndex("ImageDetails")),
                              cursor.getString(cursor.getColumnIndex("details")),
                              cursor.getString(cursor.getColumnIndex("classify")).split(","),
                              isCollect);
                noteList.add(note);
            }while(cursor.moveToNext());
        }


        return noteList;
    }
}
