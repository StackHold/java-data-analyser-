package br.com.fiap.model.bean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reuniao {
    private int idReuniao;
    private LocalDate data;
    private String transcricao;
    private Cliente cliente;

    public Reuniao(){}

    //
    public Reuniao(LocalDate data, String transcricao, Cliente cliente) {
        this.data = data;
        this.transcricao = transcricao;
        this.cliente = cliente;
    }

    public Reuniao(int idReuniao, LocalDate data, String transcricao, Cliente cliente){
        this.idReuniao = idReuniao;
        setData(data);
        this.transcricao = transcricao;
        this.cliente = cliente;

    }


    public int getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(int idReuniao) {
        this.idReuniao = idReuniao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        try{
            if(data.isAfter(LocalDate.now())){
                throw new Exception("Data inválida!");
            }
            this.data = data;
        }catch (Exception e){
            System.out.println("Não foi possível receber a data da reunião: " + e.getMessage());
        }

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }


    public String exibirInfo(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFmt = this.data.format(fmt) ;
        return String.format("""
                ID da Reunião: %d
                Cliente: %s  Cnpj: %s
                Data da reunião: %s
                Transcrição:
                %s
                """, getIdReuniao(), cliente.getNome(), cliente.getCnpj(), dataFmt, this.transcricao);
    }
}
