package br.com.fiap.main;

import br.com.fiap.bean.Cliente;
import br.com.fiap.bean.Funcionario;
import br.com.fiap.bean.Pessoa;

public class TesteMetodos {
    public static void main(String[] args) {
        System.out.println("============= 1°Teste: metodo validarCNPJ() =============\n");
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
        System.out.println(cliente.validarCnpj(cnpj));
        System.out.println(cliente2.validarCnpj(cnpj2));
        System.out.println(cliente3.validarCnpj(cnpj3));

        System.out.println("============= 2°Teste: metodo validarCPF() =============\n");
        String cpf  = "52998224725";
        String cpf2 = "529.982.247-25";
        String cpf3 = "390.533.447-05";
        String cpf4 = "529.982.247-26";
        String cpf5 = "111.111.111-11";
        String cpf6 = "123456789";
        String cpf7 = "abcdefghijk";

        System.out.println("Resultados:");

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


        System.out.println("============= 3°Teste: metodo validarEmail() =============\n");
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
        System.out.println(pessoa.validarEmail(email));
        System.out.println(pessoa2.validarEmail(email2));
        System.out.println(pessoa3.validarEmail(email3));
        System.out.println(pessoa4.validarEmail(email4));

        // ============= 4°Teste: metodo () ============= //

    }
}
