package models.dto;

import java.time.LocalDateTime;

public class VendaRelatorioDTO {
    private long id;
    private String cpf;
    private String metodoPagamento;
    private LocalDateTime dataVenda;
    private double valorTotal;

    public VendaRelatorioDTO(long id, String cpf, String metodoPagamento, LocalDateTime dataVenda, double valorTotal) {
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

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
