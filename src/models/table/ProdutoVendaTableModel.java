package models.table;

import models.entity.ProdutoEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoVendaTableModel extends AbstractTableModel {
    private final List<Object[]> dados = new ArrayList<>();
    private final String[] colunas = {"Produto", "Quantidade"};

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dados.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void adicionarProduto(ProdutoEntity produto, int quantidade) {
        dados.add(new Object[]{produto, quantidade});
        fireTableRowsInserted(dados.size() - 1, dados.size() - 1);
    }
}
