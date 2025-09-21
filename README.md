# Blog Pessoal Simples em Java

Um projeto simples para gerenciar postagens de um blog, demonstrando as operações de um CRUD (Create, Read, Update, Delete) com Java puro (JDBC) e um banco de dados MySQL.

---

## ☕ Funcionalidades

* Criar uma nova postagem com título, conteúdo e autor.
* Listar todas as postagens existentes.
* Editar o título e o conteúdo de uma postagem.
* Excluir uma postagem.

---

## 🛠️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versão 11 ou superior)
* [MySQL Server](https://dev.mysql.com/downloads/mysql/)
* Um cliente de banco de dados de sua preferência (DBeaver, MySQL Workbench, etc.)

---

## 🚀 Como Configurar e Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Kauaog13/blog-java.git](https://github.com/Kauaog13/blog-java.git)
    ```

2.  **Configure o Banco de Dados:**
    * Crie um schema/banco de dados no MySQL chamado `blog_db`.
    * Execute o script SQL para criar as tabelas `autores` e `postagens`. (É uma boa prática criar um arquivo `database.sql` no seu projeto com os comandos `CREATE TABLE`).

3.  **Configure o Driver JDBC no seu Editor:**
    * Baixe o **[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)**.
    * Se estiver usando VS Code, adicione o arquivo `.jar` baixado à seção "Referenced Libraries" na aba "Java Projects".

4.  **Configure a Conexão:**
    * Abra o arquivo `src/com/blog/db/DB.java`.
    * Altere as variáveis `user` e `password` com suas credenciais do MySQL.

5.  **Execute a Aplicação:**
    * Abra o arquivo `src/com//blog/main/App.java` e execute o método `main`.