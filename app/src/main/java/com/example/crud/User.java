package com.example.crud;

public class User {
    String nome,email;
    int telefone,id;

    public User(int id,String nometxt, String emailtxt, int telefonetxt) {
        this.id = id;
        this.nome = nometxt;
        this.email = emailtxt;
        this.telefone = telefonetxt;
    }

    public User(String nometxt, String emailtxt, int telefonetxt) {

        this.nome = nometxt;
        this.email = emailtxt;
        this.telefone = telefonetxt;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
