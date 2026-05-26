package br.com.fiap.bean;

import java.util.Map;
import java.util.Objects;

public class AnalisadorTranscricao {
    private Map<String, Categoria> palavrasChave;
    private int pontuacao;
    private String transcricao;

    //Criando construtores
    public AnalisadorTranscricao() {}


    //Criando getters e setters
    public Map<String, Categoria> getPalavraChave() {
        return palavrasChave;
    }
    public void setPalavraChave(Map<String, Categoria> palavrasChave) {
        this.palavrasChave = palavrasChave;
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
        int palavrasIdentificadas = 0;
        for(String palavra : this.transcricao.split(" ")){
            if (this.palavrasChave.containsKey(palavra)){
                palavrasIdentificadas ++;
            }
        }

        return palavrasIdentificadas;
    }

    public String classificarReuniao(){

        for (Categoria cat : this.palavrasChave.values()){
            switch (cat){
                case OPORTUNIDADE -> this.pontuacao += 15;
                case PRODUTO_TOTVS -> this.pontuacao += 5;
                case CONCORRENTE -> this.pontuacao -= 10;
                case RISCO_CHURN -> this.pontuacao -= 25;
            }
        }
        // TODO classificações : ["Excelente","Produtiva","Neutra","Crítica","Risco de Churn"]

    }
    public ResultadoAnalise processar(Reuniao reuniao){
        setTranscricao(reuniao.getTranscricao());
        String classificacao = classificarReuniao();
        int pontuacao = getPontuacao();
        boolean riscoChurn = !Objects.equals(classificacao, "Boa");

        return new ResultadoAnalise(pontuacao,classificacao,riscoChurn);
    }
}
