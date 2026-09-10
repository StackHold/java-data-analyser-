package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.time.LocalDate;
import java.util.Map;

public class TesteMetodos {
    public static void main(String[] args) {
        System.out.println("\n============= 1°Teste: metodo validarCNPJ() =============\n");
        String cnpj = "12345678000195";
        String cnpj2 = "";
        String cnpj3 = "4556328792";

        Cliente cliente = new Cliente();
        cliente.setCnpj(cnpj);

        Cliente cliente2 = new Cliente();
        cliente2.setCnpj(cnpj2);

        Cliente cliente3 = new Cliente();
        cliente3.setCnpj(cnpj3);

        System.out.println("Resultados:");
        System.out.println("------------------------");
        System.out.println(cliente.validarCnpj(cnpj));
        System.out.println(cliente2.validarCnpj(cnpj2));
        System.out.println(cliente3.validarCnpj(cnpj3));

        System.out.println("\n============= 2°Teste: metodo validarCPF() =============\n");
        String cpf  = "52998224725";
        String cpf2 = "529.982.247-25";
        String cpf3 = "390.533.447-05";
        String cpf4 = "529.982.247-26";
        String cpf5 = "111.111.111-11";
        String cpf6 = "123456789";
        String cpf7 = "abcdefghijk";

        System.out.println("Resultados:");
        System.out.println("------------------------");
        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }

        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf2));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }

        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf3));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }

        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf4));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }

        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf5));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }

        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf6));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }

        try {
            System.out.println("VÁLIDO: " + Funcionario.validaCpf(cpf7));
        } catch (IllegalArgumentException e) {
            System.out.println("REJEITADO: " + e.getMessage());
        }


        System.out.println("\n============= 3°Teste: metodo validarEmail() =============\n");
        String email = "Astrogildo@gmail.com";
        String email2 = "@gmail.com";
        String email3 = "Astrogildo@";
        String email4 = "";

        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setEmail(email2);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setEmail(email3);

        Pessoa pessoa4 = new Pessoa();
        pessoa4.setEmail(email4);

        System.out.println("Resultados:");
        System.out.println("------------------------");
        System.out.println(pessoa.validarEmail(email));
        System.out.println(pessoa2.validarEmail(email2));
        System.out.println(pessoa3.validarEmail(email3));
        System.out.println(pessoa4.validarEmail(email4));

        System.out.println("\n============= 4°Teste: metodo classificarReuniao() =============\n");
        String transcricao  = "O cliente aprovou a proposta e quer fechar o contrato.";
        String transcricao2 = "Apresentamos o Fluig da Totvs para a equipe.";
        String transcricao3 = "Conversamos sobre o clima e remarcamos para a próxima semana.";
        String transcricao4 = "O cliente comentou que está avaliando o SAP.";
        String transcricao5 = "O cliente acha o valor caro.";
        String transcricao6 = "Aprovaram a proposta, querem fechar a implementação do Fluig da Totvs, mas acharam caro e pensam em cancelar.";

        Cliente clienteTeste = new Cliente("TOTVS SA", "contato@totvs.com", "12345678000195", "Tecnologia");

        Reuniao reuniao  = new Reuniao(LocalDate.now(), transcricao,  clienteTeste);
        Reuniao reuniao2 = new Reuniao(LocalDate.now(), transcricao2, clienteTeste);
        Reuniao reuniao3 = new Reuniao(LocalDate.now(), transcricao3, clienteTeste);
        Reuniao reuniao4 = new Reuniao(LocalDate.now(), transcricao4, clienteTeste);
        Reuniao reuniao5 = new Reuniao(LocalDate.now(), transcricao5, clienteTeste);
        Reuniao reuniao6 = new Reuniao(LocalDate.now(), transcricao6, clienteTeste);

        Map<String, Categoria> palavras = Map.of(
                "proposta",      Categoria.OPORTUNIDADE,
                "fechar",        Categoria.OPORTUNIDADE,
                "implementação", Categoria.OPORTUNIDADE,
                "totvs",         Categoria.PRODUTO_TOTVS,
                "fluig",         Categoria.PRODUTO_TOTVS,
                "sap",           Categoria.CONCORRENTE,
                "senior",        Categoria.CONCORRENTE,
                "caro",          Categoria.RISCO_CHURN,
                "cancelar",      Categoria.RISCO_CHURN,
                "insatisfeito",  Categoria.RISCO_CHURN
        );

        AnalisadorTranscricao analisador = new AnalisadorTranscricao();
        analisador.setPalavraChave(palavras);

        System.out.println("Resultados:");
        System.out.println("------------------------");
        ResultadoAnalise resultado  = analisador.processar(reuniao);
        System.out.println(resultado.getPontuacao() + " -> " + resultado.getClassificacao());

        ResultadoAnalise resultado2 = analisador.processar(reuniao2);
        System.out.println(resultado2.getPontuacao() + " -> " + resultado2.getClassificacao());

        ResultadoAnalise resultado3 = analisador.processar(reuniao3);
        System.out.println(resultado3.getPontuacao() + " -> " + resultado3.getClassificacao());

        ResultadoAnalise resultado4 = analisador.processar(reuniao4);
        System.out.println(resultado4.getPontuacao() + " -> " + resultado4.getClassificacao());

        ResultadoAnalise resultado5 = analisador.processar(reuniao5);
        System.out.println(resultado5.getPontuacao() + " -> " + resultado5.getClassificacao());

        ResultadoAnalise resultado6 = analisador.processar(reuniao6);
        System.out.println(resultado6.getPontuacao() + " -> " + resultado6.getClassificacao());

        ResultadoAnalise resultado7 = analisador.processar(reuniao);
        System.out.println(resultado7.getPontuacao() + " -> " + resultado7.getClassificacao());
    }
}
