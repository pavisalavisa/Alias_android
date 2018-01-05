package com.pavisalavisa.alias;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
    }

    public void addNewWord(View view){
        TextView tw=(TextView)findViewById(R.id.new_word_text_edit);
        String word=tw.getText().toString();
        if(word.length()<2){
            Toast toast= Toast.makeText(this,"Word should be at least 3 characters long",Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        new AddNewWordTask().execute(word);
    }


    private class AddNewWordTask extends AsyncTask<String,Void,Boolean> {

        ContentValues itemValues;

        @Override
        protected Boolean doInBackground(String... params) {
            SQLiteOpenHelper dbHelper=new AliasDatabaseHelper(NewWordActivity.this);
            try{
                itemValues=new ContentValues();
                itemValues.put("WORD",params[0]);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.insert("ITEMS",null,itemValues);
                db.close();
                return true;
            }catch(SQLiteException e){
                return false;
            }
        }

        protected void onPostExecute(Boolean success){
            if(!success){
                Toast toast=Toast.makeText(NewWordActivity.this,"Database unavailable",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
