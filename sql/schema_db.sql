CREATE DATABASE blog_db;

USE blog_db;

-- Tabela de Autores
CREATE TABLE autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela de Postagens
CREATE TABLE postagens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    conteudo TEXT NOT NULL,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    autor_id INT,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
);

--(Opcional, mas recomendado) Insira um autor para testes
INSERT INTO autores (nome, email, senha) VALUES ('Admin', 'admin@blog.com', '1234');