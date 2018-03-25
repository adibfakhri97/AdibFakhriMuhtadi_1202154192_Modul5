package com.example.android.adibfakhrimuhtadi_1202154192_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class todoAdd extends AppCompatActivity {
    EditText judul, deskripsi, priority; //variable yg diperlukan
    dbHelp myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_add);

        judul = (EditText) findViewById(R.id.editJudul); //identifikasi
        deskripsi = (EditText) findViewById(R.id.editDeskripsi);
        priority = (EditText) findViewById(R.id.editPriority);
        myDb = new dbHelp(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(todoAdd.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void tambah(View view) { //method yang berfungsi menambah data ke database
        if (myDb.inputdata(new methodData(judul.getText().toString(), deskripsi.getText().toString(), priority.getText().toString()))){
            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(todoAdd.this, MainActivity.class));
            this.finish();
        }else {
            Toast.makeText(this, "Cannot tambah the list", Toast.LENGTH_SHORT).show();
            judul.setText(null);
            deskripsi.setText(null);
            priority.setText(null);
        }
    }

}
