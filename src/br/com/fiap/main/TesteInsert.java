package br.com.fiap.main;

import br.com.fiap.bean.Cliente;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.ConnectionFactory;

import java.sql.Connection;

public class TesteInsert {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();

        Cliente cliente = new Cliente();
        cliente.setCnpj("0000-111/1");
        cliente.setSegmento("Loja de roupa");
        cliente.setNome("Fashion");
        cliente.setEmail("Fashion@gmail.com");

        ClienteDAO clienteDAO = new ClienteDAO(con);
        clienteDAO.inserir(cliente);

        ConnectionFactory.fecharConexao(con);
    }
}
