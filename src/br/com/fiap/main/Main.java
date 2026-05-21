package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nome, email, cnpj, cpf, segmento, classificacao, transcricao,escolha = "sim";
        int pontuacao = 10;
        LocalDate dataAtual = LocalDate.now();
        while (escolha.equalsIgnoreCase("sim")){
            try { //Passagem de informação dos dados do Funcionário
                System.out.println("======Seja Bem Vindo Funcionário======" +
                        "\nInsira as seguintes informações a baixo para prosseguir.\nInforme: nome, email e CPF.");
                nome = scan.nextLine();
                email = scan.nextLine();
                cpf = scan.nextLine();
                Funcionario funcio = new Funcionario(nome, email, cpf);
                System.out.println("======Bem vindo======\n" + funcio.apresentar());

                System.out.println("--------------------------------------------");
                //Passagem de informação dos dados do Cliente
                System.out.println("Insira as informações do cliente.\nnome, email, CPNJ e segmento da empresa");
                nome = scan.nextLine();
                email = scan.nextLine();
                cnpj = scan.nextLine();
                segmento = scan.nextLine();
                Cliente cliente = new Cliente(nome, email, cnpj, segmento);
                System.out.println("======Informações Cadastradas======\n" + cliente.apresentar());

                System.out.println("--------------------------------------------");
                //Passagem da transcrição da reunião e exibição de suas informações
                System.out.println("Insira a transcrição da reunião realizada:");
                transcricao = scan.nextLine();
                Reuniao reuniao = new Reuniao(dataAtual, transcricao, funcio, cliente);
                System.out.println("======Reunião Cadastrada======");
                System.out.println("Informações da reunião:\n" + reuniao.exibirInfo());

                System.out.println("--------------------------------------------");
                //Exibição da classificação obtida
                AnalisadorTranscricao analisaT = new AnalisadorTranscricao();
                analisaT.setPontuacao(pontuacao); //definindo manualmente para teste
                classificacao = analisaT.classificarReuniao();
                System.out.println("======Classificação obtida======\n" + classificacao);

                System.out.println("--------------------------------------------");
                ResultadoAnalise resulAna = new ResultadoAnalise();
                resulAna.setPontuacao(pontuacao);
                resulAna.setClassificacao(classificacao);
                resulAna.exibirResultado();

            } catch (Exception e) {
                System.out.println("ERRO: Informações incorretas!");
            }
            System.out.println("Deseja continuar o programa(Sim|Não)?");
            escolha = scan.nextLine();
        }
        System.out.println("Sistema encerrado\nMuito obrigado e volte sempre!");
    }
}
