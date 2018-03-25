package com.example.android.adibfakhrimuhtadi_1202154192_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    dbHelp myDB;      //variable yang dibutuhkan
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<methodData> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerV);  //mengaitkan recyclerView ke xml
        mList = new ArrayList<>();  //ArrayList baru
        myDB = new dbHelp(this);    //database baru
        myDB.getData(mList); //memanggil method yang ada di dbHelp


        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("pref", 0);
        int color = sharedP.getInt("keyC", R.color.white); //warna untuk cardview

        adapter = new Adapter(this,mList, color);  //adapter untuk recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //membuat layout Linear
        recyclerView.setAdapter(adapter); //set adapter ke recyclerView

        swipeData(); //memanggil method swipeData
    }

    public void swipeData(){  //method yang berfungsi untuk menghapus data dengan swipe
        ItemTouchHelper.SimpleCallback tc = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                methodData curr = adapter.getData(position);

                if(direction==ItemTouchHelper.RIGHT){  //item diswipe ke kanan
                    if(myDB.hapus(curr.getJudul())){
                        adapter.hapusDat(position);
                        Snackbar.make(findViewById(R.id.re), "Data berhasil dihapus", 1500).show();
                    }
                }
            }
        };
        ItemTouchHelper th = new ItemTouchHelper(tc);
        th.attachToRecyclerView(recyclerView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //method untuk item Setting pada toolbar
        int id = item.getItemId();
        if (id==R.id.action_settings){
            Intent intent = new Intent(MainActivity.this, setting.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void tambah(View view) {
        Intent intent = new Intent(MainActivity.this, todoAdd.class);
        startActivity(intent);
    }
}
