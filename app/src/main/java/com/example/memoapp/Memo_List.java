package com.example.memoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Memo_List extends AppCompatActivity {

    Helper helper;
    private ListView listView;
    private Button addButton;
    ArrayAdapter adapter;
    private ImageButton btn_listImageButton;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Context context = this;

        helper = new Helper(this);
        ArrayList<Map<String, String>> arrayList = helper.getAll();
        addButton = findViewById(R.id.add_button);
        btn_listImageButton = findViewById(R.id.btn_listImage);

        Log.d("db쿼리 결과", arrayList.toString());

        // adapter = new CustomAdapter
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView = findViewById(R.id.memo_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, arg)->{
            int id = position + 1;

            Bundle bundle = new Bundle();
            bundle.putInt("id", id);

            AlertDialog.Builder dialog = new AlertDialog.Builder(Memo_List.this);

            Log.d("Dialog update or Delete", "Success");

            dialog .setMessage("원하는 작업을 선택하세요.")
                    .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Memo_List.this, "메모를 수정하세요", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Memo_AddPage.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

            dialog.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("id",String.valueOf(id));


                    Log.d("Database", helper.getAll().toString());
                    // database 영역
                    helper.delete(id);
                    Log.d("Database", helper.getAll().toString());

                    // view 영역
                    Log.d("arrayList",arrayList.toString());
                    arrayList.remove(id);
                    Log.d("arrayList",arrayList.toString());

                    adapter.notifyDataSetChanged();

                    Toast.makeText(Memo_List.this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.create();
            dialog.show();
            Log.d("this","Success");
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Memo_AddPage.class);
                startActivity(intent);
            }
        });

       btn_listImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Memo_List.this);
                dialog .setMessage("리스트 화면으로 이동하시겠습니까?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Memo_List.this,"이동하였습니다.", Toast.LENGTH_SHORT).show();
                                // + button 클릭 후 yes를 누르면 activity_main2.xml 동작
                                Intent intent = new Intent(context, Memo_List.class);
                                startActivity(intent);
                            }
                        });
                dialog.setNeutralButton("CanCel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Memo_List.this,"취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();
                Log.d("this","Success");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();
        adapter.addAll(helper.getAll());
        adapter.notifyDataSetChanged();
    }

    public void mOnClick(View view){
        Bundle bundle = new Bundle();
        bundle.putInt("id", 0);

        Intent intent = new Intent(getApplicationContext(), Memo_AddPage.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
