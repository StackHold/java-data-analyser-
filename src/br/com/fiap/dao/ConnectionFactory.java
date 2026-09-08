package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection abrirConexao(){
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            final String USER = "######";
            final String PASS = "######";
            con = DriverManager.getConnection(url, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO: Não foi possivel encontrar a classe de conexão!" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ERRO: de SQL" + e.getMessage());
        }
        return con;
    }

    public static void fecharConexao(Connection con){
        try {
            con.close();
            System.out.println("A conexão foi encerrada");
        } catch (SQLException e) {
            System.out.println("ERRO: de SQL" + e.getMessage());
        }
    }

}
