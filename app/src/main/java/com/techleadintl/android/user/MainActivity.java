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

    private EditText metName, mEtEmail, mEtAge, mEtNote;
    Button btnAdd, btnEdit;
    RecyclerView mrv;
    MyApplication myApplication = (MyApplication) this.getApplication();
    List<Model> models;
    MyAdapter madapter;
    private RecyclerView.LayoutManager mlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = myApplication.getModelsList();

        metName = findViewById(R.id.name);
        mEtEmail = findViewById(R.id.email);
        mEtAge = findViewById(R.id.Age);
        mEtNote = findViewById(R.id.note);
        btnAdd = findViewById(R.id.add_btn);
        mrv = findViewById(R.id.rv);
        btnEdit =findViewById(R.id.edit_btn);

    btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create president objects

                validateEmailAddress(mEtEmail);

                String email = mEtEmail.getText().toString();
                String name = metName.getText().toString();
                String note = mEtNote.getText().toString();
                String age = mEtAge.getText().toString();

                int nextId = myApplication.getNextId();

                if (TextUtils.isEmpty(email)) {
                    mEtEmail.setError("required email");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    metName.setError("required name");
                    return;
                }
                if (TextUtils.isEmpty(note)) {
                    mEtNote.setError("required note");
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    mEtAge.setError("required age");
                    return;
                }

                Model newmodel = new Model(nextId, metName.getText().toString(), mEtEmail.getText().toString(), mEtAge.getText().toString(), mEtNote.getText().toString());

                //add the object to the global list of model
                models.add(newmodel);
                myApplication.setNextId(nextId++);
                Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();

                clearData();
                //Edit data
                editData();
            }

            private void editData() {

                if(getIntent().getBundleExtra("userdata")!=null){
                    Bundle bundle = getIntent().getBundleExtra("userdata");
                    metName.setText(bundle.getString("Name"));
                    mEtEmail.setText(bundle.getString("Email"));
                    mEtAge.setText(bundle.getString("Age"));
                    mEtNote.setText(bundle.getString("Note"));
                    btnEdit.setVisibility(View.VISIBLE);
                }
            }

            private void clearData() {
                metName.setText("");
                mEtEmail.setText("");
                mEtAge.setText("");
                mEtNote.setText("");
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

        mrv.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        mrv.setLayoutManager(mlayoutManager);

        //adapter
        madapter = new MyAdapter(models, MainActivity.this);
        mrv.setAdapter(madapter);

        madapter.setOnItemClickListener(new MyAdapter.OnItemClickListner() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
            private void removeItem(int position) {
                    models.remove(position);
                    madapter.notifyItemRemoved(position);

            }
        });
    }
}

