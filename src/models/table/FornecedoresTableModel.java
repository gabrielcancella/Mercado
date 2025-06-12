package models.table;

import controllers.FornecedorController;
import models.entity.FornecedorEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresTableModel extends AbstractTableModel {
    private List<FornecedorEntity> fornecedores = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Telefone", "Email"};

    public FornecedoresTableModel() {
        super();
        atualizarDados();
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FornecedorEntity fornecedor = fornecedores.get(rowIndex);
        switch (columnIndex) {
            case 0: return fornecedor.getId();
            case 1: return fornecedor.getNome();
            case 2: return fornecedor.getTelefone();
            case 3: return fornecedor.getEmail();
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
        fornecedores = FornecedorController.getAll();
        fireTableDataChanged();
    }
}
