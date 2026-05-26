package br.com.fiap.bean;

import java.util.Map;
import java.util.Objects;

public class AnalisadorTranscricao {
    private Map<String, Categoria> palavrasChave;
    private Map<Categoria, Integer> categoriasEOcorrenciasEncontradas = new HashMap<>();
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
    public void contarOcorrencias(){
        this.categoriasEOcorrenciasEncontradas.clear();

        for(String palavra : this.transcricao.split("\\s")){
            String palavraLimpa = palavra.toLowerCase().replace("[^a-zA-Z]", "");
            if (this.palavrasChave.containsKey(palavraLimpa)){
                Categoria cat = this.palavrasChave.get(palavra);
                this.categoriasEOcorrenciasEncontradas.merge(cat, 1, Integer::sum);
            }
        }

        return palavrasIdentificadas;
    }

    public String classificarReuniao(){

        this.categoriasEOcorrenciasEncontradas.forEach((categoria, quantidade)->{
            switch (categoria){
                case OPORTUNIDADE -> this.pontuacao += 15 * quantidade;
                case PRODUTO_TOTVS -> this.pontuacao += 5 * quantidade;
                case CONCORRENTE -> this.pontuacao -= 10 * quantidade ;
                case RISCO_CHURN -> this.pontuacao -= 25 * quantidade;
            }
        });
        int chanceChurn = categoriasEOcorrenciasEncontradas.getOrDefault(Categoria.RISCO_CHURN, 0);

        if (chanceChurn >= 2) {
            return "Risco de Churn";
        }
        // TODO classificações : ["Excelente","Produtiva","Neutra","Crítica","Risco de Churn"]
        if (pontuacao >= 20) return "Excelente";
        if (pontuacao >= 5)  return "Produtiva";
        if (pontuacao >= -5) return "Neutra / Alerta";
        if (pontuacao >= -20) return "Crítica";
        return "Risco de Churn";
    }
    public ResultadoAnalise processar(Reuniao reuniao){
        setTranscricao(reuniao.getTranscricao());
        String classificacao = classificarReuniao();
        int pontuacao = getPontuacao();
        boolean riscoChurn = !Objects.equals(classificacao, "Boa");

        return new ResultadoAnalise(pontuacao,classificacao,riscoChurn);
    }
}
