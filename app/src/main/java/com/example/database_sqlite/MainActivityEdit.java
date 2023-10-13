package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityEdit extends AppCompatActivity {

    private EditText id;
    private EditText firstname;
    private EditText lastname;
    private EditText mark;
    private Button edit;
    private Button back;
    private DBHelper dbHelper;
    String Id;
    String Fname;
    String Lname;
    String Mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityEdit.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = id.getText().toString();
                Fname = firstname.getText().toString();
                Lname = lastname.getText().toString();
                Mark = mark.getText().toString();
                if (Id.isEmpty()){
                    Toast.makeText(MainActivityEdit.this, "Hibás ID", Toast.LENGTH_SHORT).show();
                }
                else if(Fname.isEmpty()){
                    Toast.makeText(MainActivityEdit.this, "Hibás Vezetéknév", Toast.LENGTH_SHORT).show();
                }
                else if(Lname.isEmpty()){
                    Toast.makeText(MainActivityEdit.this, "Hibás Keresztnév", Toast.LENGTH_SHORT).show();
                }
                else if (Mark.isEmpty()) {
                    Toast.makeText(MainActivityEdit.this, "Hibás Jegy", Toast.LENGTH_SHORT).show();
                }
                else{
                    Edit();
                }
            }
        });
    }

    private void Edit(){
        int mark = Integer.getInteger(Mark);
        boolean edit = dbHelper.modositas(Id, Fname, Lname, mark);
        if (edit){
            Toast.makeText(this, "Sikeres módosítás", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sikertelen módosítás", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        id = findViewById(R.id.idEdit);
        firstname = findViewById(R.id.firstnameEdit);
        lastname = findViewById(R.id.latsnameEdit);
        mark = findViewById(R.id.markEdit);
        edit = findViewById(R.id.edit);
        back = findViewById(R.id.backEdit);
        dbHelper = new DBHelper(MainActivityEdit.this);
    }
}