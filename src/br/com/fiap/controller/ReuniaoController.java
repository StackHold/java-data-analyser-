package br.com.fiap.controller;

import br.com.fiap.model.bean.Cliente;
import br.com.fiap.model.bean.Reuniao;
import br.com.fiap.model.dao.ClienteDAO;
import br.com.fiap.model.dao.ReuniaoDAO;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReuniaoController {

    private final ReuniaoDAO reuniaoDAO;
    private final ClienteDAO clienteDAO;

    public ReuniaoController(Connection con) {
        this.reuniaoDAO = new ReuniaoDAO(con);
        this.clienteDAO = new ClienteDAO(con);
    }


    public String cadastrar(int idCliente, LocalDate data, String transcricao) {
        //A chave estrangeira exige um cliente que exista no banco
        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        if (cliente == null) {
            return "ERRO: cliente não encontrado com o ID " + idCliente;
        }

        String erro = validar(data, transcricao);
        if (erro != null) {
            return erro;
        }

        Reuniao reuniao = new Reuniao(data, transcricao, cliente);
        return reuniaoDAO.inserir(reuniao);
    }


    public ArrayList<Reuniao> listar() {
        return reuniaoDAO.listarTodos();
    }

    public Reuniao buscarPorId(int idReuniao) {
        return reuniaoDAO.buscarPorId(idReuniao);
    }


    public String alterar(int idReuniao, int idCliente, LocalDate data, String transcricao) {
        Reuniao reuniao = reuniaoDAO.buscarPorId(idReuniao);
        if (reuniao == null) {
            return "ERRO: reunião não encontrada com o ID " + idReuniao;
        }


        if (idCliente > 0) {
            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                return "ERRO: cliente não encontrado com o ID " + idCliente;
            }
            reuniao.setCliente(cliente);
        }

        if (data != null) {
            if (data.isAfter(LocalDate.now())) {
                return "ERRO: a data da reunião não pode ser futura.";
            }
            reuniao.setData(data);
        }

        if (transcricao != null && !transcricao.isBlank()) {
            reuniao.setTranscricao(transcricao);
        }

        return reuniaoDAO.atualizar(reuniao);
    }


    public String excluir(int idReuniao) {
        Reuniao reuniao = reuniaoDAO.buscarPorId(idReuniao);
        if (reuniao == null) {
            return "ERRO: reunião não encontrada com o ID " + idReuniao;
        }
        return reuniaoDAO.excluir(idReuniao);
    }


    private String validar(LocalDate data, String transcricao) {
        if (data == null) {
            return "ERRO: a data da reunião é obrigatória.";
        }
        if (data.isAfter(LocalDate.now())) {
            return "ERRO: a data da reunião não pode ser futura.";
        }
        if (transcricao == null || transcricao.isBlank()) {
            return "ERRO: a transcrição da reunião é obrigatória.";
        }
        return null;
    }
}