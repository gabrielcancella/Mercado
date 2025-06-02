package view;

import controllers.CategoriaController;
import models.entity.CategoriaEntity;
import models.interfaces.Tela;
import models.table.CategoriaTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Categorias implements Tela {
    private JPanel background;
    private JTextField nomeCategoriaInput;
    private JButton adicionarCategoriaButton;
    private JTable categoriasTable;

    public Categorias() {
        ViewManager.setWindowSize(this.WIDTH, this.HEIGHT);
        adicionarCategoriaButton.addActionListener(_-> {
            String nomeCategoria = nomeCategoriaInput.getText().trim();
            if (!nomeCategoria.isEmpty()) {
                CategoriaController.adicionarCategoria(nomeCategoria);
                nomeCategoriaInput.setText("");

                ((CategoriaTableModel)categoriasTable.getModel()).atualizarDados();
            } else {
                JOptionPane.showMessageDialog(background, "O nome da categoria não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public JPanel getBackground() {
        return this.background;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        categoriasTable = new JTable();
        SwingUtilities.invokeLater(() -> {
            CategoriaTableModel model = new CategoriaTableModel();
            categoriasTable.setModel(model);

            categoriasTable.setAutoCreateRowSorter(true);
            categoriasTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            categoriasTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            categoriasTable.setRowSelectionAllowed(false);
            categoriasTable.setColumnSelectionAllowed(false);
            categoriasTable.setCellSelectionEnabled(false);
            categoriasTable.setFocusable(false);

            model.atualizarDados();
        });
    }
}
