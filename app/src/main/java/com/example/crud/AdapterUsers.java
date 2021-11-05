package com.example.crud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder > {

    Context context;
    Banco db;
    int emailAtual;
    int materia;


    public AdapterUsers(Context context,Banco db) {
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
        i += 1;

        if(i!= emailAtual){
            if(db.selecionarUser(i).getNome()!=null){
                myHolder.mNameTv.setText( db.selecionarUser(i).getNome());

            }


            }

            myHolder.mEmailTv.setText(db.selecionarUser(i).getEmail());
            myHolder.mTelefone.setText(String.valueOf(db.selecionarUser(i).getTelefone()));
        int finalI = i;
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditarActivity.class);
                intent.putExtra("id",finalI);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        int size;
        try{
            size = db.getDbSize();
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


