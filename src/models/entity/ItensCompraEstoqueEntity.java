package models.entity;

public class ItensCompraEstoqueEntity {
    private long entrada;
    private long produto;
    private long quantidade;

    public long getEntrada() {
        return entrada;
    }

    public void setEntrada(long entrada) {
        this.entrada = entrada;
    }

    public long getProduto() {
        return produto;
    }

    public void setProduto(long produto) {
        this.produto = produto;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
}
