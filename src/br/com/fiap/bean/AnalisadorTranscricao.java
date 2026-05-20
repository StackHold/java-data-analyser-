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
    public int contarOcorrencias(){

    }

    public String classificarReuniao(){
        try {
            if (pontuacao >= 10) {
                return "Boa";
            } else if (pontuacao >= 0 && pontuacao <= 10){
                return  "Regular";
            } else if (pontuacao < 0) {
                return "Ruim";
            } else {
                throw new Exception("ERRO: Não à palavras para serem analisadas");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
