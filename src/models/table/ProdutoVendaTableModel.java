package models.table;

import models.entity.ItensVendaEntity;
import models.entity.ProdutoEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoVendaTableModel extends AbstractTableModel {

    private final List<ItensVendaEntity> itens = new ArrayList<>();
    private final String[] colunas = {"Produto", "Quantidade", "Valor Unitário", "Total", "Ação"};

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItensVendaEntity item = itens.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getProduto().getNome();
            case 1:
                return item.getQuantidade();
            case 2:
                return String.format("R$ %.2f", item.getProduto().getPreco());
            case 3:
                return String.format("R$ %.2f", item.getProduto().getPreco() * item.getQuantidade());
            case 4:
                return "Remover";
            default:
                return null;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4; // Só a coluna "Ação" é editável
    }

    public void adicionarProduto(ProdutoEntity produto, int quantidade) {
        ItensVendaEntity item = new ItensVendaEntity();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setValor_unitario(produto.getPreco());
        itens.add(item);
        fireTableRowsInserted(itens.size() - 1, itens.size() - 1);
    }

    public List<ItensVendaEntity> getItens() {
        return itens;
    }

    public void removerItem(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < itens.size()) {
            itens.remove(rowIndex);
            fireTableDataChanged();
        }
    }

    public void limpar() {
        itens.clear();
        fireTableDataChanged();
    }


}

