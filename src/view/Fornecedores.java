package view;

import models.interfaces.Tela;
import models.table.FornecedoresTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fornecedores implements Tela {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private JPanel background;
    private JButton voltarButton;
    private JButton registrarNovoButton;
    private JTable fornecedoresTable;

    public Fornecedores() {
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });
        registrarNovoButton.addActionListener(_ -> {
            ViewManager.setScreen(new RegistrarFornecedor());
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }

    private void createUIComponents() {
        fornecedoresTable = new JTable();
        SwingUtilities.invokeLater(() -> {
            FornecedoresTableModel model = new FornecedoresTableModel();
            fornecedoresTable.setModel(model);

            fornecedoresTable.getColumnModel().getColumn(0).setPreferredWidth(50); // Ajuste de largura da coluna ID
            fornecedoresTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Ajuste de largura da coluna Nome
            fornecedoresTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Ajuste de largura da coluna Telefone
            fornecedoresTable.getColumnModel().getColumn(3).setPreferredWidth(250); // Ajuste de largura da coluna Email

            fornecedoresTable.setAutoCreateRowSorter(true);
            fornecedoresTable.setRowSelectionAllowed(false);
            fornecedoresTable.setFocusable(false);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < fornecedoresTable.getColumnCount(); i++) {
                fornecedoresTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        });
    }
}
