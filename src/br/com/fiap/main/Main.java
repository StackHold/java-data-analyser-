package br.com.fiap.main;

import br.com.fiap.bean.Cliente;
import br.com.fiap.bean.Funcionario;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nome, email, cnpj, cpf, segmento, transcricao, classificacao;
        int pontuacao;
        LocalDate data;
        try {
            System.out.println("======Seja Bem Vindo Funcionário======" +
                    "\nInsira as seguintes informações a baixo para prosseguir.\nInforme: nome, email e CPF.");
            nome = scan.nextLine();
            email = scan.nextLine();
            cpf = scan.nextLine();
            Funcionario funcio = new Funcionario(nome, email, cpf);
            System.out.println("======Bem vindo!======\n" + funcio.apresentar());
            System.out.println("--------------------------------------------");
            System.out.println("Insira as informações do cliente.\nnome, email, CPNJ e segmento da empresa");
            nome = scan.nextLine();
            email = scan.nextLine();
            cnpj = scan.nextLine();
            segmento = scan.nextLine();
            Cliente cliente = new Cliente(nome, email, cnpj, segmento);
            System.out.println("======Informações Cadastradas!======\n" + cliente.apresentar());
            System.out.println("--------------------------------------------");
            System.out.println("Insira a transcrição da reunião realizada:");
            transcricao = scan.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
