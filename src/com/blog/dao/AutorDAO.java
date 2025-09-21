package com.blog.dao;

import com.blog.db.DB;
import com.blog.model.Autor;
import java.sql.*;

public class AutorDAO {

    public void criarAutor(Autor autor) {
        String sql = "INSERT INTO autores (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());
            stmt.setString(3, autor.getSenha());
            stmt.executeUpdate();
            System.out.println("Autor criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar autor: " + e.getMessage());
        }
    }

    public Autor findByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM autores WHERE email = ? AND senha = ?";
        try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor();
                    autor.setId(rs.getInt("id"));
                    autor.setNome(rs.getString("nome"));
                    autor.setEmail(rs.getString("email"));
                    return autor;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar autor: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o autor ou der erro
    }
}