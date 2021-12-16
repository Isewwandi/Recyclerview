package com.techleadintl.android.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, age_txt, note_txt;
    Button add;
    String name, email, age, note;
    RecyclerView rv;
    MyApplication myApplication = (MyApplication) this.getApplication();
    List<model> models;
    MyAdapter adapter;


    //    private RecyclerView.MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = myApplication.getModelsList();

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        age_txt = findViewById(R.id.Age);
        note_txt = findViewById(R.id.note);
        add = findViewById(R.id.add_btn);
        rv = findViewById(R.id.rv);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create president objects

                validateEmailAddress(etEmail);


                String email = etEmail.getText().toString();
                String name = etName.getText().toString();
                String note = note_txt.getText().toString();
                String age = age_txt.getText().toString();


                int nextId = myApplication.getNextId();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("required email");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    etName.setError("required name");
                    return;
                }
                if (TextUtils.isEmpty(note)) {
                    note_txt.setError("required note");
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    age_txt.setError("required age");
                    return;
                }


                model newmodel = new model(nextId, etName.getText().toString(), etEmail.getText().toString(), age_txt.getText().toString(), note_txt.getText().toString());

                //add the object to the global list of model

                models.add(newmodel);
                myApplication.setNextId(nextId++);
                Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();

                clearData();
                //goback
            }

            private void clearData() {
                etName.setText("");
                etEmail.setText("");
                age_txt.setText("");
                note_txt.setText("");
            }

            private boolean validateEmailAddress(EditText email_txt) {

                String emailInput = email_txt.getText().toString();

                if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    return true;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //Recyclerview

        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        //adapter
        adapter = new MyAdapter(models, MainActivity.this);
        rv.setAdapter(adapter);

    }
}

