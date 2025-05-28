package view;

import controllers.ProdutoController;
import models.entity.ProdutoEntity;
import models.interfaces.Tela;

import javax.swing.*;

public class CadastroProduto implements Tela {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private JPanel background;
    private JTextField inputNome;
    private JTextField inputValor;
    private JTextField inputQuantidade;
    private JButton salvarButton;
    private JButton atualizarButton;
    private JButton limparCamposButton;
    private JButton excluirButton;
    private JComboBox categoriasCombobox;

    public CadastroProduto() {
        ViewManager.setWindowSize(WIDTH, HEIGHT);

        this.getLimparCamposButton().addActionListener(_ -> {
            this.limparCampos();
        });

        this.getAtualizarButton().addActionListener(_ -> {
            if (!this.verificarCampos()) return;
            JOptionPane.showMessageDialog(null, "Atualizando Produto");
        });

        this.getAtualizarButton().addActionListener(_ -> {
            JOptionPane.showMessageDialog(null, "Excluindo Produto");
        });

        this.getSalvarButton().addActionListener(_ -> {
            if (!this.verificarCampos()) return;

            ProdutoEntity produto = new ProdutoEntity(
                    getInputNome().getText(),
                    1,
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

        this.getExcluirButton().addActionListener(_ -> {
            ViewManager.backToMainScreen();
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
