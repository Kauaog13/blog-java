package com.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    /**
     * Cria e retorna uma nova conexão com o banco de dados.
     * @return uma nova conexão com o banco.
     * @throws SQLException se a conexão falhar.
     */
    public static Connection getConnection() throws SQLException {
        // A URL completa com todos os parâmetros necessários para evitar erros.
        String url = "jdbc:mysql://localhost:3306/blog_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        
        // Suas credenciais do banco de dados.
        String user = "root"; // <-- Coloque seu usuário do MySQL aqui
        String password = "sua senha"; // <-- Coloque sua senha aqui
        
        return DriverManager.getConnection(url, user, password);
    }
}