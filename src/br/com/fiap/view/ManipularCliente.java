package br.com.fiap.view;

import br.com.fiap.controller.ClienteController;
import br.com.fiap.model.bean.Cliente;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipularCliente {
    private final Scanner scan = new Scanner(System.in);
    private final ClienteController clienteController;

    public ManipularCliente(Connection con){
        this.clienteController = new ClienteController(con);
    }

    public void submenuCliente(){
        int opcao = 0;
        while (opcao != 5){
            try {
                System.out.println("\n===== GERENCIAMENTO DE CLIENTES =====" +
                        "\n1.Cadastrar Cliente" +
                        "\n2.Listar Clientes" +
                        "\n3.Alterar inf. de Cliente" +
                        "\n4.Excluir Cliente" +
                        "\n5.Voltar para o menu principal");
                System.out.print("Insira opção: ");
                opcao = Integer.parseInt(scan.nextLine().trim());

                switch (opcao){
                    case 1:
                        String nome, email, cnpj, segmento;
                        System.out.println("Informe:");
                        System.out.print("Nome: ");
                        nome = scan.nextLine();
                        System.out.print("Email: ");
                        email = scan.nextLine();
                        System.out.print("CNPJ: ");
                        cnpj = scan.nextLine();
                        System.out.print("Segmento: ");
                        segmento = scan.nextLine();

                        System.out.println(clienteController.cadastrar(nome, email, cnpj, segmento));
                        break;

                    case 2:
                        ArrayList<Cliente> listaClientes = clienteController.listar();
                        if (listaClientes.isEmpty()){
                            System.out.println("Não tem nenhum cliente cadastrado!");
                            break;
                        } else {
                            System.out.println("===== CLIENTES CADASTRADOS =====");
                            for (Cliente c : listaClientes){
                                System.out.println("ID: " + c.getIdCliente());
                                System.out.println(c.apresentar());
                                System.out.println("-------------------------");
                            }
                            System.out.println("Total: " + listaClientes.size() + " Cliente(s)");
                        }
                        break;

                    case 3:
                        if (clienteController.listar().isEmpty()){
                            System.out.println("Não tem clientes cadastrados!");
                            break;
                        }

                        System.out.print("Informe o ID do cliente que deseja alterar: ");
                        int idAlterar = Integer.parseInt(scan.nextLine().trim());

                        Cliente clienteAlterar = clienteController.buscarPorId(idAlterar);
                        if (clienteAlterar == null){
                            System.out.println("Cliente não encontrado com o ID " + idAlterar);
                            break;
                        }

                        System.out.println("===== DADOS ATUAIS =====");
                        System.out.println(clienteAlterar.apresentar());
                        System.out.println("------------------------");
                        System.out.println("Informe os novos dados (Enter para manter o atual):");

                        String novoNome, novoEmail, novoCnpj, novoSegmento;
                        System.out.print("Nome: ");
                        novoNome = scan.nextLine();
                        System.out.print("Email: ");
                        novoEmail = scan.nextLine();
                        System.out.print("CNPJ: ");
                        novoCnpj = scan.nextLine();
                        System.out.print("Segmento: ");
                        novoSegmento = scan.nextLine();

                        System.out.println(clienteController.alterar(idAlterar, novoNome, novoEmail, novoCnpj, novoSegmento));
                        break;

                    case 4:
                        if (clienteController.listar().isEmpty()){
                            System.out.println("Não tem clientes cadastrados!");
                            break;
                        }

                        System.out.print("Informe o ID do cliente que deseja excluir: ");
                        int idExcluir = Integer.parseInt(scan.nextLine().trim());

                        Cliente clienteExcluir = clienteController.buscarPorId(idExcluir);
                        if (clienteExcluir == null){
                            System.out.println("Cliente não encontrado com o ID " + idExcluir);
                            break;
                        }

                        System.out.println("===== CLIENTE A SER EXCLUÍDO =====");
                        System.out.println(clienteExcluir.apresentar());
                        System.out.print("Confirma a exclusão? (S/N): ");
                        String confirma = scan.nextLine();

                        if (confirma.equalsIgnoreCase("S")){
                            System.out.println(clienteController.excluir(idExcluir));
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