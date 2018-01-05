package com.pavisalavisa.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startClicked(View view){
        Intent intent=new Intent(this,TeamCreateActivity.class);
        startActivity(intent);
    }
    public void newWordClicked(View view){
        Intent intent=new Intent(MainActivity.this,NewWordActivity.class);
        startActivity(intent);
    }
    public void optionsClicked(View view){
        Intent intent=new Intent(MainActivity.this,OptionsActivity.class);
        startActivity(intent);
    }


}
