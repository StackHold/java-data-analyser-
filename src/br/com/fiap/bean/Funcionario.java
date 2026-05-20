package br.com.fiap.bean;
//Importando classe pai(Pessoa) para Funcionario
public class Funcionario extends Pessoa{
    //Criando atributo para classe Funcionario
    private String cpf;

    //Criando construtor da classe Funcionario
    public Funcionario() {}
    public Funcionario(String nome, String email, String cpf){
        super.setNome(nome);
        super.setEmail(email);
        setCpf(cpf);
    }
    //Criando getters e setters da classe Funcionario
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //Puxando metodo da classe pai (Pessoa)
    public String apresentar() {
        return String.format("Nome: %s\nEmail: %s\n CPF: %s", super.getNome(), super.getEmail(), getCpf());
    }
}
