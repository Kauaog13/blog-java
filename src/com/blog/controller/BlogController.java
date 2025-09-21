package com.blog.controller;

import com.blog.dao.PostagemDAO;
import com.blog.model.Autor;
import com.blog.model.Postagem;
import com.blog.view.PostFormularioController;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BlogController {

    @FXML private TableView<Postagem> tabelaPostagens;
    @FXML private TableColumn<Postagem, Integer> colunaId;
    @FXML private TableColumn<Postagem, String> colunaTitulo;
    @FXML private TableColumn<Postagem, String> colunaAutor;
    @FXML private Button botaoNovo;
    @FXML private Button botaoEditar;
    @FXML private Button botaoExcluir;

    private PostagemDAO postagemDAO;
    private Autor usuarioLogado;

    @FXML
    public void initialize() {
        postagemDAO = new PostagemDAO();
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaAutor.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor().getNome()));

        botaoNovo.setDisable(true);
        botaoEditar.setDisable(true);
        botaoExcluir.setDisable(true);

        carregarPostagens();
    }

    public void initData(Autor autor) {
        this.usuarioLogado = autor;
        botaoNovo.setDisable(false);
        botaoEditar.setDisable(false);
        botaoExcluir.setDisable(false);
    }

    @FXML
    private void handleBotaoNovo() {
        Postagem novaPostagem = new Postagem();
        novaPostagem.setAutor(this.usuarioLogado); 
        
        boolean salvo = mostrarFormularioPostagem(novaPostagem);
        if (salvo) {
            postagemDAO.criarPostagem(novaPostagem);
            carregarPostagens();
        }
    }

    @FXML
    private void handleBotaoEditar() {
        Postagem selecionada = tabelaPostagens.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            boolean salvo = mostrarFormularioPostagem(selecionada);
            if (salvo) {
                postagemDAO.atualizarPostagem(selecionada);
                carregarPostagens();
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

    private void carregarPostagens() {
        ObservableList<Postagem> postagens = FXCollections.observableArrayList(postagemDAO.listarTodas());
        tabelaPostagens.setItems(postagens);
    }

    private boolean mostrarFormularioPostagem(Postagem postagem) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/blog/view/PostFormulario.fxml"));
            GridPane page = (GridPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário de Postagem");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner((Stage) botaoNovo.getScene().getWindow());
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
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}