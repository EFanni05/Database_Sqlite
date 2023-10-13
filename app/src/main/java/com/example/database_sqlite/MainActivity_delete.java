package com.example.database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_delete extends AppCompatActivity {

    private EditText id;
    private Button delete;
    private Button back;
    private DBHelper dbHelper;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_delete);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_delete.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = id.getText().toString();
                delete(Id);
            }
        });
    }

    private void delete(String id){
        boolean delete = dbHelper.torles(id);
        if (delete){
            Toast.makeText(this, "Sikeres törlés", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sikertelen törlés", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        id = findViewById(R.id.idDelete);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.backDelete);
        dbHelper = new DBHelper(MainActivity_delete.this);
    }
}