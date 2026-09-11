package br.com.fiap.view;

import br.com.fiap.controller.FuncionarioController;
import br.com.fiap.model.bean.Funcionario;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ManipularFuncionario {
    private final Scanner scan = new Scanner(System.in);
    private final FuncionarioController funcionarioController;

    public ManipularFuncionario(Connection con){
        this.funcionarioController = new FuncionarioController(con);
    }

    public void submenuFuncionario(){
        int opcao = 0;
        while (opcao != 5){
            try {
                System.out.println("\n===== GERENCIAMENTO DE FUNCIONÁRIOS =====" +
                        "\n1.Cadastrar Funcionário" +
                        "\n2.Listar Funcionários" +
                        "\n3.Alterar inf. de Funcionário" +
                        "\n4.Excluir Funcionário" +
                        "\n5.Voltar para o menu principal");
                System.out.print("Insira opção: ");
                opcao = Integer.parseInt(scan.nextLine().trim());

                switch (opcao){
                    case 1:
                        String nome, email, cpf, senha;
                        System.out.println("Informe:");
                        System.out.print("Nome: ");
                        nome = scan.nextLine();
                        System.out.print("Email: ");
                        email = scan.nextLine();
                        System.out.print("CPF: ");
                        cpf = scan.nextLine();
                        System.out.print("Senha: ");
                        senha = scan.nextLine();

                        System.out.println(funcionarioController.cadastrar(nome, email, cpf, senha));
                        break;

                    case 2:
                        List<Funcionario> listaFuncionarios = funcionarioController.listar();
                        if (listaFuncionarios == null || listaFuncionarios.isEmpty()){
                            System.out.println("Não tem nenhum funcionário cadastrado!");
                            break;
                        } else {
                            System.out.println("===== FUNCIONÁRIOS CADASTRADOS =====");
                            for (Funcionario f : listaFuncionarios){
                                System.out.println("ID: " + f.getIdFuncionario());
                                System.out.println(f.apresentar());
                                System.out.println("-------------------------");
                            }
                            System.out.println("Total: " + listaFuncionarios.size() + " Funcionário(s)");
                        }
                        break;

                    case 3:
                        System.out.print("Informe o ID do funcionário que deseja alterar: ");
                        int idAlterar = Integer.parseInt(scan.nextLine().trim());

                        Funcionario funcAlterar = funcionarioController.buscarPorId(idAlterar);
                        if (funcAlterar == null){
                            System.out.println("Funcionário não encontrado com o ID " + idAlterar);
                            break;
                        }

                        System.out.println("===== DADOS ATUAIS =====");
                        System.out.println(funcAlterar.apresentar());
                        System.out.println("------------------------");
                        System.out.println("Informe os novos dados (Enter para manter o atual):");

                        String novoNome, novoEmail, novoCpf, novaSenha;
                        System.out.print("Nome: ");
                        novoNome = scan.nextLine();
                        System.out.print("Email: ");
                        novoEmail = scan.nextLine();
                        System.out.print("CPF: ");
                        novoCpf = scan.nextLine();
                        System.out.print("Senha: ");
                        novaSenha = scan.nextLine();

                        System.out.println(funcionarioController.alterar(idAlterar, novoNome, novoEmail, novoCpf, novaSenha));
                        break;

                    case 4:
                        System.out.print("Informe o ID do funcionário que deseja excluir: ");
                        int idExcluir = Integer.parseInt(scan.nextLine().trim());

                        Funcionario funcExcluir = funcionarioController.buscarPorId(idExcluir);
                        if (funcExcluir == null){
                            System.out.println("Funcionário não encontrado com o ID " + idExcluir);
                            break;
                        }

                        System.out.println("===== FUNCIONÁRIO A SER EXCLUÍDO =====");
                        System.out.println(funcExcluir.apresentar());
                        System.out.print("Confirma a exclusão? (S/N): ");
                        String confirma = scan.nextLine();

                        if (confirma.equalsIgnoreCase("S")){
                            System.out.println(funcionarioController.excluir(idExcluir));
                        } else {
                            System.out.println("Exclusão cancelada.");
                        }
                        break;

                    case 5:
                        System.out.println("Voltando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Insira somente valores numéricos!");
            } catch (Exception e){
                System.out.println("Erro! " + e.getMessage());
            }
        }
    }
}