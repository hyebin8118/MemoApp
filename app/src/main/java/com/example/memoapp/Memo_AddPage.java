package com.example.memoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Memo_AddPage extends AppCompatActivity {

    Helper helper;

    private EditText edit_title_text, edit_body_text;
    private ImageButton btn_listImage;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        final Context context = this;
        edit_title_text = findViewById(R.id.title_text);
        edit_body_text = findViewById(R.id.body_text);
        Button addButton = findViewById(R.id.addButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        btn_listImage = findViewById(R.id.btn_listImage);

        helper = new Helper(this);
        Bundle bundleExtras = getIntent().getExtras();

        if(bundleExtras != null){
            int value = bundleExtras.getInt("id");

            if ( value > 0 ) {
                Cursor cursor = helper.getData(value);

                id = value;

                cursor.moveToFirst();

                String title_text = cursor.getString(cursor.getColumnIndex(helper.DATABASE_COLUMN_TITLE_TEXT));
                String body_text = cursor.getString(cursor.getColumnIndex(helper.DATABASE_COLUMN_BODY_TEXT));

                if ( ! cursor.isClosed() ){
                    cursor.close();
                }

                addButton.setVisibility(View.INVISIBLE);

                edit_title_text.setText(title_text);
                edit_body_text.setText(body_text);
            }
        }


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog .setMessage("메모를 등록하시겠습니까??")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                helper.insert(edit_title_text.getText().toString(), edit_body_text.getText().toString());

                                Toast.makeText(context,"메모가 등록되었습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(context, Memo_List.class);
                                startActivity(intent);

                                finish();
                            }
                        });
                dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"취소되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.create();
                dialog.show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Memo_AddPage.this);
                dialog .setMessage("메모 등록을 취소하시겠습니까?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Memo_AddPage.this,"취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Memo_AddPage.this,"메모를 작성하세요.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });


        btn_listImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder dialog = new AlertDialog.Builder(Memo_AddPage.this);
                dialog .setMessage("리스트 화면으로 이동하시겠습니까?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                Intent intent = new Intent(context, Memo_List.class);
                                startActivity(intent);
                                Toast.makeText(Memo_AddPage.this, "이동하였습니다.", Toast.LENGTH_LONG).show();
                            }
                        });

                dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Memo_AddPage.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();
                Log.d("listAddButton", "Success");
            }
        });
    }
}