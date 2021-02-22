package com.example.memoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button plusButton;
    private ImageButton btn_listImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        final Context context = this;
        plusButton = (Button)findViewById(R.id.plusButton);
        btn_listImageButton = (ImageButton) findViewById(R.id.btn_listImage);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setMessage("메모를 등록하시겠습니까?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"메모를 생성하세요", Toast.LENGTH_SHORT).show();
                                // + button 클릭 후 yes를 누르면 activity_main2.xml 동작
                                Intent intent = new Intent(context, Memo_AddPage.class);
                                startActivity(intent);
                            }
                        });
                dialog.setNeutralButton("CanCel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();
                Log.d("this","Success");
            }
        });

        btn_listImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setMessage("리스트 화면으로 이동하시겠습니까??")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                Intent intent = new Intent(context, Memo_List.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "이동 완료", Toast.LENGTH_LONG).show();
                            }
                 });

                dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();
                Log.d("listAddButton", "Success");
            }
        });
    }
}
