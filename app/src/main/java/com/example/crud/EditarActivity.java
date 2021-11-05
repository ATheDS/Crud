package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {
    int id;
    EditText nomeedit,emailedit,telefoneedit;
    Banco db;
    Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");


            // and get whatever type user account id is
        }
        IniciarComponentes();
        emailedit.setText(db.selecionarUser(id).getEmail());
        nomeedit.setText(db.selecionarUser(id).getNome());
        telefoneedit.setText(String.valueOf(db.selecionarUser(id).getTelefone()));
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nometxt = nomeedit.getText().toString();
                String emailtxt = emailedit.getText().toString();
                String telefonetxt = telefoneedit.getText().toString();
                if(nometxt.equals("") || emailtxt.equals("") || telefonetxt.equals("")){
                    Toast.makeText(EditarActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();



                }else {
                    db.setUserString("email",emailedit.getText().toString(),id);
                    db.setUserString("nome",nomeedit.getText().toString(),id);
                    db.setUserInt("telefone",Integer.parseInt(telefoneedit.getText().toString()),id);


                    Intent intent = new Intent(EditarActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });


    }

    private void IniciarComponentes() {
        emailedit = findViewById(R.id.emailedit);
        nomeedit = findViewById(R.id.nomeedit);
        telefoneedit = findViewById(R.id.telefoneedit);
        db = ((Application) this.getApplication()).getDb();
        salvar = findViewById(R.id.salvar);



    }
}