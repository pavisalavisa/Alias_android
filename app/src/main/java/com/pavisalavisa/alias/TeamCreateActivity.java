package com.pavisalavisa.alias;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TeamCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        Intent intent=getIntent();
    }

    public void addNewTeam(View view){
        //TODO: dodati funkcionalnost timovac
        String name="aaa";
        DialogFragment dialog=new NewTeamFragment();
        dialog.show(getFragmentManager(),"newTeamDialog");
        //TableLayout table=(TableLayout)findViewById(R.id.players_table);
        //TableRow tr=new TableRow(this);
        //addItemsToRow(tr,name);
        //table.addView(tr);
    }


    private void addItemsToRow(TableRow tr, String item){
        TextView tw=new TextView(this);
        tw.setText(item);
        tr.addView(tw);
        TextView tw2=new TextView(this);
        TextView tw3=new TextView(this);
        tr.addView(tw2);
        tr.addView(tw3);


    }
}
