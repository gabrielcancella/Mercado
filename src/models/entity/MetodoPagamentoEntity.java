package models.entity;

public class MetodoPagamentoEntity {
    private long id;
    private String metodo;

    public MetodoPagamentoEntity(long id, String metodo) {
        this.id = id;
        this.metodo = metodo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return metodo;
    }
}
