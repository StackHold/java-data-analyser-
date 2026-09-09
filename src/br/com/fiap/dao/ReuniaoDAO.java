package br.com.fiap.dao;

import java.sql.Connection;

public class ReuniaoDAO {

    private Connection con;

    public ReuniaoDAO(Connection con){this.con = con;}

    public Connection getCon() {
        return con;
    }
}
