package com.blog.view;

import com.blog.dao.PostagemDAO;
import com.blog.model.Postagem;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BlogController {

    @FXML
    private TableView<Postagem> tabelaPostagens;
    @FXML
    private TableColumn<Postagem, Integer> colunaId;
    @FXML
    private TableColumn<Postagem, String> colunaTitulo;
    @FXML
    private TableColumn<Postagem, String> colunaAutor;

    private PostagemDAO postagemDAO;

    @FXML
    public void initialize() {
        postagemDAO = new PostagemDAO();
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaAutor.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor().getNome()));

        carregarPostagens();
    }

    private void carregarPostagens() {
        ObservableList<Postagem> postagens = FXCollections.observableArrayList(postagemDAO.listarTodas());
        tabelaPostagens.setItems(postagens);
    }

    @FXML
    private void handleBotaoNovo() {
        Postagem novaPostagem = new Postagem();
        boolean salvo = mostrarFormularioPostagem(novaPostagem);
        if (salvo) {
            postagemDAO.criarPostagem(novaPostagem);
            carregarPostagens(); // Atualiza a tabela com a nova postagem
        }
    }

    @FXML
    private void handleBotaoEditar() {
        Postagem selecionada = tabelaPostagens.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            boolean salvo = mostrarFormularioPostagem(selecionada);
            if (salvo) {
                postagemDAO.atualizarPostagem(selecionada);
                carregarPostagens(); // Atualiza a tabela
            }
        } else {
            mostrarAlerta("Nenhuma postagem selecionada", "Por favor, selecione uma postagem na tabela para editar.");
        }
    }

    @FXML
    private void handleBotaoExcluir() {
        Postagem selecionada = tabelaPostagens.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            postagemDAO.excluirPostagem(selecionada.getId());
            carregarPostagens();
        } else {
            mostrarAlerta("Nenhuma postagem selecionada", "Por favor, selecione uma postagem na tabela para excluir.");
        }
    }

    // Método para abrir a janela de formulário (tanto para novo quanto para editar)
    private boolean mostrarFormularioPostagem(Postagem postagem) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PostFormulario.fxml"));
            GridPane page = (GridPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário de Postagem");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PostFormularioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPostagem(postagem);

            dialogStage.showAndWait();

            return controller.isSalvo();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}