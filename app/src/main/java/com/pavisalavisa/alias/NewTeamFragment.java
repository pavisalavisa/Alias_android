package com.pavisalavisa.alias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

        wireUpButtons();

        return builder.create();
    }



    private void wireUpButtons()
    {
        Button cancelButton = (Button) dialogFragmentView.findViewById(R.id.cancel_adding_team_button);
        Button okButton = (Button) dialogFragmentView.findViewById(R.id.confirm_adding_team_button);
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
                    return;
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
