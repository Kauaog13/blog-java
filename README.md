# Blog Simples com Interface Gráfica em JavaFX

Uma aplicação desktop desenvolvida em Java com JavaFX que simula um sistema de blog. Permite a criação de múltiplos usuários, login e gerenciamento completo (CRUD) de postagens através de uma interface gráfica intuitiva.

![Screenshot da Aplicação](https://github.com/user-attachments/assets/2ba1b69b-f565-4a59-8f41-12a95b700d37)

---

## 🚀 Funcionalidades

* **Autenticação de Usuário:** Sistema de login seguro para acesso à aplicação.
* **Cadastro de Usuário:** Formulário para criação de novas contas de autor (nome, email, senha).
* **Gerenciamento de Postagens (CRUD):**
    * **Criar:** Adicionar novas postagens associadas ao usuário logado.
    * **Ler:** Visualizar todas as postagens em uma tabela principal.
    * **Atualizar:** Editar o título e o conteúdo de postagens existentes.
    * **Deletar:** Remover postagens.
* **Interface Gráfica Moderna:** Desenvolvida com **JavaFX** e FXML, separando o design da lógica da aplicação.
* **Arquitetura Organizada:** O projeto segue uma estrutura com separação de responsabilidades (View, Controller, DAO, Model).

---

## 🛠️ Tecnologias Utilizadas

* **Java (JDK 17+)**
* **JavaFX (SDK 21+)** para a interface gráfica
* **JDBC (Java Database Connectivity)** para comunicação com o banco
* **MySQL** como sistema de gerenciamento de banco de dados
* **Visual Studio Code** como ambiente de desenvolvimento

---

## ⚙️ Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

1.  [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versão 17 ou superior).
2.  [JavaFX SDK](https://gluonhq.com/products/javafx/) (compatível com a sua versão do JDK).
3.  [MySQL Connector/J.](https://dev.mysql.com/downloads/connector/j/)
4.  [MySQL Server](https://dev.mysql.com/downloads/mysql/) (versão 8.0 ou superior).
5.  [Visual Studio Code](https://code.visualstudio.com/) com o "Extension Pack for Java".

---

## 🖥️ Como Rodar Localmente

Siga os passos abaixo para configurar e executar o projeto no seu ambiente.

### 1. Clone o Repositório
```bash
git clone https://github.com/Kauaog13/blog-java.git
cd blog-java
```

### 2. Configure o Banco de Dados
```Sql
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
```

### 3. Configure o Ambiente no VS Code
Abra a pasta do projeto no Visual Studio Code.

* Adicione as Bibliotecas Referenciadas (JARs):

    * Na aba JAVA PROJECTS do VS Code, clique no ícone + ao lado de Referenced Libraries e adicione o arquivo **.jar do MySQL Connector.**

    * Na mesma seção Referenced Libraries, clique no + novamente e adicione **TODOS os arquivos .jar da pasta lib do JavaFX SDK**.

* Configure o Arquivo launch.json:

    * Crie ou edite o arquivo .vscode/launch.json e insira a seguinte configuração. Lembre-se de substituir o caminho para o seu JavaFX SDK!
        ```JSON
        {
            "version": "0.2.0",
            "configurations": [
                {
                    "type": "java",
                    "name": "Launch App",
                    "request": "launch",
                    "mainClass": "com.blog.main.App",
                    "projectName": "blog-java", // Verifique se o nome do projeto está correto
                    "vmArgs": "--module-path \"/caminho/completo/para/seu/javafx-sdk-21/lib\" --add-modules javafx.controls,javafx.fxml"
                }
            ]
        }
        ```
  
### 4. Configure as Credenciais do Banco
Abra o arquivo src/com/blog/db/DB.java.

* Altere as variáveis user e password com as suas credenciais de acesso ao MySQL.

### 5. Execute a Aplicação
Abra a aba "Executar e Depurar" (▶️🐞).

* Verifique se a configuração "Launch App" está selecionada no topo.

* Clique no botão de play verde (▶️) para iniciar a aplicação.

* A janela de login deverá aparecer. Você pode criar uma nova conta e começar a usar o blog!
