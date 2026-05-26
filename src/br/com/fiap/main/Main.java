package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nome, email, cnpj, cpf, segmento, transcricao, escolha = "sim";
        LocalDate dataAtual = LocalDate.now();
        while (escolha.equalsIgnoreCase("sim")){
            try { //Passagem de informação dos dados do Funcionário

                System.out.println("""
                        ====== Seja Bem Vindo Funcionário ======\
                        
                        Insira as seguintes informações a baixo para prosseguir.
                        Informe: nome, email e CPF.""");
                nome = scan.nextLine();
                email = scan.nextLine();
                cpf = scan.nextLine();
                Funcionario funcio = new Funcionario(nome, email, cpf);
                System.out.println("====== Bem vindo ======\n\n" + funcio.apresentar());

                System.out.println("--------------------------------------------\n");

                //Passagem de informação dos dados do Cliente
                System.out.println("Insira as informações do cliente.\nnome, email, CPNJ e segmento da empresa");
                nome = scan.nextLine();
                email = scan.nextLine();
                cnpj = scan.nextLine();
                segmento = scan.nextLine();
                Cliente cliente = new Cliente(nome, email, cnpj, segmento);
                System.out.println("====== Informações Cadastradas ======\n\n" + cliente.apresentar());

                System.out.println("--------------------------------------------");

                //Passagem da transcrição da reunião e exibição de suas informações
                System.out.println("Insira a transcrição da reunião realizada:");
                transcricao = scan.nextLine();
                Reuniao reuniao = new Reuniao(dataAtual, transcricao, funcio, cliente);
                System.out.println("====== Reunião Cadastrada ======\n\n");
                System.out.println("Informações da reunião:\n" + reuniao.exibirInfo());

                //Realizando a análise da reunião

                AnalisadorTranscricao analisaT = new AnalisadorTranscricao();

                // Settando as palavras chaves préviamente

                Map<String, Categoria> mapPalavras;
                mapPalavras = Map.of("proposta", Categoria.OPORTUNIDADE,
                        "fechar", Categoria.OPORTUNIDADE,
                        "implementação", Categoria.OPORTUNIDADE,
                        "totvs", Categoria.PRODUTO_TOTVS,
                        "fluig", Categoria.PRODUTO_TOTVS,
                        "sap", Categoria.CONCORRENTE,
                        "senior", Categoria.CONCORRENTE,
                        "caro", Categoria.RISCO_CHURN,
                        "cancelar", Categoria.RISCO_CHURN,
                        "suporte", Categoria.RISCO_CHURN);
                analisaT.setPalavraChave(mapPalavras); //definindo manualmente para teste


                System.out.println("--------------------------------------------\n");
                ResultadoAnalise resulAna = analisaT.processar(reuniao);
                resulAna.exibirResultado();

            } catch (Exception e) {
                System.out.println("ERRO: Informações incorretas! " + e.getMessage());
            }
            System.out.println("Deseja continuar o programa(Sim|Não)?");
            escolha = scan.nextLine();
        }
        System.out.println("Sistema encerrado\nMuito obrigado e volte sempre!");
    }
}
