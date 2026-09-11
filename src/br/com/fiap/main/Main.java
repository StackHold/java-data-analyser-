package br.com.fiap.main;

import br.com.fiap.controller.FuncionarioController;
import br.com.fiap.model.bean.Funcionario;
import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.view.ManipularCliente;
import br.com.fiap.view.ManipularFuncionario;
import br.com.fiap.view.ManipularResultadoAnalise;
import br.com.fiap.view.ManipularReuniao;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);
    private static Funcionario funcionarioLogado;

    public static void main(String[] args) {


        Connection con = ConnectionFactory.abrirConexao();
        if (con == null) {
            System.out.println("Não foi possível conectar ao banco de dados. Encerrando.");
            return;
        }

        FuncionarioController funcionarioController = new FuncionarioController(con);

        if (!iniciarSessao(funcionarioController)) {
            System.out.println("Acesso negado. Encerrando o sistema.");
            ConnectionFactory.fecharConexao(con);
            return;
        }


        int opcao = 0;
        while (opcao != 5) {
            try {
                System.out.println("\n===== MENU PRINCIPAL =====" +
                        "\nUsuário: " + funcionarioLogado.getNome() +
                        "\n1.Gerenciar Clientes" +
                        "\n2.Gerenciar Reuniões" +
                        "\n3.Gerenciar Análises" +
                        "\n4.Gerenciar Funcionários" +
                        "\n5.Finalizar programa");
                System.out.print("Insira opção: ");
                opcao = Integer.parseInt(scan.nextLine().trim());

                switch (opcao) {
                    case 1:
                        new ManipularCliente(con).submenuCliente();
                        break;
                    case 2:
                        new ManipularReuniao(con).submenuReuniao();
                        break;
                    case 3:
                        new ManipularResultadoAnalise(con).submenuResultadoAnalise();
                        break;
                    case 4:
                        new ManipularFuncionario(con).submenuFuncionario();
                        break;
                    case 5:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Insira somente valores numéricos!");
            } catch (Exception e) {
                System.out.println("Erro! " + e.getMessage());
            }
        }

        ConnectionFactory.fecharConexao(con);
        System.out.println("Muito obrigado e volte sempre!");
    }

    private static boolean iniciarSessao(FuncionarioController funcionarioController) {
        int escolha = 0;
        while (escolha != 3) {
            try {
                System.out.println("\n=========================================" +
                        "\n   SISTEMA DE ANÁLISE DE REUNIÕES" +
                        "\n=========================================" +
                        "\n1.Fazer login" +
                        "\n2.Criar perfil de funcionário" +
                        "\n3.Sair");
                System.out.print("Insira opção: ");
                escolha = Integer.parseInt(scan.nextLine().trim());

                switch (escolha) {
                    case 1:
                        if (fazerLogin(funcionarioController)) {
                            return true;
                        }
                        break;
                    case 2:
                        criarPerfil(funcionarioController);
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Insira somente valores numéricos!");
            } catch (Exception e) {
                System.out.println("Erro! " + e.getMessage());
            }
        }
        return false;
    }


    private static boolean fazerLogin(FuncionarioController funcionarioController) {
        System.out.println("\n===== LOGIN =====");

        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            System.out.print("CPF: ");
            String cpf = scan.nextLine();
            System.out.print("Senha: ");
            String senha = scan.nextLine();


            Funcionario funcionario = funcionarioController.autenticar(cpf, senha);

            if (funcionario != null) {
                funcionarioLogado = funcionario;
                System.out.println("\nBem-vindo, " + funcionario.getNome() + "!");
                return true;
            }

            System.out.println("CPF ou senha incorretos. Tentativa " + tentativa + "/3");
        }

        System.out.println("Número de tentativas excedido.");
        return false;
    }

    private static void criarPerfil(FuncionarioController funcionarioController) {
        System.out.println("\n===== CRIAÇÃO DE PERFIL =====");

        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("CPF: ");
        String cpf = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();

        System.out.println(funcionarioController.cadastrar(nome, email, cpf, senha));
    }
}