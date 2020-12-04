package com.example.prayfirst;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DisplayCheckboxFragment extends DialogFragment {

    List<String> days;
    List<String> strdays = new ArrayList<>();


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Days");

        builder.setMultiChoiceItems(days.toArray(new String[0]), null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if(isChecked){
                    strdays.add(days.get(which));
                }else {
                    strdays.remove(days.get(which));
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selection = "";
                for (String item : strdays){
                    selection = selection + "\n" + item;
                }
                //TODO: Display in daytextview
//                String dayResult = Toast.makeText(getActivity(), selection ,Toast.LENGTH_SHORT).toString().trim();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
