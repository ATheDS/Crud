package com.example.crud;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder > {

    Context context;
    Banco db;
    List<User> users;

    public AdapterUsers(Context context, Banco db, List<User> users) {
        this.users = users;
        this.context = context;
        this.db = db;//((MaisEstudoApplication) getApplication()).getDb();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i ) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, @SuppressLint("RecyclerView") int i) {
        User useratual = users.get(i);

        if(useratual.getNome()!=null){
            myHolder.mNameTv.setText(useratual.getNome());
        }

        myHolder.mEmailTv.setText(useratual.getEmail());
        myHolder.mTelefone.setText(String.valueOf(useratual.getTelefone()));
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditarActivity.class);
                intent.putExtra("useratual",useratual);
                intent.putExtra("id",useratual.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int size;
        try{
            size = users.size();
            return size;
        }catch (Exception e){
            return size = 0;
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView mNameTv,mEmailTv,mTelefone;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mEmailTv = itemView.findViewById(R.id.email);
            mNameTv = itemView.findViewById(R.id.nome);
            mTelefone = itemView.findViewById(R.id.telefone);
        }
    }

}