package com.blog.model;

public class Autor {
    private int id;
    private String nome;
    private String email;
    private String senha; // O campo que você adicionou

    // Construtores, Getters e Setters...
    // (Se você já tem os outros, só precisa adicionar os de 'senha')

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // MÉTODOS QUE ESTÃO FALTANDO
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}