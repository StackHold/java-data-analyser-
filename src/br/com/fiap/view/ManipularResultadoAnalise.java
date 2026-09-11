package br.com.fiap.view;

import br.com.fiap.controller.ResultadoAnaliseController;
import br.com.fiap.controller.ReuniaoController;
import br.com.fiap.model.bean.ResultadoAnalise;
import br.com.fiap.model.bean.Reuniao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipularResultadoAnalise {
    private final Scanner scan = new Scanner(System.in);
    private final ResultadoAnaliseController resultadoController;
    private final ReuniaoController reuniaoController;

    public ManipularResultadoAnalise(Connection con){
        this.resultadoController = new ResultadoAnaliseController(con);
        this.reuniaoController = new ReuniaoController(con);
    }

    public void submenuResultadoAnalise(){
        int opcao = 0;
        while (opcao != 5){
            try {
                System.out.println("\n===== GERENCIAMENTO DAS ANÁLISES =====" +
                        "\n1.Analisar uma Reunião" +
                        "\n2.Listar Análises" +
                        "\n3.Reprocessar uma Análise" +
                        "\n4.Excluir Análise" +
                        "\n5.Voltar para o menu principal");
                System.out.print("Insira opção: ");
                opcao = Integer.parseInt(scan.nextLine().trim());

                switch (opcao){
                    case 1:
                        ArrayList<Reuniao> reunioes = reuniaoController.listar();
                        if (reunioes.isEmpty()){
                            System.out.println("Cadastre uma reunião antes de analisar!");
                            break;
                        }

                        System.out.println("===== REUNIÕES DISPONÍVEIS =====");
                        for (Reuniao r : reunioes){
                            System.out.println("ID: " + r.getIdReuniao()
                                    + " - Cliente: " + r.getCliente().getNome());
                        }

                        System.out.print("Informe o ID da reunião a analisar: ");
                        int idReuniao = Integer.parseInt(scan.nextLine().trim());

                        System.out.print("Sumário/observações da reunião: ");
                        String sumario = scan.nextLine();

                        System.out.println(resultadoController.analisar(idReuniao, sumario));


                        ResultadoAnalise gerado = resultadoController.buscarPorReuniao(idReuniao);
                        if (gerado != null){
                            gerado.exibirResultado();
                        }
                        break;

                    case 2:
                        ArrayList<ResultadoAnalise> listaResultados = resultadoController.listar();
                        if (listaResultados.isEmpty()){
                            System.out.println("Não tem nenhuma análise cadastrada!");
                            break;
                        } else {
                            System.out.println("===== ANÁLISES CADASTRADAS =====");
                            for (ResultadoAnalise ra : listaResultados){
                                ra.exibirResultado();
                                System.out.println("-------------------------");
                            }
                            System.out.println("Total: " + listaResultados.size() + " Análise(s)");
                        }
                        break;

                    case 3:
                        if (resultadoController.listar().isEmpty()){
                            System.out.println("Não tem análises cadastradas!");
                            break;
                        }

                        System.out.print("Informe o ID da análise que deseja reprocessar: ");
                        int idAlterar = Integer.parseInt(scan.nextLine().trim());

                        ResultadoAnalise analiseAlterar = resultadoController.buscarPorId(idAlterar);
                        if (analiseAlterar == null){
                            System.out.println("Análise não encontrada com o ID " + idAlterar);
                            break;
                        }

                        System.out.println("===== ANÁLISE ATUAL =====");
                        analiseAlterar.exibirResultado();
                        System.out.println("------------------------");
                        System.out.println("A transcrição da reunião será analisada novamente.");

                        System.out.print("Novo sumário (Enter para manter o atual): ");
                        String novoSumario = scan.nextLine();

                        System.out.println(resultadoController.alterar(idAlterar, novoSumario));

                        ResultadoAnalise atualizado = resultadoController.buscarPorId(idAlterar);
                        if (atualizado != null){
                            System.out.println("===== ANÁLISE ATUALIZADA =====");
                            atualizado.exibirResultado();
                        }
                        break;

                    case 4:
                        if (resultadoController.listar().isEmpty()){
                            System.out.println("Não tem análises cadastradas!");
                            break;
                        }

                        System.out.print("Informe o ID da análise que deseja excluir: ");
                        int idExcluir = Integer.parseInt(scan.nextLine().trim());

                        ResultadoAnalise analiseExcluir = resultadoController.buscarPorId(idExcluir);
                        if (analiseExcluir == null){
                            System.out.println("Análise não encontrada com o ID " + idExcluir);
                            break;
                        }

                        System.out.println("===== ANÁLISE A SER EXCLUÍDA =====");
                        analiseExcluir.exibirResultado();
                        System.out.print("Confirma a exclusão? (S/N): ");
                        String confirma = scan.nextLine();

                        if (confirma.equalsIgnoreCase("S")){
                            System.out.println(resultadoController.excluir(idExcluir));
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