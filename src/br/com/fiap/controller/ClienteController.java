package br.com.fiap.controller;

import br.com.fiap.model.bean.Cliente;
import br.com.fiap.model.dao.ClienteDAO;

import java.sql.Connection;
import java.util.ArrayList;


public class ClienteController {


    private static final String EMAIL_OK = "Email valido!";
    private static final String CNPJ_OK  = "O CNPJ informado é válido";

    private final ClienteDAO clienteDAO;

    public ClienteController(Connection con) {
        this.clienteDAO = new ClienteDAO(con);
    }


    public String cadastrar(String nome, String email, String cnpj, String segmento) {
        if (nome == null || nome.isBlank()) {
            return "ERRO: o nome do cliente é obrigatório.";
        }

        Cliente cliente = new Cliente(nome, email, cnpj, segmento);

        String erro = validar(cliente);
        if (erro != null) {
            return erro;
        }

        return clienteDAO.inserir(cliente);
    }


    public ArrayList<Cliente> listar() {
        return clienteDAO.listarClientes();
    }

    public Cliente buscarPorId(int idCliente) {
        return clienteDAO.buscarPorId(idCliente);
    }


    public String alterar(int idCliente, String nome, String email, String cnpj, String segmento) {
        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        if (cliente == null) {
            return "ERRO: cliente não encontrado com o ID " + idCliente;
        }


        if (nome     != null && !nome.isBlank())     cliente.setNome(nome);
        if (email    != null && !email.isBlank())    cliente.setEmail(email);
        if (cnpj     != null && !cnpj.isBlank())     cliente.setCnpj(cnpj);
        if (segmento != null && !segmento.isBlank()) cliente.setSegmento(segmento);

        String erro = validar(cliente);
        if (erro != null) {
            return erro;
        }

        return clienteDAO.atualizar(cliente);
    }


    public String excluir(int idCliente) {
        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        if (cliente == null) {
            return "ERRO: cliente não encontrado com o ID " + idCliente;
        }
        return clienteDAO.excluir(cliente);
    }



    private String validar(Cliente cliente) {
        String resultadoEmail = cliente.validarEmail(cliente.getEmail());
        if (!EMAIL_OK.equals(resultadoEmail)) {
            return "ERRO: " + resultadoEmail;
        }

        String resultadoCnpj = cliente.validarCnpj(cliente.getCnpj());
        if (!CNPJ_OK.equals(resultadoCnpj)) {
            return "ERRO: " + resultadoCnpj;
        }

        return null;
    }
}