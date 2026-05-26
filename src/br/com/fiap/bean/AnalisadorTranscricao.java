package br.com.fiap.bean;

import java.util.Map;

public class AnalisadorTranscricao {
    private Map<String, Categoria> palavraChave;
    private int pontuacao;

    //Criando construtores
    public AnalisadorTranscricao() {}


    //Criando getters e setters
    public Map<String, Categoria> getPalavraChave() {
        return palavraChave;
    }
    public void setPalavraChave(Map<String, Categoria> palavraChave) {
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
        return 0;
    }

    public String classificarReuniao(){
        if (pontuacao > 10) {
            return "Boa";
        } else if (pontuacao > 0 && pontuacao < 10){
            return  "Regular";
        } else {
            return "Ruim";
        }
    }
    public void processar(Reuniao reuniao){
        String transcricao = reuniao.getTranscricao();
    }
}
