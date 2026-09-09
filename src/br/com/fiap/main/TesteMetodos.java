package br.com.fiap.main;

import br.com.fiap.bean.Cliente;

public class TesteMetodos {
    public static void main(String[] args) {
        String cnpj = "12345678000195";
        Cliente cliente = new Cliente();
        cliente.setCnpj(cnpj);
        System.out.println(cliente.validarCnpj(cnpj));
    }
}
