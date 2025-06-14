package view;

import controllers.CategoriaController;
import controllers.ProdutoController;
import models.entity.CategoriaEntity;
import models.entity.ProdutoEntity;
import models.interfaces.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroProduto implements Tela {
    public static final int WIDTH = 450;
    public static final int HEIGHT = 330;

    private JPanel background;
    private JTextField inputNome;
    private JTextField inputValor;
    private JTextField inputQuantidade;
    private JButton salvarButton;
    private JButton limparCamposButton;
    private JComboBox<CategoriaEntity> categoriasCombobox;
    private JButton voltarButton;

    public CadastroProduto() {
        this.getLimparCamposButton().addActionListener(_ -> {
            this.limparCampos();
        });

        this.getSalvarButton().addActionListener(_ -> {
            if (!this.verificarCampos()) return;

            ProdutoEntity produto = new ProdutoEntity(
                    getInputNome().getText(),
                    ((CategoriaEntity)getCategoriasCombobox().getSelectedItem()).getId(),
                    Double.parseDouble(getInputValor().getText()),
                    Long.parseLong(getInputQuantidade().getText())
            );

            if (ProdutoController.cadastrarProduto(produto)) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                this.limparCampos();

            }else {
                JOptionPane.showMessageDialog(null, "Produto já existe ou erro ao cadastrar.");

            }
        });
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });
    }

    private void createUIComponents() {
        this.categoriasCombobox = new JComboBox<>();
        SwingUtilities.invokeLater(() -> {
            List<CategoriaEntity> categorias = CategoriaController.getAllCategorias();
            for (CategoriaEntity categoria : categorias) {
                this.categoriasCombobox.addItem(categoria);
            }
            this.categoriasCombobox.setSelectedItem(null);
        });
    }

    private boolean verificarCampos() {
        if (this.getInputNome().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome");
            return false;
        } else if (this.getCategoriasCombobox().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Seleciona a categoria do produto");
            return false;
        } else if (this.getInputValor().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo valor");
            return false;
        } else if (this.getInputQuantidade().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo quantidade");
            return false;
        }

        String quantidadeEstoque = this.getInputQuantidade().getText().replaceAll("[^0-9]", "");
        if (quantidadeEstoque.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha corretamente o campo quantidade");
            return false;
        }

        return true;
    }

    private void limparCampos() {
        inputNome.setText("");
        categoriasCombobox.setSelectedItem(null);
        inputValor.setText("");
        inputQuantidade.setText("");
    }

    public JPanel getBackground() {
        return background;
    }

    public JTextField getInputNome() {
        return inputNome;
    }

    public JTextField getInputValor() {
        return inputValor;
    }

    public JTextField getInputQuantidade() {
        return inputQuantidade;
    }

    public JButton getSalvarButton() {
        return salvarButton;
    }

    public JButton getLimparCamposButton() {
        return limparCamposButton;
    }

    public JComboBox<CategoriaEntity> getCategoriasCombobox() {
        return categoriasCombobox;
    }
}
