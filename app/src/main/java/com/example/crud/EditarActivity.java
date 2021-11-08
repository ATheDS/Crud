package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {
    int id;
    EditText nomeedit,emailedit,telefoneedit;
    Banco db;
    Button salvar;
    TextView deletar;

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
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaRemocao();

            }
        });
        emailedit.setText(db.selecionarUser(id).getEmail());
        nomeedit.setText(db.selecionarUser(id).getNome());
        telefoneedit.setText(String.valueOf(db.selecionarUser(id).getTelefone()));

// Check if email id is valid or not

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nometxt = nomeedit.getText().toString();
                String emailtxt = emailedit.getText().toString();
                String telefonetxt = telefoneedit.getText().toString();
                try{
                    if(nometxt.equals("") || emailtxt.equals("") || telefonetxt.equals("")){
                        Toast.makeText(EditarActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();



                    }else {
                        if (!isEmailValid(emailtxt)){
                            Toast.makeText(EditarActivity.this, "email invalido", Toast.LENGTH_SHORT).show();
                        }else{
                            if(telefonetxt.length() <8){
                                Toast.makeText(EditarActivity.this, "telefone invalido", Toast.LENGTH_SHORT).show();
                            }else{
                                try {
                                    db.setUserString("email",emailedit.getText().toString(),id);


                                }catch (Exception e) {
                                    Toast.makeText(EditarActivity.this, "Email ja cadastrado", Toast.LENGTH_SHORT).show();

                                    e.printStackTrace();
                                }
                                db.setUserString("nome",nomeedit.getText().toString(),id);
                                db.setUserInt("telefone",Integer.parseInt(telefoneedit.getText().toString()),id);
                                Intent intent = new Intent(EditarActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    }

                }catch (Exception e) {
                    Toast.makeText(EditarActivity.this, "Email ja cadastrado", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
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
        deletar = findViewById(R.id.deletar);



    }
    private void confirmaRemocao() {
        new AlertDialog
                .Builder(this)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteUserFromID(id);
                        Toast.makeText(EditarActivity.this,"usuario deletado" , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditarActivity.this,MainActivity.class));
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}