package com.blog.main;

import java.util.List;
import java.util.Scanner;
import com.blog.dao.PostagemDAO;
import com.blog.model.Autor;
import com.blog.model.Postagem;

public class App {
    public static void main(String[] args) {
        PostagemDAO postagemDAO = new PostagemDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        // O loop principal que mantém a aplicação rodando.
        while (opcao != 0) {
            System.out.println("\n--- BLOG PESSOAL SIMPLES ---");
            System.out.println("1. Criar nova postagem");
            System.out.println("2. Listar todas as postagens");
            System.out.println("3. Editar postagem");
            System.out.println("4. Excluir postagem");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                // Lê a linha inteira para evitar erros de buffer do Scanner.
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERRO: Opção inválida. Por favor, digite um número.");
                continue; // Pula o resto do loop e volta para o menu.
            }

            // Seleciona a ação baseada na escolha do usuário.
            switch (opcao) {
                case 1:
                    System.out.println("\n== Criar Nova Postagem ==");
                    System.out.print("Digite o título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o conteúdo: ");
                    String conteudo = scanner.nextLine();
                    System.out.print("Digite o ID do autor: ");
                    int autorId = Integer.parseInt(scanner.nextLine());

                    Autor autor = new Autor();
                    autor.setId(autorId);

                    Postagem novaPostagem = new Postagem();
                    novaPostagem.setTitulo(titulo);
                    novaPostagem.setConteudo(conteudo);
                    novaPostagem.setAutor(autor);
                    
                    postagemDAO.criarPostagem(novaPostagem);
                    break;
                
                case 2:
                    System.out.println("\n== Todas as Postagens ==");
                    List<Postagem> postagens = postagemDAO.listarTodas();
                    if (postagens.isEmpty()) {
                        System.out.println("Nenhuma postagem encontrada.");
                    } else {
                        for (Postagem p : postagens) {
                            System.out.println("---------------------------------");
                            System.out.println("ID: " + p.getId() + " | Título: " + p.getTitulo() + " | Autor: " + p.getAutor().getNome());
                        }
                        System.out.println("---------------------------------");
                    }
                    break;

                case 3:
                    System.out.println("\n== Editar Postagem ==");
                    System.out.print("Digite o ID da postagem para editar: ");
                    int idParaEditar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o novo título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Digite o novo conteúdo: ");
                    String novoConteudo = scanner.nextLine();

                    Postagem postagemEditada = new Postagem();
                    postagemEditada.setId(idParaEditar);
                    postagemEditada.setTitulo(novoTitulo);
                    postagemEditada.setConteudo(novoConteudo);

                    postagemDAO.atualizarPostagem(postagemEditada);
                    break;

                case 4:
                    System.out.println("\n== Excluir Postagem ==");
                    System.out.print("Digite o ID da postagem para excluir: ");
                    int idParaExcluir = Integer.parseInt(scanner.nextLine());
                    postagemDAO.excluirPostagem(idParaExcluir);
                    break;
                    
                case 0:
                    System.out.println("\nSaindo do sistema...");
                    break;

                default:
                    System.out.println("ERRO: Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }
}