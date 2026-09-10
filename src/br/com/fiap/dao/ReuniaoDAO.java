package br.com.fiap.dao;

import br.com.fiap.bean.Cliente;
import br.com.fiap.bean.Reuniao;

import java.sql.*;
import java.util.ArrayList;

public class ReuniaoDAO {

    private Connection con;

    public ReuniaoDAO(Connection con){this.con = con;}

    public Connection getCon() {
        return con;
    }

    public String inserir(Reuniao reuniao){
        String sql = "insert into REUNIAO(DATA, TRANSCRICAO, ID_CLIENTE) values(?, ?, ?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql, new String[]{"ID_REUNIAO"})) {
            ps.setDate(1, Date.valueOf(reuniao.getData()));
            ps.setString(2, reuniao.getTranscricao());
            ps.setInt(3, reuniao.getCliente().getIdCliente());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        reuniao.setIdReuniao(rs.getInt(1));
                    }
                }
                return "Reunião inserida com sucesso! ID: " + reuniao.getIdReuniao();
            }
            return "Não foi possível inserir a reunião.";
        } catch (SQLException e) {
            return "ERRO: erro de SQL " + e.getMessage();
        }
    }

    public String atualizar(Reuniao reuniao){
        String sql = "update REUNIAO set DATA = ?, TRANSCRICAO = ?, ID_CLIENTE = ? WHERE ID_REUNIAO = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(reuniao.getData()));
            ps.setString(2, reuniao.getTranscricao());
            ps.setInt(3, reuniao.getCliente().getIdCliente());
            ps.setInt(4, reuniao.getIdReuniao());
            if (ps.executeUpdate() > 0) {
                return "Reunião foi atualizada com sucesso!";
            } else {
                return "Não foi possivel atualizar a reunião, id não encontrado";
            }
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public String excluir(int idReuniao){
        String sql = "delete from REUNIAO where ID_REUNIAO = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idReuniao);
            if (ps.executeUpdate() > 0) {
                return "A reunião foi excluida com sucesso!";
            } else {
                return "Não foi possivel excluir a reunião!";
            }
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public ArrayList<Reuniao> listarTodos(){
        String sql = "select * from REUNIAO order by ID_REUNIAO";
        ArrayList<Reuniao> listaReuniao = new ArrayList<>();
        try(PreparedStatement ps = getCon().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                Cliente cliente = new ClienteDAO(getCon()).buscarPorId(rs.getInt("ID_CLIENTE"));
                Reuniao reuniao = new Reuniao();
                reuniao.setIdReuniao(rs.getInt("ID_REUNIAO"));
                reuniao.setData(rs.getDate("DATA").toLocalDate());
                reuniao.setTranscricao(rs.getString("TRANSCRICAO"));
                reuniao.setCliente(cliente);

                listaReuniao.add(reuniao);
            }
        } catch (SQLException e) {
            System.out.println("ERRO: erro de SQL ao listar a reunião" + e.getMessage());
        }
        return listaReuniao;
    }

    public Reuniao buscarPorId(int idReuniao){
        String sql = "select ID_REUNIAO, DATA, TRANSCRICAO, ID_CLIENTE from REUNIAO where ID_REUNIAO = ?";
        Reuniao reuniao = null;
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idReuniao);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new ClienteDAO(getCon()).buscarPorId(rs.getInt("ID_CLIENTE"));
                    reuniao = new Reuniao();
                    reuniao.setIdReuniao(rs.getInt("ID_REUNIAO"));
                    reuniao.setData(rs.getDate("DATA").toLocalDate());
                    reuniao.setTranscricao(rs.getString("TRANSCRICAO"));
                    reuniao.setCliente(cliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO: erro de SQL ao buscar o ID da reunião" + e.getMessage());
        }
        return reuniao;
    }

}
