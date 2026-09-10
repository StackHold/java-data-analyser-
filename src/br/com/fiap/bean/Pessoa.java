package br.com.fiap.bean;
//Pessoa será a classe pai para Cliente e Funcionario
public class Pessoa {
    //Criando atributos da classe pessoa.
    private String nome;
    private String email;

    //Criando construtor da classe pessoa
    public Pessoa() {}

    //Criando getters e setters da classe pessoa
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //Criando metodo da classe pessoa
    public String apresentar(){
        return String.format("Nome: %s\nEmail: %s", getNome(), getEmail());
    }

    public String validarEmail(String email){
        if (this.email == null || this.email == "") {
            return "Email inválido";
        }
        String e = this.email.trim();
        int posArroba = e.indexOf("@");
        if (posArroba == -1 || e.indexOf('@', posArroba + 1) != - 1){
            return "O email deve conter @";
        }
        if (posArroba == 0) {
            return "Email deve ter texto antes do @";
        }
        if (posArroba == e.length() - 1){
            return "Email deve ter texto depois do @";
        }
        return "Email valido!";
    }

}
