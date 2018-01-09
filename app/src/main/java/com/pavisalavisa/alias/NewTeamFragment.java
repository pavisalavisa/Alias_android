package com.pavisalavisa.alias;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewTeamFragment extends DialogFragment{
    private View dialogFragmentView;
    private String teamName;
    private String playerOne;
    private String playerTwo;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Context context=builder.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        dialogFragmentView = inflater.inflate(R.layout.fragment_new_team, null);
        builder.setView(dialogFragmentView);

        wireUpButtons(dialogFragmentView);

        return builder.create();
    }



    private void wireUpButtons(final View view)
    {
        Button cancelButton = (Button) view.findViewById(R.id.cancel_adding_team_button);
        Button okButton = (Button) view.findViewById(R.id.confirm_adding_team_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                NewTeamFragment.this.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getTeamInputInfo();
                if(!Game.getCurrentGame().addTeam(teamName,playerOne,playerTwo)){
                    Toast toast= Toast.makeText(v.getContext(),"Don't leave out anything",Toast.LENGTH_SHORT);
                    toast.show();
                }
                NewTeamFragment.this.dismiss();
                notifyParentActivity();

            }
        });
    }


    private void getTeamInputInfo()
    {
        teamName=((EditText)dialogFragmentView.findViewById(R.id.team_name_edit_text)).getText().toString();
        playerOne=((EditText)dialogFragmentView.findViewById(R.id.player_one_edit_text)).getText().toString();
        playerTwo=((EditText)dialogFragmentView.findViewById(R.id.player_two_edit_text)).getText().toString();
    }

    private void notifyParentActivity(){
        ((TeamCreateActivity)this.getActivity()).addNewRow();
    }


}
