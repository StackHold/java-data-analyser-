package br.com.fiap.model.bean;
//Importando classe pai(Pessoa) para Funcionario
public class Funcionario extends Pessoa{
    //Criando atributo para classe Funcionario
    private String cpf;
    private String senha;
    private int idFuncionario;

    //Criando construtor da classe Funcionario
    public Funcionario() {}
    public Funcionario(String cpf, String senha, int idFuncionario, String nome, String email) {
        this.cpf = cpf;
        this.senha = senha;
        this.idFuncionario = idFuncionario;
        super.setNome(nome);
        super.setEmail(email);
    }

    public Funcionario(String cpf, String senha, String nome, String email) {
        this.cpf = cpf;
        this.senha = senha;
        super.setNome(nome);
        super.setEmail(email);
    }

    //Criando getters e setters da classe Funcionario
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    //Puxando metodo da classe pai (Pessoa)
    public String apresentar() {
        return String.format("Nome: %s\nEmail: %s\n CPF: %s", super.getNome(), super.getEmail(), getCpf());
    }
    //Validador de CPF
    public static String validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido");
        }

        int d1 = 0, d2 = 0;
        for (int i = 0; i < 9; i++) {
            int num = cpf.charAt(i) - '0';
            d1 += num * (10 - i);
            d2 += num * (11 - i);
        }
        d1 = 11 - (d1 % 11);
        if (d1 >= 10) d1 = 0;

        d2 += d1 * 2;
        d2 = 11 - (d2 % 11);
        if (d2 >= 10) d2 = 0;

        if (d1 != cpf.charAt(9) - '0' || d2 != cpf.charAt(10) - '0') {
            throw new IllegalArgumentException("CPF inválido");
        }

        return cpf;
    }
}
