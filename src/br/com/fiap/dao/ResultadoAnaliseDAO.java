package br.com.fiap.dao;

import br.com.fiap.bean.ResultadoAnalise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultadoAnaliseDAO {

    private Connection con;

    public ResultadoAnaliseDAO(Connection con){
        this.con = con;
    }

    public Connection getCon(){
        return con;
    }

    public String inserir(ResultadoAnalise resultadoAnalise){
        String sql = "insert into RESULTADO_ANALISE(PONTUACAO, CLASSIFICACAO, SUMARIO, ID_REUNIAO) values(?, ?, ?, ?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql, new String[]{"ID_RESULTADO_ANALISE"})) {
            ps.setInt(1, resultadoAnalise.getPontuacao());
            ps.setString(2, resultadoAnalise.getClassificacao());
            ps.setString(3, resultadoAnalise.getSumario());
            ps.setInt(4, resultadoAnalise.getIdReuniao());
            if (ps.executeUpdate() > 0) {
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        resultadoAnalise.setIdResultadoAnalise(rs.getInt(1));
                    }
                }
                return "A análise da reunião foi inserida com sucesso! ID:" + resultadoAnalise.getIdResultadoAnalise();
            }
            return "Não foi possível inserir o resultado da análise.";
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public String alterar(ResultadoAnalise resultadoAnalise){
        String sql = "update RESULTADO_ANALISE set PONTUACAO = ?, CLASSIFICACAO = ?, SUMARIO = ?, ID_REUNIAO = ? where ID_RESULTADO_ANALISE = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, resultadoAnalise.getPontuacao());
            ps.setString(2, resultadoAnalise.getClassificacao());
            ps.setString(3, resultadoAnalise.getSumario());
            ps.setInt(4, resultadoAnalise.getIdReuniao());
            ps.setInt(5, resultadoAnalise.getIdResultadoAnalise());
            if (ps.executeUpdate() > 0) {
                return "Resultado análise atualizado com sucesso!";
            }
            return "Não foi possível atualizar o resultado da análise!";
        } catch (SQLException e) {
            return "ERRO: erro de sql" + e.getMessage();
        }
    }

    public String excluir(ResultadoAnalise resultadoAnalise){
        String sql = "delete from RESULTADO_ANALISE where ID_RESULTADO_ANALISE = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, resultadoAnalise.getIdResultadoAnalise());
            if (ps.executeUpdate() > 0) {
                return "Resultado análise excluido com sucesso!";
            }
            return "Não foi possível excluir resultado análise!";
        } catch (SQLException e) {
            return "ERRO: erro de sql" + e.getMessage();
        }
    }

    public ArrayList<ResultadoAnalise> listarResultados(){
        String sql = "select ID_RESULTADO_ANALISE, PONTUACAO, CLASSIFICACAO, SUMARIO, ID_REUNIAO from RESULTADO_ANALISE order by ID_RESULTADO_ANALISE";
        ArrayList<ResultadoAnalise> listaResultado = new ArrayList<>();
        try(PreparedStatement ps = getCon().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                ResultadoAnalise resultadoAnalise = new ResultadoAnalise();
                resultadoAnalise.setIdResultadoAnalise(rs.getInt("ID_RESULTADO_ANALISE"));
                resultadoAnalise.setPontuacao(rs.getInt("PONTUACAO"));
                resultadoAnalise.setClassificacao(rs.getString("CLASSIFICACAO"));
                resultadoAnalise.setSumario(rs.getString("SUMARIO"));
                resultadoAnalise.setIdReuniao(rs.getInt("ID_REUNIAO"));
                resultadoAnalise.setRiscoChurn("Risco de Churn".equals(resultadoAnalise.getClassificacao()));

                listaResultado.add(resultadoAnalise);
            }
        } catch (SQLException e) {
            System.out.println("ERRO: erro de SQL ao listar os resultados " + e.getMessage());
        }
        return listaResultado;
    }

    public ResultadoAnalise buscarPorId(int idResultadoAnalise){
        String sql = "select ID_RESULTADO_ANALISE, PONTUACAO, CLASSIFICACAO, SUMARIO, ID_REUNIAO from RESULTADO_ANALISE where ID_RESULTADO_ANALISE = ?";
        ResultadoAnalise resultadoAnalise = null;
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idResultadoAnalise);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultadoAnalise = new ResultadoAnalise();
                    resultadoAnalise.setIdResultadoAnalise(rs.getInt("ID_RESULTADO_ANALISE"));
                    resultadoAnalise.setPontuacao(rs.getInt("PONTUACAO"));
                    resultadoAnalise.setClassificacao(rs.getString("CLASSIFICACAO"));
                    resultadoAnalise.setSumario(rs.getString("SUMARIO"));
                    resultadoAnalise.setIdReuniao(rs.getInt("ID_REUNIAO"));
                    resultadoAnalise.setRiscoChurn("Risco de Churn".equals(resultadoAnalise.getClassificacao()));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO: erro de SQL ao buscar o resultado da análise " + e.getMessage());
        }
        return resultadoAnalise;
    }


    public ResultadoAnalise buscarPorReuniao(int idReuniao){
        String sql = "select ID_RESULTADO_ANALISE, PONTUACAO, CLASSIFICACAO, SUMARIO, ID_REUNIAO from RESULTADO_ANALISE where ID_REUNIAO = ?";
        ResultadoAnalise resultadoAnalise = null;
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idReuniao);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultadoAnalise = new ResultadoAnalise();
                    resultadoAnalise.setIdResultadoAnalise(rs.getInt("ID_RESULTADO_ANALISE"));
                    resultadoAnalise.setPontuacao(rs.getInt("PONTUACAO"));
                    resultadoAnalise.setClassificacao(rs.getString("CLASSIFICACAO"));
                    resultadoAnalise.setSumario(rs.getString("SUMARIO"));
                    resultadoAnalise.setIdReuniao(rs.getInt("ID_REUNIAO"));
                    resultadoAnalise.setRiscoChurn("Risco de Churn".equals(resultadoAnalise.getClassificacao()));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO: erro de SQL ao buscar a análise da reunião " + e.getMessage());
        }
        return resultadoAnalise;
    }

}