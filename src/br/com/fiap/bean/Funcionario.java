package br.com.fiap.bean;
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
}
