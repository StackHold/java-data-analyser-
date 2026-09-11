package br.com.fiap.controller;

import br.com.fiap.model.bean.AnalisadorTranscricao;
import br.com.fiap.model.bean.Categoria;
import br.com.fiap.model.bean.ResultadoAnalise;
import br.com.fiap.model.bean.Reuniao;
import br.com.fiap.model.dao.ResultadoAnaliseDAO;
import br.com.fiap.model.dao.ReuniaoDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;


public class ResultadoAnaliseController {

    private final ResultadoAnaliseDAO resultadoDAO;
    private final ReuniaoDAO reuniaoDAO;

    public ResultadoAnaliseController(Connection con) {
        this.resultadoDAO = new ResultadoAnaliseDAO(con);
        this.reuniaoDAO = new ReuniaoDAO(con);
    }


    public String analisar(int idReuniao, String sumario) {
        Reuniao reuniao = reuniaoDAO.buscarPorId(idReuniao);
        if (reuniao == null) {
            return "ERRO: reunião não encontrada com o ID " + idReuniao;
        }

        if (reuniao.getTranscricao() == null || reuniao.getTranscricao().isBlank()) {
            return "ERRO: a reunião não possui transcrição para analisar.";
        }


        if (resultadoDAO.buscarPorReuniao(idReuniao) != null) {
            return "ERRO: esta reunião já possui análise. Use a opção Alterar.";
        }

        ResultadoAnalise resultado = processar(reuniao);
        resultado.setIdReuniao(idReuniao);
        resultado.setSumario(sumario);

        return resultadoDAO.inserir(resultado);
    }


    public ArrayList<ResultadoAnalise> listar() {
        return resultadoDAO.listarResultados();
    }

    public ResultadoAnalise buscarPorId(int idResultadoAnalise) {
        return resultadoDAO.buscarPorId(idResultadoAnalise);
    }

    public ResultadoAnalise buscarPorReuniao(int idReuniao) {
        return resultadoDAO.buscarPorReuniao(idReuniao);
    }


    public String alterar(int idResultadoAnalise, String sumario) {
        ResultadoAnalise antigo = resultadoDAO.buscarPorId(idResultadoAnalise);
        if (antigo == null) {
            return "ERRO: análise não encontrada com o ID " + idResultadoAnalise;
        }

        Reuniao reuniao = reuniaoDAO.buscarPorId(antigo.getIdReuniao());
        if (reuniao == null) {
            return "ERRO: a reunião desta análise não existe mais.";
        }

        ResultadoAnalise novo = processar(reuniao);


        novo.setIdResultadoAnalise(antigo.getIdResultadoAnalise());
        novo.setIdReuniao(antigo.getIdReuniao());


        novo.setSumario((sumario == null || sumario.isBlank()) ? antigo.getSumario() : sumario);

        return resultadoDAO.alterar(novo);
    }


    public String excluir(int idResultadoAnalise) {
        ResultadoAnalise resultado = resultadoDAO.buscarPorId(idResultadoAnalise);
        if (resultado == null) {
            return "ERRO: análise não encontrada com o ID " + idResultadoAnalise;
        }
        return resultadoDAO.excluir(resultado);
    }



    private ResultadoAnalise processar(Reuniao reuniao) {
        AnalisadorTranscricao analisador = new AnalisadorTranscricao();
        analisador.setPalavraChave(montarPalavrasChave());
        return analisador.processar(reuniao);
    }


    public static Map<String, Categoria> montarPalavrasChave() {
        return Map.of(
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
    }
}