package com.example.crud;

import java.io.Serializable;

public class User implements Serializable {
    String nome,email;
    Integer telefone,id;

    public User(Integer id,String nometxt, String emailtxt, Integer telefonetxt) {
        this.id = id;
        this.nome = nometxt;
        this.email = emailtxt;
        this.telefone = telefonetxt;
    }

    public User(String nometxt, String emailtxt, Integer telefonetxt) {

        this.nome = nometxt;
        this.email = emailtxt;
        this.telefone = telefonetxt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
}
