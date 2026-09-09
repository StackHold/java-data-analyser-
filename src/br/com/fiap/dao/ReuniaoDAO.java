package br.com.fiap.dao;

import br.com.fiap.bean.Reuniao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReuniaoDAO {

    private Connection con;

    public ReuniaoDAO(Connection con){this.con = con;}

    public Connection getCon() {
        return con;
    }

    public String inserir(Reuniao reuniao){
        String sql = "insert into REUNIAO(DATA, TRANSCRICAO, ID_CLIENTE) values(?, ?, ?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {

        } catch (SQLException e) {
            System.out.println();

        }
        return null;
    }

}
