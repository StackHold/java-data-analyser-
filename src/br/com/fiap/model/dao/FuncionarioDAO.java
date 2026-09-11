package br.com.fiap.model.dao;

import br.com.fiap.model.bean.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection con;

    public FuncionarioDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Funcionario funcionario) {
        String sql = "insert into FUNCIONARIO(CPF, SENHA, NOME, EMAIL) values(?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql, new String[]{"ID_FUNCIONARIO"})) {
            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getSenha());
            ps.setString(3, funcionario.getNome());
            ps.setString(4, funcionario.getEmail());

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        funcionario.setIdFuncionario(rs.getInt(1));
                    }
                }
                return "Funcionário inserido com sucesso! ID: " + funcionario.getIdFuncionario();
            }
            return "Não foi possível inserir o funcionário.";
        } catch (SQLException e) {
            return "ERRO de SQL ao inserir funcionário: " + e.getMessage();
        }
    }

    public String atualizar(Funcionario funcionario) {
        String sql = "update FUNCIONARIO set CPF = ?, SENHA = ?, NOME = ?, EMAIL = ? where ID_FUNCIONARIO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, funcionario.getCpf());
            ps.setString(2, funcionario.getSenha());
            ps.setString(3, funcionario.getNome());
            ps.setString(4, funcionario.getEmail());
            ps.setInt(5, funcionario.getIdFuncionario());

            if (ps.executeUpdate() > 0) {
                return "Funcionário foi alterado com sucesso!";
            } else {
                return "Não foi possível alterar o funcionário, ID não encontrado";
            }
        } catch (SQLException e) {
            return "ERRO de SQL ao alterar funcionário: " + e.getMessage();
        }
    }

    public String excluir(int idFuncionario) {
        String sql = "delete from FUNCIONARIO where ID_FUNCIONARIO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);
            if (ps.executeUpdate() > 0) {
                return "Funcionário excluído com sucesso!";
            } else {
                return "Não foi possível excluir o funcionário, ID não encontrado";
            }
        } catch (SQLException e) {
            return "ERRO de SQL ao excluir funcionário: " + e.getMessage();
        }
    }

    public List<Funcionario> listar() {
        String sql = "select ID_FUNCIONARIO, CPF, SENHA, NOME, EMAIL from FUNCIONARIO order by ID_FUNCIONARIO";
        List<Funcionario> listaFuncionario = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaFuncionario.add(montarFuncionario(rs));
            }
        } catch (SQLException e) {
            System.out.println("ERRO de SQL ao listar funcionários: " + e.getMessage());
        }
        return listaFuncionario;
    }

    public Funcionario buscarPorId(int idFuncionario) {
        String sql = "select ID_FUNCIONARIO, CPF, SENHA, NOME, EMAIL from FUNCIONARIO where ID_FUNCIONARIO = ?";
        Funcionario funcionario = null;
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcionario = montarFuncionario(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO de SQL ao buscar funcionário por ID: " + e.getMessage());
        }
        return funcionario;
    }


    public Funcionario buscarPorCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        String procurado = cpf.replaceAll("[^0-9]", "");
        String sql = "select ID_FUNCIONARIO, CPF, SENHA, NOME, EMAIL from FUNCIONARIO "
                + "where REPLACE(REPLACE(REPLACE(CPF, '.', ''), '-', ''), ' ', '') = ?";
        Funcionario funcionario = null;
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, procurado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcionario = montarFuncionario(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO de SQL ao buscar funcionário por CPF: " + e.getMessage());
        }
        return funcionario;
    }


    public String buscarSenha(String cpf) {
        if (cpf == null) {
            return null;
        }
        String procurado = cpf.replaceAll("[^0-9]", "");

        String sql = "select SENHA from FUNCIONARIO "
                + "where REPLACE(REPLACE(REPLACE(CPF, '.', ''), '-', ''), ' ', '') = ?";
        String senha = null;
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, procurado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    senha = rs.getString("SENHA");
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO de SQL ao buscar a senha: " + e.getMessage());
        }
        return senha;
    }

    private Funcionario montarFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
        funcionario.setCpf(rs.getString("CPF"));
        funcionario.setSenha(rs.getString("SENHA"));
        funcionario.setNome(rs.getString("NOME"));
        funcionario.setEmail(rs.getString("EMAIL"));
        return funcionario;
    }
}