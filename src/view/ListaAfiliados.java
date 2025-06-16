package view;

import models.interfaces.Tela;
import models.table.AfiliadosTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaAfiliados implements Tela {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private JPanel background;
    private JTable afiliadosTable;
    private JButton voltarButton;

    public ListaAfiliados() {
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        afiliadosTable = new JTable();
        SwingUtilities.invokeLater(() -> {
            AfiliadosTableModel model = new AfiliadosTableModel();
            afiliadosTable.setModel(model);
            afiliadosTable.setAutoCreateRowSorter(true);
            afiliadosTable.setFocusable(false);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < afiliadosTable.getColumnCount(); i++) {
                afiliadosTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            model.atualizar();
        });
    }
}
