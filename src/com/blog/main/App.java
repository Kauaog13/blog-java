package com.blog.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o arquivo FXML da interface
        Parent root = FXMLLoader.load(getClass().getResource("../view/BlogView.fxml"));
        
        // Cria a cena com o conteúdo da interface
        Scene scene = new Scene(root);
        
        // Define o título da janela
        primaryStage.setTitle("Meu Blog");
        // Adiciona a cena à janela
        primaryStage.setScene(scene);
        // Exibe a janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}