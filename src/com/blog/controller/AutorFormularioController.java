package com.blog.controller;

import com.blog.dao.AutorDAO;
import com.blog.model.Autor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutorFormularioController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;

    private Stage dialogStage;
    private AutorDAO autorDAO;

    @FXML
    private void initialize() {
        autorDAO = new AutorDAO();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleSalvar() {
        if (isInputValid()) {
            Autor novoAutor = new Autor();
            novoAutor.setNome(nomeField.getText());
            novoAutor.setEmail(emailField.getText());
            novoAutor.setSenha(senhaField.getText());

            autorDAO.criarAutor(novoAutor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Usuário Cadastrado!");
            alert.setContentText("O usuário '" + novoAutor.getNome() + "' foi criado. Agora você já pode fazer login.");
            alert.showAndWait();

            dialogStage.close();
        }
    }

    @FXML
    private void handleCancelar() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nomeField.getText() == null || nomeField.getText().isEmpty()) {
            errorMessage += "Nome inválido!\n";
        }
        if (emailField.getText() == null || emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
            errorMessage += "Email inválido!\n";
        }
        if (senhaField.getText() == null || senhaField.getText().isEmpty()) {
            errorMessage += "Senha inválida!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Mostra o alerta de erro.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os campos inválidos.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}