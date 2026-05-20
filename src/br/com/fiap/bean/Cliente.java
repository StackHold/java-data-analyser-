package br.com.fiap.bean;
//Importando classe pai(Pessoa) para Cliente
public class Cliente extends Pessoa{
    //Criando atributo para classe Cliente
    private String cnpj;
    private String segmento;

    //Criando construtor da classe Cliente
    public Cliente() {}

    //Criando getters e setters da classe Cliente
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getSegmento() {
        return segmento;
    }
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    //Puxando metodo de pessoa
    public String apresentar() {
        return String.format("Nome: %s\nEmail: %s\nCNPJ: %s\nSegmento da empresa: %s", super.getNome(), super.getEmail(), getCnpj(), getSegmento());
    }
}
