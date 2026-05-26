package br.com.fiap.bean;

public class ResultadoAnalise {
    //Criando atributos da classe
    private int pontuacao;
    private String classificacao;
    private boolean riscoChurn;

    //Criando Constutor da classe
    public ResultadoAnalise() {}
    public ResultadoAnalise(int pontuacao, String classificacao, boolean riscoChurn){
        setClassificacao(classificacao);
        setPontuacao(pontuacao);
        setRiscoChurn(riscoChurn);
    }
    //Criando getters e setters
    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    public String getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    private boolean getRiscoChurn() {
        return riscoChurn;
    }
    private void setRiscoChurn(boolean riscoChurn) {
        this.riscoChurn = riscoChurn;
    }

    //Criando metodos da classe
    public String gerarRelatorio(){
        return String.format("====Relatório da Reunião====\nPontuação: %d\nClassificação: %s\nRisco de Churn: %s", getPontuacao(), getClassificacao(), getRiscoChurn() ? "Sim" : "Não");
    }

    public void exibirResultado(){
        System.out.println(gerarRelatorio());
    }
}
