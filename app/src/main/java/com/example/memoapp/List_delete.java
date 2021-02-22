package com.example.memoapp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class List_delete extends LinearLayout implements Checkable {

    public List_delete(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public boolean isChecked() {
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);

        return checkBox.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);

        setChecked(checkBox.isChecked() ? false:true);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);

        if(checkBox.isChecked() != checked){
            checkBox.setChecked(checked);
        }
    }
}
