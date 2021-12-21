package com.techleadintl.android.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    Button btnOk;
    List<Model> models;

    TextView mtvEmail, mtvName, mtvNote,mtvAge,mProfileName;
    Button mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mtvName= findViewById(R.id.name);
        mtvEmail = findViewById(R.id.email);
        mtvAge = findViewById(R.id.age);
        mtvNote = findViewById(R.id.note);
        mProfileName = findViewById(R.id.profile_name);
        mBtnBack = findViewById(R.id.btn_back);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String age = getIntent().getStringExtra("age");
        String note = getIntent().getStringExtra("note");

        mtvName.setText(name);
        mtvEmail.setText(email);
        mtvAge.setText(age);
        mtvNote.setText(note);
        mProfileName.setText(name);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}