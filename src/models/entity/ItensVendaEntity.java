package models.entity;

public class ItensVendaEntity {
    private long venda;
    private ProdutoEntity produto;
    private long quantidade;
    private double valor_unitario;

    public long getVenda() {
        return venda;
    }

    public void setVenda(long venda) {
        this.venda = venda;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
}
