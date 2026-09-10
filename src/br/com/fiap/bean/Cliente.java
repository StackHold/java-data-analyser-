package br.com.fiap.bean;
//Importando classe pai(Pessoa) para Cliente
public class Cliente extends Pessoa{
    //Criando atributo para classe Cliente
    private int idCliente;
    private String cnpj;
    private String segmento;

    //Criando construtor da classe Cliente
    public Cliente() {}

    //Construtor para o main
    public Cliente(String nome, String email, String cnpj, String segmento) {
        super.setNome(nome);
        super.setEmail(email);
        setCnpj(cnpj);
        setSegmento(segmento);
    }

    //Construtor para o cliente vindo do banco já com o ID.
    public Cliente(int idCliente, String cnpj, String segmento, String nome, String email) {
        this.idCliente = idCliente;
        this.cnpj = cnpj;
        this.segmento = segmento;
        super.setNome(nome);
        super.setEmail(email);
    }



    //Criando getters e setters da classe Cliente
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
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

    public String validarCnpj(String cnpj) {
        if (this.cnpj == null || this.cnpj == "") {
            return "CNPJ não informado";
        }
        String numeros = this.cnpj.replaceAll("[^0-9]", "");
        if (numeros.length() != 14) {
            return "CNPJ deve conter 14 dígitos";
        }
        return "O CNPJ informado é válido";
    }

}
