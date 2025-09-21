// src/com/blog/dao/PostagemDAO.java
package com.blog.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.blog.db.DB;
import com.blog.model.Autor;
import com.blog.model.Postagem;

public class PostagemDAO {

    // Criar uma nova postagem
    public void criarPostagem(Postagem postagem) {
        String sql = "INSERT INTO postagens (titulo, conteudo, autor_id) VALUES (?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, postagem.getTitulo());
            stmt.setString(2, postagem.getConteudo());
            stmt.setInt(3, postagem.getAutor().getId());
            stmt.executeUpdate();
            System.out.println("Postagem criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar postagem: " + e.getMessage());
        }
    }

    // Listar todas as postagens
    public List<Postagem> listarTodas() {
        List<Postagem> postagens = new ArrayList<>();
        // Usamos JOIN para buscar o nome do autor junto com a postagem
        String sql = "SELECT p.id, p.titulo, p.conteudo, p.data_publicacao, a.id as autor_id, a.nome as autor_nome " +
                     "FROM postagens p JOIN autores a ON p.autor_id = a.id ORDER BY p.data_publicacao DESC";

        try (Connection conn = DB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("autor_id"));
                autor.setNome(rs.getString("autor_nome"));

                Postagem post = new Postagem();
                post.setId(rs.getInt("id"));
                post.setTitulo(rs.getString("titulo"));
                post.setConteudo(rs.getString("conteudo"));
                post.setDataPublicacao(rs.getTimestamp("data_publicacao"));
                post.setAutor(autor);
                
                postagens.add(post);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar postagens: " + e.getMessage());
        }
        return postagens;
    }

    // Atualizar uma postagem
    public void atualizarPostagem(Postagem postagem) {
        String sql = "UPDATE postagens SET titulo = ?, conteudo = ? WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, postagem.getTitulo());
            stmt.setString(2, postagem.getConteudo());
            stmt.setInt(3, postagem.getId());
            stmt.executeUpdate();
            System.out.println("Postagem atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar postagem: " + e.getMessage());
        }
    }

    // Excluir uma postagem
    public void excluirPostagem(int id) {
        String sql = "DELETE FROM postagens WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Postagem excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir postagem: " + e.getMessage());
        }
    }
}