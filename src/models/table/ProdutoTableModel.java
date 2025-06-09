package models.table;

import controllers.ProdutoController;
import models.entity.ProdutoComCategoriaEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoTableModel extends AbstractTableModel {
    List<ProdutoComCategoriaEntity> produtos = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Categoria", "Preço", "Quantidade"};

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoComCategoriaEntity produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0: return produto.getId();
            case 1: return produto.getNome();
            case 2: return produto.getCategoria();
            case 3: return "R$ %.2f".formatted(produto.getPreco());
            case 4: return produto.getQuantidade();
            default: return null;
        }
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void atualizarDados() {
        this.produtos = ProdutoController.getAllProdutosComCategoria();
        fireTableDataChanged();
    }
}
