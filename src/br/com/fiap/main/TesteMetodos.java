package br.com.fiap.main;

import br.com.fiap.bean.Cliente;

public class TesteMetodos {
    public static void main(String[] args) {
        // ============= Teste do metodo validarCNPJ() =============
        String cnpj = "12345678000195";
        String cnpj2 = "";
        String cnpj3 = "4556328792";

        Cliente cliente = new Cliente();
        cliente.setCnpj(cnpj);

        Cliente cliente1 = new Cliente();
        cliente1.setCnpj(cnpj2);

        Cliente cliente2 = new Cliente();
        cliente2.setCnpj(cnpj3);

        // ======== Resultados ========
        System.out.println(cliente.validarCnpj(cnpj));
        System.out.println(cliente1.validarCnpj(cnpj2));
        System.out.println(cliente2.validarCnpj(cnpj3));

    }
}
