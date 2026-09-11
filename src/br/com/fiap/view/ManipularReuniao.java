package br.com.fiap.view;

import br.com.fiap.controller.ClienteController;
import br.com.fiap.controller.ReuniaoController;
import br.com.fiap.model.bean.Cliente;
import br.com.fiap.model.bean.Reuniao;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipularReuniao {
    private final Scanner scan = new Scanner(System.in);
    private final ReuniaoController reuniaoController;
    private final ClienteController clienteController;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ManipularReuniao(Connection con){
        this.reuniaoController = new ReuniaoController(con);
        this.clienteController = new ClienteController(con);
    }

    public void submenuReuniao(){
        int opcao = 0;
        while (opcao != 5){
            try {
                System.out.println("\n===== GERENCIAMENTO DE REUNIÕES =====" +
                        "\n1.Cadastrar Reunião" +
                        "\n2.Listar Reuniões" +
                        "\n3.Alterar inf. de Reunião" +
                        "\n4.Excluir Reunião" +
                        "\n5.Voltar para o menu principal");
                System.out.print("Insira opção: ");
                opcao = Integer.parseInt(scan.nextLine().trim());

                switch (opcao){
                    case 1:
                        ArrayList<Cliente> clientes = clienteController.listar();
                        if (clientes.isEmpty()){
                            System.out.println("Cadastre um cliente antes de registrar uma reunião!");
                            break;
                        }

                        System.out.println("===== CLIENTES DISPONÍVEIS =====");
                        for (Cliente c : clientes){
                            System.out.println("ID: " + c.getIdCliente() + " - " + c.getNome());
                        }

                        System.out.print("Informe o ID do cliente: ");
                        int idCliente = Integer.parseInt(scan.nextLine().trim());

                        System.out.print("Data da reunião (dd/MM/aaaa) ou Enter para hoje: ");
                        String dataDigitada = scan.nextLine();
                        LocalDate data = dataDigitada.isBlank()
                                ? LocalDate.now()
                                : LocalDate.parse(dataDigitada.trim(), fmt);

                        System.out.print("Cole a transcrição da reunião: ");
                        String transcricao = scan.nextLine();

                        System.out.println(reuniaoController.cadastrar(idCliente, data, transcricao));
                        break;

                    case 2:
                        ArrayList<Reuniao> listaReunioes = reuniaoController.listar();
                        if (listaReunioes.isEmpty()){
                            System.out.println("Não tem nenhuma reunião cadastrada!");
                            break;
                        } else {
                            System.out.println("===== REUNIÕES CADASTRADAS =====");
                            for (Reuniao r : listaReunioes){
                                System.out.println(r.exibirInfo());
                                System.out.println("-------------------------");
                            }
                            System.out.println("Total: " + listaReunioes.size() + " Reunião(ões)");
                        }
                        break;

                    case 3:
                        if (reuniaoController.listar().isEmpty()){
                            System.out.println("Não tem reuniões cadastradas!");
                            break;
                        }

                        System.out.print("Informe o ID da reunião que deseja alterar: ");
                        int idAlterar = Integer.parseInt(scan.nextLine().trim());

                        Reuniao reuniaoAlterar = reuniaoController.buscarPorId(idAlterar);
                        if (reuniaoAlterar == null){
                            System.out.println("Reunião não encontrada com o ID " + idAlterar);
                            break;
                        }

                        System.out.println("===== DADOS ATUAIS =====");
                        System.out.println(reuniaoAlterar.exibirInfo());
                        System.out.println("------------------------");
                        System.out.println("Informe os novos dados (Enter para manter o atual):");

                        System.out.print("ID do cliente: ");
                        String novoIdCliente = scan.nextLine();
                        int idClienteNovo = novoIdCliente.isBlank() ? 0 : Integer.parseInt(novoIdCliente.trim());

                        System.out.print("Data (dd/MM/aaaa): ");
                        String novaDataTexto = scan.nextLine();
                        LocalDate novaData = novaDataTexto.isBlank()
                                ? null
                                : LocalDate.parse(novaDataTexto.trim(), fmt);

                        System.out.print("Transcrição: ");
                        String novaTranscricao = scan.nextLine();

                        System.out.println(reuniaoController.alterar(idAlterar, idClienteNovo, novaData, novaTranscricao));
                        break;

                    case 4:
                        if (reuniaoController.listar().isEmpty()){
                            System.out.println("Não tem reuniões cadastradas!");
                            break;
                        }

                        System.out.print("Informe o ID da reunião que deseja excluir: ");
                        int idExcluir = Integer.parseInt(scan.nextLine().trim());

                        Reuniao reuniaoExcluir = reuniaoController.buscarPorId(idExcluir);
                        if (reuniaoExcluir == null){
                            System.out.println("Reunião não encontrada com o ID " + idExcluir);
                            break;
                        }

                        System.out.println("===== REUNIÃO A SER EXCLUÍDA =====");
                        System.out.println(reuniaoExcluir.exibirInfo());
                        System.out.print("Confirma a exclusão? (S/N): ");
                        String confirma = scan.nextLine();

                        if (confirma.equalsIgnoreCase("S")){
                            System.out.println(reuniaoController.excluir(idExcluir));
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
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/aaaa.");
            } catch (Exception e){
                System.out.println("Erro! " + e.getMessage());
            }
        }
    }
}