package view;

import models.interfaces.Tela;
import models.table.ProdutoTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ListaProdutos implements Tela {
    public static final int WIDTH = 650;
    public static final int HEIGHT = 400;

    private JPanel background;
    private JTable table;
    private JButton voltarButton;

    public ListaProdutos() {
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }

    private void createUIComponents() {
        table = new JTable();

        SwingUtilities.invokeLater(() -> {
            ProdutoTableModel model = new ProdutoTableModel();
            table.setModel(model);
            table.setAutoCreateRowSorter(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
            table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nome
            table.getColumnModel().getColumn(2).setPreferredWidth(150); // Categoria
            table.getColumnModel().getColumn(3).setPreferredWidth(75); // Preço
            table.getColumnModel().getColumn(4).setPreferredWidth(50); // Quantidade
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // Criar um renderizador centralizado
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table.setRowSelectionAllowed(true);
            table.setColumnSelectionAllowed(false);
            table.setCellSelectionEnabled(false);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.atualizarDados();
        });
    }
}
