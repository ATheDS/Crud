package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CriarActivity extends AppCompatActivity {
    Button salvar;
    EditText nome,email,telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar);
        Banco db = ((Application) getApplication()).getDb();


        InicializarComponentes();
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nometxt = nome.getText().toString();
                String emailtxt = email.getText().toString();
                String telefonetxt = telefone.getText().toString();
                if(nometxt.equals("") || emailtxt.equals("") || telefonetxt.equals("")){
                    Toast.makeText(CriarActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        if (!isEmailValid(emailtxt)){
                            Toast.makeText(CriarActivity.this, "email invalido", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            if(telefonetxt.length() <8){
                                Toast.makeText(CriarActivity.this, "telefone invalido", Toast.LENGTH_SHORT).show();


                            }else{
                                try {

                                    String erro = db.addUser(new User(nometxt, emailtxt, Integer.parseInt(telefonetxt)));
                                    Toast.makeText(CriarActivity.this, erro, Toast.LENGTH_SHORT).show();



                                }catch (Exception e) {
                                    Toast.makeText(CriarActivity.this, "Email ja cadastrado", Toast.LENGTH_SHORT).show();

                                    e.printStackTrace();
                                }
                                startActivity(new Intent(CriarActivity.this,MainActivity.class));
                                finish();

                            }



                        }


                    }catch (Exception e) {
                        Toast.makeText(CriarActivity.this, "Email ja cadastrado", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }


                }





            }
        });


    }

    private void InicializarComponentes() {
        salvar = findViewById(R.id.salvar);
        nome = findViewById(R.id.nomeedit);
        telefone = findViewById(R.id.telefoneedit);
        email = findViewById(R.id.emailedit);
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}