package br.com.fiap.bean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reuniao {
    private LocalDate data;
    private String transcricao;
    private Funcionario vendedor;
    private Cliente cliente;

    public Reuniao(){}
    public Reuniao(LocalDate data, String transcricao, Funcionario vendedor, Cliente cliente){
        setData(data);
        this.transcricao = transcricao;
        this.vendedor = vendedor;
        this.cliente = cliente;

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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public String exibirInfo(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFmt = this.data.format(fmt) ;
        return String.format("""
                Vendedor: %s Cpf: %s
                Cliente: %s  Cnpj: %s
                Data da reunião: %s
                Transcrição:
                %s
                """,vendedor.getNome(), vendedor.getCpf(), cliente.getNome(), cliente.getCnpj(), dataFmt, this.transcricao);
    }
}
