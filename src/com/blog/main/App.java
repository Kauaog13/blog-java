package com.blog.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega a tela de login como a primeira tela, usando caminho absoluto
            Parent root = FXMLLoader.load(getClass().getResource("/com/blog/view/LoginView.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de login:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}