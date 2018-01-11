package com.pavisalavisa.alias;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by krist on 5.1.2018..
 */

public class AliasDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME="alias_v2";
    private static final int DB_VERSION=2;
    private static final String TEXT_FILE_DIR="HeadFirstAndroid.txt";
    AliasDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context=context;
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
        if(oldVersion<1){
                db.execSQL("CREATE TABLE ITEMS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "WORD TEXT);");

        }if(oldVersion<2){
            new prePopulateDatabaseTask().execute();
        }
    }

    public int getRandomRow(){
        //Returns pseudorandom integer from 1 to noOfRows(inclusive)
        int noOfRows=(int)DatabaseUtils.queryNumEntries(this.getReadableDatabase(),"ITEMS");
        return new Random().nextInt(noOfRows)+1;
    }

    private void populateDatabaseFromTextFile()throws IOException {
        String buffer;
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(context.getAssets().open(TEXT_FILE_DIR)));
        while((buffer=bufferedReader.readLine())!=null) {
            addFromBuffer(buffer);
        }
        bufferedReader.close();
    }

    private void addFromBuffer(String buffer){
        String[] strings=buffer.split(" ");
        for (String s:strings) {
            if(s.length()>3){
                insertItem(this.getWritableDatabase(),s);
            }
        }
    }
    private class prePopulateDatabaseTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                AliasDatabaseHelper.this.getWritableDatabase();
                    try {
                        populateDatabaseFromTextFile();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
            }catch(SQLiteException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
