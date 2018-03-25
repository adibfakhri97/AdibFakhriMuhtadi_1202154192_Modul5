package com.example.android.adibfakhrimuhtadi_1202154192_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class setting extends AppCompatActivity {
    TextView warna;
    int idwarna;
    AlertDialog.Builder alertD;
    SharedPreferences.Editor sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        setTitle("Pengaturan");

        alertD = new AlertDialog.Builder(this); //menambahkan alertDialog

        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("pref", 0); //identifikasi sharedpreference
        sp = sharedP.edit();
        idwarna = sharedP.getInt("keyC", R.color.white);
        warna = findViewById(R.id.warna);
        warna.setText(getShapeColor(idwarna));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(setting.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.merah;
        }else if (i==R.color.green){
            return R.id.hijau;
        }else if (i==R.color.blue){
            return R.id.biru;
        }else{
            return R.id.putih;
        }
    }

    public void choosecolor(View view) {
        View view1 = getLayoutInflater().inflate(R.layout.warnaset, null);
        alertD.setView(view1);
        final RadioGroup radio = view1.findViewById(R.id.radioG);
        radio.check(getColorid(idwarna));

        alertD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int a = radio.getCheckedRadioButtonId();
                switch (a){
                    case R.id.merah:
                        idwarna = R.color.red;
                        break;
                    case R.id.hijau:
                        idwarna = R.color.green;
                        break;
                    case R.id.biru:
                        idwarna = R.color.blue;
                        break;
                    case R.id.putih:
                        idwarna = R.color.white;
                        break;
                }
                warna.setText(getShapeColor(idwarna));
                sp.putInt("Colourground", idwarna);
                sp.commit();
            }
        });

        alertD.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertD.create().show();
    }
}
