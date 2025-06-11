package models.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class VendaRelatorioDTO {
    private long id;
    private String cpf;
    private String metodoPagamento;
    private Date dataVenda;
    private double valorTotal;

    public VendaRelatorioDTO(long id, String cpf, String metodoPagamento, Date dataVenda, double valorTotal) {
        this.id = id;
        this.cpf = cpf;
        this.metodoPagamento = metodoPagamento;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
