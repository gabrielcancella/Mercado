package view;

import controllers.MetodoPagamentoController;
import models.entity.MetodoPagamentoEntity;
import models.interfaces.Tela;
import models.table.VendasTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatorioVendas implements Tela {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private JPanel background;
    private JTable vendasTable;
    private JComboBox periodoCombobox;
    private JComboBox<MetodoPagamentoEntity> metPagCombobox;
    private JTextField cpfInput;
    private JButton buscarButton;
    private JButton voltarButton;

    public RelatorioVendas() {
        buscarButton.addActionListener(_ -> {
            ((VendasTableModel)vendasTable.getModel()).atualizarDados(
                (String) periodoCombobox.getSelectedItem(),
                ((MetodoPagamentoEntity) metPagCombobox.getSelectedItem()).getId(),
                cpfInput.getText().trim().isEmpty() ? null : cpfInput.getText()
            );
        });
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });
    }

    public JPanel getBackground() {
        return background;
    }

    private void createUIComponents() {
        vendasTable = new JTable();
        metPagCombobox = new JComboBox();

        SwingUtilities.invokeLater(() -> {
            VendasTableModel model = new VendasTableModel();
            vendasTable.setModel(model);
            vendasTable.setAutoCreateRowSorter(true);
            vendasTable.setFocusable(false);
            vendasTable.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
            vendasTable.getColumnModel().getColumn(1).setPreferredWidth(200); // CPF
            vendasTable.getColumnModel().getColumn(2).setPreferredWidth(175); // Data
            vendasTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Valor

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < vendasTable.getColumnCount(); i++) {
                vendasTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            MetodoPagamentoController.getAll().forEach((MetodoPagamentoEntity metodo) -> {;
                metPagCombobox.addItem(metodo);
            });
            MetodoPagamentoEntity todos = new MetodoPagamentoEntity(-1, "TODOS");
            metPagCombobox.addItem(todos);
            metPagCombobox.setSelectedItem(todos);

            periodoCombobox.setSelectedItem("Todos");

            model.atualizarDados((String)periodoCombobox.getSelectedItem(), ((MetodoPagamentoEntity) metPagCombobox.getSelectedItem()).getId(), cpfInput.getText());
        });
    }
}
