package br.com.fiap.bean;

import java.util.Map;

public class AnalisadorTranscricao {
    private Map<String, Categoria> palavraChave;
    private int pontuacao;
    private String transcricao;

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

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }

    //Criando metodos
    public int contarOcorrencias(){
        int count = 0;
        for(String palavra : this.transcricao.split(" ")){
            if (this.palavraChave.containsKey(palavra)){
                count ++;
            };
        }

        return count;
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
    public ResultadoAnalise processar(Reuniao reuniao){
        ResultadoAnalise res = new ResultadoAnalise();
        setTranscricao(reuniao.getTranscricao());
        return res;
    }
}
