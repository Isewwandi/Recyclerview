package com.techleadintl.android.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   List<model>models;
   Context context;

    public MyAdapter(List<model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_user,parent,false);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.email.setText(models.get(position).getEmail());
        holder.name.setText(models.get(position).getName());
        holder.age.setText(models.get(position).getAge());
        holder.note.setText(models.get(position).getNote());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,age,note;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_txt);
            email = itemView.findViewById(R.id.email_txt);
            age = itemView.findViewById(R.id.age_txt);
            note = itemView.findViewById(R.id.note_txt);
        }
    }
}
