package models.entity;

import java.time.LocalDateTime;

public class ComprasEntity {
    private long id;
    private long fornecedor;
    private LocalDateTime data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(long fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
