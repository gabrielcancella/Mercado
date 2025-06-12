package models.entity;

import java.time.LocalDateTime;

public class VendaEntity {
    private long id;
    private String cpf;
    private long met_pag;
    private LocalDateTime data;
    private double valor;

    public VendaEntity(String cpf, long met_pag, LocalDateTime data, double valor) {
        this.cpf = cpf;
        this.met_pag = met_pag;
        this.data = data;
        this.valor = valor;
    }

    public VendaEntity() {

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

    public long getMet_pag() {
        return met_pag;
    }

    public void setMet_pag(long met_pag) {
        this.met_pag = met_pag;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
