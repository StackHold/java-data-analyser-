package br.com.fiap.controller;

import br.com.fiap.model.bean.Funcionario;
import br.com.fiap.model.dao.FuncionarioDAO;

import java.sql.Connection;
import java.util.List;

public class FuncionarioController {

    private static final String EMAIL_OK = "Email valido!";

    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioController(Connection con) {
        this.funcionarioDAO = new FuncionarioDAO(con);
    }


    public String cadastrar(String nome, String email, String cpf, String senha) {
        if (nome == null || nome.isBlank()) {
            return "ERRO: o nome do funcionário é obrigatório.";
        }
        if (senha == null || senha.isBlank()) {
            return "ERRO: a senha é obrigatória.";
        }


        String cpfLimpo = limparCpf(cpf);
        if (cpfLimpo == null) {
            return "ERRO: CPF inválido.";
        }

        Funcionario funcionario = new Funcionario(cpfLimpo, senha, nome, email);

        String erro = validarEmail(funcionario);
        if (erro != null) {
            return erro;
        }


        if (funcionarioDAO.buscarPorCpf(cpfLimpo) != null) {
            return "ERRO: já existe um funcionário cadastrado com este CPF.";
        }

        return funcionarioDAO.inserir(funcionario);
    }

    public Funcionario autenticar(String cpf, String senha) {
        if (senha == null || senha.isBlank()) {
            return null;
        }


        String cpfLimpo = limparCpf(cpf);
        if (cpfLimpo == null) {
            return null;
        }

        Funcionario encontrado = funcionarioDAO.buscarPorCpf(cpfLimpo);
        if (encontrado == null) {
            return null;
        }


        if (!senha.equals(encontrado.getSenha())) {
            return null;
        }

        return encontrado;
    }


    public List<Funcionario> listar() {
        return funcionarioDAO.listar();
    }

    public Funcionario buscarPorId(int idFuncionario) {
        return funcionarioDAO.buscarPorId(idFuncionario);
    }

    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioDAO.buscarPorCpf(cpf);
    }


    public String alterar(int idFuncionario, String nome, String email, String cpf, String senha) {
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);
        if (funcionario == null) {
            return "ERRO: funcionário não encontrado com o ID " + idFuncionario;
        }


        if (nome  != null && !nome.isBlank())  funcionario.setNome(nome);
        if (email != null && !email.isBlank()) funcionario.setEmail(email);
        if (senha != null && !senha.isBlank()) funcionario.setSenha(senha);

        if (cpf != null && !cpf.isBlank()) {
            String cpfLimpo = limparCpf(cpf);
            if (cpfLimpo == null) {
                return "ERRO: CPF inválido.";
            }


            Funcionario dono = funcionarioDAO.buscarPorCpf(cpfLimpo);
            if (dono != null && dono.getIdFuncionario() != idFuncionario) {
                return "ERRO: este CPF já pertence a outro funcionário.";
            }

            funcionario.setCpf(cpfLimpo);
        }

        String erro = validarEmail(funcionario);
        if (erro != null) {
            return erro;
        }

        return funcionarioDAO.atualizar(funcionario);
    }


    public String excluir(int idFuncionario) {
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);
        if (funcionario == null) {
            return "ERRO: funcionário não encontrado com o ID " + idFuncionario;
        }
        return funcionarioDAO.excluir(idFuncionario);
    }

    private String limparCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        try {
            return Funcionario.validaCpf(cpf);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private String validarEmail(Funcionario funcionario) {
        String resultadoEmail = funcionario.validarEmail(funcionario.getEmail());
        if (!EMAIL_OK.equals(resultadoEmail)) {
            return "ERRO: " + resultadoEmail;
        }
        return null;
    }
}