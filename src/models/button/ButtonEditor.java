package models.button;

import models.table.ProdutoVendaTableModel;
import view.CadastroVenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private ProdutoVendaTableModel tableModel;
    private CadastroVenda cadastroVenda;
    private int selectedRow;

    public ButtonEditor(JCheckBox checkBox, ProdutoVendaTableModel tableModel, CadastroVenda cadastroVenda) {
        super(checkBox);
        this.tableModel = tableModel;
        this.cadastroVenda = cadastroVenda;

        button = new JButton("Remover");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.removerItem(selectedRow);
                cadastroVenda.atualizarValorTotal();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        selectedRow = row;
        button.setText("Remover");
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Remover";
    }
}
