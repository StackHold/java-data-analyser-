package br.com.fiap.submenus;

import br.com.fiap.bean.Cliente;
import br.com.fiap.bean.Pessoa;
import br.com.fiap.dao.ClienteDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipularCliente {
    private final Scanner scan = new Scanner(System.in);
    private final ClienteDAO clienteDAO;

    public ManipularCliente(Connection con){
        this.clienteDAO = new ClienteDAO(con);
    }

    public void submenuCliente(){
        int opcao = 0;
        while (opcao != 5){
            try {
                System.out.println("===== GERENCIAMENTO DE CLIENTES =====" +
                        "\n1.Cadastrar Cliente" +
                        "\n2.Listar Clientes" +
                        "\n3.Alterar inf. de Cliente" +
                        "\n4.Excluir Cliente" +
                        "\n5.Voltar para o menu principal");
                System.out.println("Insira opção: ");
                opcao = scan.nextInt();
                switch (opcao){
                    case 1:
                        String nome, email, cnpj, segmento, valida, valida2;
                        System.out.println("Informe:");
                        System.out.println("Nome:");
                        nome = scan.nextLine();
                        System.out.println("Email:");
                        email = scan.nextLine();
                        System.out.println("CNPJ:");
                        cnpj = scan.nextLine();
                        System.out.println("Segmento:");
                        segmento = scan.nextLine();

                        Cliente cliente = new Cliente(nome, email, cnpj, segmento);
                        valida = cliente.validarEmail(email);
                        if (valida != null){
                            System.out.println("ERRO: " + valida);
                            break;
                        }
                        valida2 = cliente.validarCnpj(cnpj);
                        if (valida2 != null){
                            System.out.println("ERRO:" + valida2);
                            break;
                        }
                        System.out.println(clienteDAO.inserir(cliente));
                        break;
                    case 2:
                        ArrayList<Cliente> listaClientes = clienteDAO.listarClientes();
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
                            System.out.println("Total: " + listaClientes.size() + "Cliente(s)");
                        }
                        break;
                    case 3:
                        int id;
                        if (clienteDAO.listarClientes().isEmpty()){
                            System.out.println("Não tem clientes cadastrados!");
                            break;
                        }
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("Voltando ao menu príncipal...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Insira somente valores númericos!" + e.getMessage());
            } catch (Exception e){
                System.out.println("Erro!" + e.getMessage());
            }
        }
    }
}
