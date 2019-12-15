package com.guidi.collegesearch.frontEnd;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;

import java.util.List;


public class CheckboxSpinner extends AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener {
    private List<String> listitems;
    private boolean[] checked;

    public CheckboxSpinner(Context context) {
        super(context);
    }

    public CheckboxSpinner(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public CheckboxSpinner(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public void onClick(DialogInterface dialog, int ans, boolean isChecked) {
        checked[ans] = isChecked;

    }


    @Override
    public void onCancel(DialogInterface dialog) {



    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(
                listitems.toArray(new CharSequence[listitems.size()]), checked, this);
        builder.setPositiveButton("Done",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checked.length; i++){
                            if(checked[i] == true){
                                getThisSpinner().setSelection(i);
                                break;
                            }else {
                                getThisSpinner().setSelection(checked.length-1);
                            }
                        }
                        dialog.cancel();
                    }
                });
        builder.setOnCancelListener(this);
        builder.show();
        return true;
    }

    public void setItems(List<String> items) {
        this.listitems = items;

        checked = new boolean[items.size()];
        for (int i = 0; i < checked.length; i++)
            checked[i] = false;

    }
    public CheckboxSpinner getThisSpinner() {
        return this;
    }

}
