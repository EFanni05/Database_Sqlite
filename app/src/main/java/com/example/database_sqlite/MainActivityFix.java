package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityFix extends AppCompatActivity {

    private EditText fisrtname;
    private EditText lastname;
    private EditText mark;
    private Button fix;
    private Button back;
    private DBHelper dbHelper;
    String Fname;
    String Lname;
    Integer Mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fix);
        init();
        Fname = fisrtname.getText().toString();
        Lname = lastname.getText().toString();
        String a = mark.getText().toString();
        if(Fname.isEmpty()){
            Toast.makeText(this, "Vezetéknév kitöltése kötelező", Toast.LENGTH_SHORT).show();
        } else if (Lname.isEmpty()) {
            Toast.makeText(this, "Keresznév kitöltése kötelező", Toast.LENGTH_SHORT).show();
        } else if (a.isEmpty()) {
            Toast.makeText(this, "Jegy kitöltése kötelező", Toast.LENGTH_SHORT).show();
        } else {
            Mark = Integer.parseInt(a);
            if(dbHelper.rogzites(Fname, Lname,Mark)){
                Toast.makeText(this, "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Nem sikerült az adatfelvétel", Toast.LENGTH_SHORT).show();
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityFix.this , MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void init(){
        fisrtname = findViewById(R.id.firstnameFix);
        lastname = findViewById(R.id.lastnameFix);
        mark = findViewById(R.id.markFix);
        fix = findViewById(R.id.fix);
        back = findViewById(R.id.backFix);
        dbHelper = new DBHelper(MainActivityFix.this);
    }
}