package models.table;

import controllers.AfiliadoController;
import models.entity.AfiliadoEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AfiliadosTableModel extends AbstractTableModel {
    private List<AfiliadoEntity> afiliados = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Sobrenome", "CPF", "Data de Nascimento"};

    @Override
    public int getRowCount() {
        return afiliados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AfiliadoEntity afiliado = afiliados.get(rowIndex);
        switch (columnIndex) {
            case 0: return afiliado.getId();
            case 1: return afiliado.getNome();
            case 2: return afiliado.getSobrenome();
            case 3: return afiliado.getCpf();
            case 4: return afiliado.getNascimento().toLocalDate();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public AfiliadoEntity getCategoriaAt(int row) {
        return afiliados.get(row);
    }

    public void atualizar() {
        this.afiliados = AfiliadoController.listaAfiliados();
        fireTableDataChanged();
    }
}
