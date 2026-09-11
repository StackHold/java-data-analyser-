package br.com.fiap.model.bean;

public class ResultadoAnalise {
    //Criando atributos da classe
    private int idResultadoAnalise;
    private int pontuacao;
    private String classificacao;
    private String sumario;
    private boolean riscoChurn;
    private int idReuniao;

    //Criando Constutor da classe
    public ResultadoAnalise() {}

    public ResultadoAnalise(int pontuacao, String classificacao, boolean riscoChurn){
        setClassificacao(classificacao);
        setPontuacao(pontuacao);
        setRiscoChurn(riscoChurn);
    }

    public ResultadoAnalise(int idResultadoAnalise, int pontuacao, String classificacao,
                            String sumario, int idReuniao){
        this.idResultadoAnalise = idResultadoAnalise;
        setPontuacao(pontuacao);
        setClassificacao(classificacao);
        setSumario(sumario);
        setIdReuniao(idReuniao);
        setRiscoChurn("Risco de Churn".equals(classificacao));
    }

    //Criando getters e setters
    public int getIdResultadoAnalise() {
        return idResultadoAnalise;
    }
    public void setIdResultadoAnalise(int idResultadoAnalise) {
        this.idResultadoAnalise = idResultadoAnalise;
    }
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
    public String getSumario() {
        return sumario;
    }
    public void setSumario(String sumario) {
        if (sumario != null && sumario.length() > 1500) {
            sumario = sumario.substring(0, 1500);
        }
        this.sumario = sumario;
    }
    public boolean getRiscoChurn() {
        return riscoChurn;
    }
    public void setRiscoChurn(boolean riscoChurn) {
        this.riscoChurn = riscoChurn;
    }
    public int getIdReuniao() {
        return idReuniao;
    }
    public void setIdReuniao(int idReuniao) {
        this.idReuniao = idReuniao;
    }

    //Criando metodos da classe
    public String gerarRelatorio(){
        return String.format("""
                ==== Relatório da Reunião ====
                ID da análise: %d
                ID da reunião: %d
                Pontuação: %d
                Classificação: %s
                Risco de Churn: %s
                Sumario: %s""",
                getIdResultadoAnalise(),
                getIdReuniao(),
                getPontuacao(),
                getClassificacao(),
                getRiscoChurn() ? "Sim" : "Não",
                getSumario() == null ? "(não informado)" : getSumario());
    }

    public void exibirResultado(){
        System.out.println(gerarRelatorio());
    }

    public String toString() {
        return String.format("ResultadoAnalise [id=%d, pontuacao=%d, classificacao=%s, idReuniao=%d]",
                idResultadoAnalise, pontuacao, classificacao, idReuniao);
    }
}
