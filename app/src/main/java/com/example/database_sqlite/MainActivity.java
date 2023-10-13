package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button Read;
    private Button Fix;
    private Button Edit;
    private Button Delete;
    private TextView Text;
    private DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lekerdezes();
            }
        });
        Fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivityFix.class);
                startActivity(i);
                finish();
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivityEdit.class);
                startActivity(i);
                finish();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity_delete.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void lekerdezes(){
        Cursor adatok = dbhelper.lekerdezes();
        if(adatok == null){
            Toast.makeText(this, "Hiba történt a lekérdezés közben", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(adatok.getCount() == 0){
            Toast.makeText(this, "Még nincs adat felvéve", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            StringBuilder builder = new StringBuilder();
            while (adatok.moveToNext()){
                builder.append("ID: ").append(adatok.getInt(0)).append("\n")
                        .append("Vezetéknév: ").append(adatok.getInt(1)).append("\n")
                        .append("Keresztnév: ").append(adatok.getInt(2)).append("\n")
                        .append("Jegy: ").append(adatok.getInt(3)).append("\n\n");
            }
            Text.setText(builder);
            Toast.makeText(this, "Sikeres adatlekérdezés", Toast.LENGTH_SHORT).show();
        }
    }

    public void init(){
        Read = findViewById(R.id.readMain);
        Fix = findViewById(R.id.fixMain);
        Edit = findViewById(R.id.ediMaint);
        Delete = findViewById(R.id.deleteMain);
        Text = findViewById(R.id.textviewMain);
        Text.setMovementMethod(new ScrollingMovementMethod());
        dbhelper = new DBHelper(MainActivity.this);
    }
}