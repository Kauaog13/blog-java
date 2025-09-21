// src/com/blog/model/Postagem.java
package com.blog.model;

import java.sql.Timestamp;

public class Postagem {
    private int id;
    private String titulo;
    private String conteudo;
    private Timestamp dataPublicacao;
    private Autor autor; // Relacionamento com Autor
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public Timestamp getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(Timestamp dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    // Construtores, Getters e Setters
}