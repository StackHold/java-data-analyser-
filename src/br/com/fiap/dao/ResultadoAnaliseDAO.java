package br.com.fiap.dao;

import br.com.fiap.bean.ResultadoAnalise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                return "A analise da reunião foi inserida com sucesso! ID:" + resultadoAnalise.getIdResultadoAnalise();
            }
            return "Não foi possível inserir o resultado da análise.";
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public String atualizar(ResultadoAnalise resultadoAnalise){}

    public String excluir(ResultadoAnalise resultadoAnalise){}

}
