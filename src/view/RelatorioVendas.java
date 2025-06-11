package view;

import controllers.MetodoPagamentoController;
import models.entity.MetodoPagamentoEntity;
import models.interfaces.Tela;
import models.table.VendasTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatorioVendas implements Tela {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 600;

    private JPanel background;
    private JTable vendasTable;
    private JComboBox periodoCombobox;
    private JComboBox<MetodoPagamentoEntity> metPagCombobox;
    private JTextField cpfInput;
    private JButton buscarButton;
    private JButton voltarButton;

    public RelatorioVendas() {
        ViewManager.setWindowSize(WIDTH, HEIGHT);

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

            MetodoPagamentoController.getAll().forEach((MetodoPagamentoEntity metodo) -> {;
                metPagCombobox.addItem(metodo);
            });
            MetodoPagamentoEntity todos = new MetodoPagamentoEntity(-1, "TODOS");
            metPagCombobox.addItem(todos);
            metPagCombobox.setSelectedItem(todos);

            periodoCombobox.setSelectedItem("Todos");

            model.atualizarDados((String)periodoCombobox.getSelectedItem(), ((MetodoPagamentoEntity)metPagCombobox.getSelectedItem()).getId(), cpfInput.getText());
        });
    }
}
