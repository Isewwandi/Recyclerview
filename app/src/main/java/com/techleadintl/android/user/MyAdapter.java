package com.techleadintl.android.user;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final String TAG = "MyAdapter";
    List<Model>models;
   Context mcontext;
   OnItemClickListner mListener;

   public interface OnItemClickListner{
       void onDeleteClick(int position);
   }
    public void setOnItemClickListener(OnItemClickListner listener) {
        mListener = (OnItemClickListner) listener;
    }
    public MyAdapter(List<Model> models, Context context) {
        this.models = models;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_user,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.memail.setText(models.get(position).getEmail());
        holder.mname.setText(models.get(position).getName());
        holder.mage.setText(models.get(position).getAge());
        holder.mnote.setText(models.get(position).getNote());

        holder.btEdit.setOnClickListener(v -> {

            Dialog dialog = new Dialog(mcontext);
            dialog.setContentView(R.layout.display_user);
            Button btnOk;
            EditText etName, etEmail, age_txt, note_txt;
            etName = dialog.findViewById(R.id.edit_name);
            etEmail = dialog.findViewById(R.id.edit_email);
            age_txt = dialog.findViewById(R.id.edit_age);
            note_txt = dialog.findViewById(R.id.edit_note);
            btnOk =dialog.findViewById(R.id.ok);

            etEmail.setText(models.get(position).getEmail());
            etName.setText(models.get(position).getName());
            age_txt.setText(models.get(position).getAge());
            note_txt.setText(models.get(position).getNote());

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = etEmail.getText().toString();
                    String name = etName.getText().toString();
                    String note = note_txt.getText().toString();
                    String age = age_txt.getText().toString();
                    int id = 0;
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
                models.set(position,new Model(id,name,email,age,note));
                    notifyItemChanged(position);

                    dialog.dismiss();
                }
            });
            dialog.show();
        });

        //activity
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: clicked on: " + models.get(position).getName());

                Toast.makeText(mcontext, models.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mcontext, EditActivity.class);
                intent.putExtra("email", models.get(position).getEmail());
                intent.putExtra("name", models.get(position).getName());
                intent.putExtra("age", models.get(position).getAge());
                intent.putExtra("note", models.get(position).getNote());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mname, memail, mage, mnote;
        Button btEdit,btDel;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mname = itemView.findViewById(R.id.name_txt);
            memail = itemView.findViewById(R.id.email_txt);
            mage = itemView.findViewById(R.id.age_txt);
            mnote = itemView.findViewById(R.id.note_txt);
            btEdit = itemView.findViewById(R.id.edit_btn);
            btDel = itemView.findViewById(R.id.del_btn);
            parentLayout =itemView.findViewById(R.id.layout);
            btDel.setOnClickListener(v -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onDeleteClick(position);
                    }
                }
            });
        }
    }
}
