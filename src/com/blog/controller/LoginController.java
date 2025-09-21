package com.blog.controller;

import com.blog.dao.AutorDAO;
import com.blog.model.Autor;
import com.blog.view.AutorFormularioController;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField senhaField;
    
    private AutorDAO autorDAO = new AutorDAO();

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String senha = senhaField.getText();

        Autor autor = autorDAO.findByEmailAndSenha(email, senha);

        if (autor != null) {
            closeWindow();
            abrirTelaPrincipal(autor);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Login");
            alert.setHeaderText("Email ou senha inválidos.");
            alert.setContentText("Por favor, tente novamente ou crie uma nova conta.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleCriarConta() {
        try {
            FXMLLoader loader = new FXMLLoader();
            // Carrega o formulário de autor usando caminho absoluto
            loader.setLocation(getClass().getResource("/com/blog/view/AutorFormulario.fxml"));
            GridPane page = (GridPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastrar Novo Autor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner((Stage) emailField.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AutorFormularioController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaPrincipal(Autor autor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/blog/view/BlogView.fxml"));
            Parent root = loader.load();

            BlogController blogController = loader.getController();
            blogController.initData(autor);

            Stage stage = new Stage();
            stage.setTitle("Meu Blog - " + autor.getNome());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.close();
    }
}