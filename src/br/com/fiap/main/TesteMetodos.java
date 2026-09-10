package br.com.fiap.main;

import br.com.fiap.bean.Cliente;
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

        System.out.println("======== Resultados ========");
        System.out.println(cliente.validarCnpj(cnpj));
        System.out.println(cliente2.validarCnpj(cnpj2));
        System.out.println(cliente3.validarCnpj(cnpj3));

        // ============= 2°Teste: metodo validarCPF() ============= //


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

        System.out.println("======== Resultados ========");
        System.out.println(pessoa.validarEmail(email));
        System.out.println(pessoa2.validarEmail(email2));
        System.out.println(pessoa3.validarEmail(email3));
        System.out.println(pessoa4.validarEmail(email4));

        // ============= 4°Teste: metodo () ============= //

    }
}
