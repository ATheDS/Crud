package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends Activity {
    FloatingActionButton criar;
    Banco db;
    RecyclerView recyclerView;
    AdapterUsers adapterUsers;
    ArrayList<User> userlist;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = ((Application) getApplication()).getDb();
        userlist = new ArrayList<User>();
        try{
            for (int i = 1 ; i <= db.getDbSize()  ; i++) {
                userlist.add(db.selecionarUser(i));

            }

        }catch (Exception e){

        }
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<User> filteredusers = new ArrayList<User>();
                for (User user: userlist) {
                    if(user.getNome().toLowerCase().contains(s.toLowerCase())){
                        filteredusers.add(user);

                    }
                    AdapterUsers aadapter = new AdapterUsers(MainActivity.this,db,filteredusers);
                    recyclerView.setAdapter(aadapter);



                }



                return false;
            }
        });

        criar = findViewById(R.id.criarFloting);


        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CriarActivity.class);
                startActivity(intent);
            }
        });

        recyclerView =findViewById(R.id.lista_pessoas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapterUsers = new AdapterUsers(MainActivity.this,db,userlist);
        recyclerView.setAdapter(adapterUsers);


    }

}