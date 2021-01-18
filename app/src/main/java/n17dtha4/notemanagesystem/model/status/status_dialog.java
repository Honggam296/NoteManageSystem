package n17dtha4.notemanagesystem.model.status;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import n17dtha4.notemanagesystem.R;
import n17dtha4.notemanagesystem.model.category.category_dialog;
import n17dtha4.notemanagesystem.model.priority.priority_dialog;
import n17dtha4.notemanagesystem.model.priority.priority_dialog;

import java.io.Console;
import java.util.Date;

public class status_dialog extends DialogFragment {

    public status_dialog.dialog_Add_Status_Listener dialogAddStatusListener ;
    public  interface  dialog_Add_Status_Listener{
        void addStatus(String status, String date);
        void getData();
    }
    String name  = "-1";
    int keyId  ;
    public status_dialog(String name,int keyId) {
        this.name= name;
        this.keyId = keyId;
    }
    public status_dialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog,null);


        if(name.equals("-1")){
            builder.setView(view)
                    .setTitle("Add Status").setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText txt =  (EditText)view.findViewById(R.id.etStatusDialog);
                    String status="Name: "+txt.getText().toString() ;
                    String date = "Created Day: "+java.text.DateFormat.getDateTimeInstance().format(new Date());
                    dialogAddStatusListener.addStatus(status,date);
                    try {
                        status_db dbHelper = new status_db(status_dialog.this.getContext());
                        dbHelper.addStatus(new StatusViewModel(-1,txt.getText().toString(),java.text.DateFormat.getDateTimeInstance().format(new Date())));
                        dialogAddStatusListener.getData();
                        Toast.makeText(status_dialog.this.getContext(),"Success",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(status_dialog.this.getContext(),"UnSuccess",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            EditText txt =  (EditText)view.findViewById(R.id.etStatusDialog);
            txt.setText(name);
            builder.setView(view)
                    .setTitle("Edit Status").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText txt =  (EditText)view.findViewById(R.id.etStatusDialog);
                    String status="Name: "+txt.getText().toString() ;
                    String date = "Created Day: "+java.text.DateFormat.getDateTimeInstance().format(new Date());
                    dialogAddStatusListener.addStatus(status,date);
                    try {
                        status_db dbHelper = new status_db(status_dialog.this.getContext());
                        dbHelper.updateStatus(new StatusViewModel(keyId,txt.getText().toString(),java.text.DateFormat.getDateTimeInstance().format(new Date())));
                        dialogAddStatusListener.getData();
                        Toast.makeText(status_dialog.this.getContext(),"Success",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(status_dialog.this.getContext(),"UnSuccess",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return builder.create();
    }
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        dialogAddStatusListener = (dialog_Add_Status_Listener) getParentFragment();
    }
}
