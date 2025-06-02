package models.table;

import controllers.CategoriaController;
import models.entity.CategoriaEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoriaTableModel extends AbstractTableModel {
    private List<CategoriaEntity> categorias = new ArrayList<>();
    private String[] colunas = {"ID", "Nome"};

    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CategoriaEntity categoria = categorias.get(rowIndex);
        switch (columnIndex) {
            case 0: return categoria.getId();
            case 1: return categoria.getNome();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public CategoriaEntity getCategoriaAt(int row) {
        return categorias.get(row);
    }

    public void atualizarDados() {
        this.categorias = CategoriaController.getAllCategorias();
        fireTableDataChanged();
    }
}
