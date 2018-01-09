package com.pavisalavisa.alias;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by krist on 5.1.2018..
 */

public class AliasDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="alias";
    private static final int DB_VERSION=1;

    AliasDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }

    private static void insertItem(SQLiteDatabase db, String item){
        ContentValues itemValues=new ContentValues();
        itemValues.put("WORD",item);
        db.insert("ITEMS",null,itemValues);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("CREATE TABLE ITEMS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "WORD TEXT);");

    }
}
