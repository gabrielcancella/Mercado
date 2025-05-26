package view;

import controllers.ProdutoController;
import models.entity.ProdutosEntity;
import models.interfaces.Tela;

import javax.swing.*;
import java.awt.*;

public class CadastroProduto implements Tela {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private JPanel background;
    private JTextField inputNome;
    private JLabel valorUnitarioLabel;
    private JTextField inputValor;
    private JTextField inputQuantidade;
    private JLabel labelNome;
    private JLabel labelCategoria;
    private JLabel labelQuantidade;
    private JButton salvarButton;
    private JPanel buttonsBackground;
    private JButton atualizarButton;
    private JButton limparCamposButton;
    private JButton excluirButton;
    private JComboBox categoriasCombobox;

    public CadastroProduto() {
        SwingUtilities.invokeLater(() -> {
            Window window = SwingUtilities.getWindowAncestor(background);
            if (window instanceof JFrame) {
                JFrame frame = (JFrame) window;
                frame.setResizable(false);
                frame.setSize(WIDTH, HEIGHT);
                frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
                frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
            }
        });

        limparCamposButton.addActionListener(_ -> {
            this.limparCampos();
        });

        atualizarButton.addActionListener(_ -> {
            if (!this.verificarCampos()) return;
            JOptionPane.showMessageDialog(null, "Atualizando Produto");
        });

        excluirButton.addActionListener(_ -> {
            JOptionPane.showMessageDialog(null, "Excluindo Produto");
        });

        salvarButton.addActionListener(_ -> {
            if (!this.verificarCampos()) return;

            ProdutosEntity produto = new ProdutosEntity(
                    getInputNome().getText(),
                    1,
                    Double.parseDouble(getInputValor().getText()),
                    Long.parseLong(getInputQuantidade().getText())
            );

            ProdutoController.cadastrarProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

            this.limparCampos();
        });
    }

    public void setWindowSize(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            Window window = SwingUtilities.getWindowAncestor(background);
            if (window instanceof JFrame) {
                JFrame frame = (JFrame) window;
                frame.setSize(width, height);
                frame.setMinimumSize(new Dimension(width, height));
                frame.setMaximumSize(new Dimension(width, height));
            }
        });
    }

    private boolean verificarCampos() {
        if (this.getInputNome().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome");
            return false;
        } else if ((this.getCategoriasCombobox().getSelectedIndex() == 0 || this.getCategoriasCombobox().getSelectedItem() == null) && false) {
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

    private void createUIComponents() {
        this.background = new JPanel();
        this.background.setLayout(null);
        this.background.setBounds(0, 0, WIDTH, HEIGHT);
        this.background.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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

    public JPanel getButtonsBackground() {
        return buttonsBackground;
    }

    public JButton getAtualizarButton() {
        return atualizarButton;
    }

    public JButton getLimparCamposButton() {
        return limparCamposButton;
    }

    public JButton getExcluirButton() {
        return excluirButton;
    }

    public JComboBox getCategoriasCombobox() {
        return categoriasCombobox;
    }
}
