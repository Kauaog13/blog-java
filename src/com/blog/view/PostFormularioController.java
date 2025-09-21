package com.blog.view;

import com.blog.model.Autor;
import com.blog.model.Postagem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PostFormularioController {

    @FXML
    private TextField tituloField;
    @FXML
    private TextArea conteudoArea;
    @FXML
    private TextField autorIdField;

    private Stage dialogStage;
    private Postagem postagem;
    private boolean salvo = false;

    // Chamado para configurar o "palco" (a janela) deste diálogo
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Define a postagem a ser editada no diálogo
    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;

        // Preenche os campos com os dados da postagem (se for uma edição)
        tituloField.setText(postagem.getTitulo());
        conteudoArea.setText(postagem.getConteudo());
        if (postagem.getAutor() != null) {
            autorIdField.setText(String.valueOf(postagem.getAutor().getId()));
        }
    }
    
    // Retorna true se o usuário clicou em Salvar, false caso contrário
    public boolean isSalvo() {
        return salvo;
    }

    @FXML
    private void handleSalvar() {
        // Validação simples
        if (tituloField.getText() == null || tituloField.getText().isEmpty()) {
            // Aqui você poderia mostrar um alerta de erro
            System.out.println("Título é obrigatório!");
            return;
        }

        // Preenche o objeto postagem com os dados do formulário
        postagem.setTitulo(tituloField.getText());
        postagem.setConteudo(conteudoArea.getText());
        
        Autor autor = new Autor();
        autor.setId(Integer.parseInt(autorIdField.getText()));
        postagem.setAutor(autor);
        
        salvo = true;
        dialogStage.close(); // Fecha a janela do formulário
    }

    @FXML
    private void handleCancelar() {
        dialogStage.close(); // Fecha a janela do formulário
    }
}