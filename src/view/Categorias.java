package view;

import controllers.CategoriaController;
import models.interfaces.Tela;
import models.table.CategoriaTableModel;

import javax.swing.*;

public class Categorias implements Tela {
    private JPanel background;
    private JTextField nomeCategoriaInput;
    private JButton adicionarCategoriaButton;
    private JTable categoriasTable;
    private JButton editarButton;
    private JButton excluirButton;
    private JTextField editarField;
    private JButton voltarButton;

    public Categorias() {
        ViewManager.setWindowSize(this.WIDTH, this.HEIGHT);
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });
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
        editarButton.addActionListener(_ -> {
            int selectedRow = categoriasTable.getSelectedRow();
            if (selectedRow != -1) {
                String novoNome = editarField.getText().trim();
                if (!novoNome.isEmpty()) {
                    long categoriaId = (long) categoriasTable.getValueAt(selectedRow, 0);
                    CategoriaController.atualizarCategoria(categoriaId, novoNome);
                    editarField.setText("");
                    ((CategoriaTableModel)categoriasTable.getModel()).atualizarDados();
                } else {
                    JOptionPane.showMessageDialog(background, "O nome da categoria não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(background, "Selecione uma categoria para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        excluirButton.addActionListener(_ -> {
            int selectedRow = categoriasTable.getSelectedRow();
            if (selectedRow != -1) {
                long categoriaId = (long) categoriasTable.getValueAt(selectedRow, 0);
                CategoriaController.excluirCategoria(categoriaId);
                ((CategoriaTableModel)categoriasTable.getModel()).atualizarDados();
            } else {
                JOptionPane.showMessageDialog(background, "Selecione uma categoria para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public JPanel getBackground() {
        return this.background;
    }

    private void createUIComponents() {
        categoriasTable = new JTable();
        editarField = new JTextField();
        SwingUtilities.invokeLater(() -> {
            CategoriaTableModel model = new CategoriaTableModel();
            categoriasTable.setModel(model);
            categoriasTable.setAutoCreateRowSorter(true);
            categoriasTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            categoriasTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            categoriasTable.setRowSelectionAllowed(true);
            categoriasTable.setColumnSelectionAllowed(false);
            categoriasTable.setCellSelectionEnabled(false);
            categoriasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.atualizarDados();

            categoriasTable.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = categoriasTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Object valor = categoriasTable.getValueAt(selectedRow, 1);
                        editarField.setText(valor != null ? valor.toString() : "");
                        editarField.setEditable(true);
                        editarField.setFocusable(true);
                    }
                }
            });

            editarField.setEditable(false);
            editarField.setFocusable(false);
        });
    }
}
