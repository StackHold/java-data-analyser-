package br.com.fiap.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnalisadorTranscricao {
    private Map<String, Categoria> palavrasChave;
    private final Map<Categoria, Integer> categoriasEOcorrenciasEncontradas = new HashMap<>();
    private int pontuacao = 0;
    private String transcricao;

    //Criando construtores
    public AnalisadorTranscricao() {}


    //Criando getters e setters
    public void setPalavraChave(Map<String, Categoria> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    public int getPontuacao() { return pontuacao; }
    public void setTranscricao(String transcricao) { this.transcricao = transcricao; }

    //Criando metodos
    private void contarOcorrencias(){
        this.categoriasEOcorrenciasEncontradas.clear();

        for(String palavra : this.transcricao.split("\\s+")){
            String palavraLimpa = palavra.toLowerCase().replaceAll("[^a-zA-Z\\u00C0-\\u00FF]", "");
            if (this.palavrasChave.containsKey(palavraLimpa)){
                Categoria cat = this.palavrasChave.get(palavraLimpa);
                this.categoriasEOcorrenciasEncontradas.merge(cat, 1, Integer::sum);
            }
        }
    }

    private String classificarReuniao(){
        contarOcorrencias();
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
        boolean riscoChurn = Objects.equals(classificacao, "Risco de Churn");
        this.categoriasEOcorrenciasEncontradas.forEach((cat, quant)->{
            System.out.println(cat + ":" + quant);
        });
        return new ResultadoAnalise(pontuacao,classificacao,riscoChurn);
    }
}
