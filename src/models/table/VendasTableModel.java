package models.table;

import controllers.VendaController;
import models.dto.VendaRelatorioDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VendasTableModel extends AbstractTableModel {
    List<VendaRelatorioDTO> vendas = new ArrayList<>();
    private String[] colunas = {"ID", "CPF", "Método de Pagamento", "Data", "Valor"};

    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VendaRelatorioDTO venda = vendas.get(rowIndex);
        switch (columnIndex) {
            case 0: return venda.getId();
            case 1: return venda.getCpf() != null ? venda.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4") : "N/A";
            case 2: return venda.getMetodoPagamento();
            case 3: return venda.getDataVenda().toString();
            case 4: return "R$ %.2f".formatted(venda.getValorTotal());
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

    public void atualizarDados(String periodo, long metodoPagamento, String cpf) {
        vendas = VendaController.listarVendas(periodo, metodoPagamento, cpf);
        fireTableDataChanged();
    }
}
