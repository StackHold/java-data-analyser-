package br.com.fiap.bean;

public class AnalisadorTranscricao {
    private String palavraChave;
    private int pontuacao;

    //Criando construtores
    public AnalisadorTranscricao() {}

    //Criando getters e setters
    public String getPalavraChave() {
        return palavraChave;
    }
    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }
    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    //Criando metodos

}
