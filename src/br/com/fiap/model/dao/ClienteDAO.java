package br.com.fiap.model.dao;

import br.com.fiap.model.bean.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection con;

    public ClienteDAO(Connection con){this.con = con;}

    public Connection getCon(){
        return con;
    }

    public String inserir(Cliente cliente){
        String sql = "insert into CLIENTE(CNPJ, SEGMENTO, NOME, EMAIL) values(?, ?, ?, ?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql, new String[]{"ID_CLIENTE"})){
            ps.setString(1, cliente.getCnpj());
            ps.setString(2, cliente.getSegmento());
            ps.setString(3,cliente.getNome());
            ps.setString(4, cliente.getEmail());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setIdCliente(rs.getInt(1));
                    }
                }
                return "Cliente inserido com sucesso! ID: " + cliente.getIdCliente();
            } else {
                return "Houve um erro ao inserir o cliente!";
            }
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public String atualizar(Cliente cliente){
        String sql = "update CLIENTE set cnpj = ?, segmento = ?, nome = ?, email = ? where id_cliente = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, cliente.getCnpj());
            ps.setString(2, cliente.getSegmento());
            ps.setString(3, cliente.getNome());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getIdCliente());
            if (ps.executeUpdate() > 0) {
                return "Cliente foi alterado com sucesso!";
            } else {
                return "Não foi possivel alterar cliente";
            }
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public String excluir(Cliente cliente){
        String sql = "delete from CLIENTE where id_cliente = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdCliente());
            if (ps.executeUpdate() > 0) {
                return "Cliente foi excluido com sucesso!";
            } else {
                return "Falha ao excluir o cliente";
            }
        } catch (SQLException e) {
            return "ERRO: erro de SQL" + e.getMessage();
        }
    }

    public ArrayList<Cliente> listarClientes(){
        String sql = "select * from CLIENTE order by id_cliente";
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        try(PreparedStatement ps = getCon().prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setCnpj(rs.getString("CNPJ").trim());
                cliente.setSegmento(rs.getString("SEGMENTO"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setEmail(rs.getString("EMAIL"));

                listaCliente.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("ERRO: erro de SQL ao listar clientes " + e.getMessage());
        }
        return listaCliente;
    }

    public Cliente buscarPorId(int idCliente) {
        String sql = "select ID_CLIENTE, NOME, EMAIL, CNPJ, SEGMENTO from CLIENTE where ID_CLIENTE = ?";
        Cliente cliente = null;
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                    cliente.setNome(rs.getString("NOME"));
                    cliente.setEmail(rs.getString("EMAIL"));
                    cliente.setCnpj(rs.getString("CNPJ").trim());
                    cliente.setSegmento(rs.getString("SEGMENTO"));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO de SQL ao buscar cliente: " + e.getMessage());
        }
        return cliente;
    }
}
