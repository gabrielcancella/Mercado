package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroProduto {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 280;

    private JPanel background;
    private JTextField inputNome;
    private JTextField inputCategoria;
    private JLabel valorUnitarioLabel;
    private JTextField inputValor;
    private JTextField inputQuantidade;
    private JButton cadastrarButton;
    private JLabel labelNome;
    private JLabel labelCategoria;
    private JLabel labelQuantidade;

    public CadastroProduto() {
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cadastrando Produto");
            }
        });
    }

    private void createUIComponents() {
        this.background = new JPanel();
        this.background.setLayout(null); // Desativa o layout manager
        this.background.setBounds(0, 0, WIDTH, HEIGHT); // Define tamanho e posição
        this.background.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Garante o tamanho preferido
    }

    public JPanel getBackground() {
        return background;
    }

    public void setBackground(JPanel background) {
        this.background = background;
    }

    public JTextField getInputNome() {
        return inputNome;
    }

    public void setInputNome(JTextField inputNome) {
        this.inputNome = inputNome;
    }

    public JTextField getInputCategoria() {
        return inputCategoria;
    }

    public JLabel getLabelNome() {
        return labelNome;
    }

    public void setLabelNome(JLabel labelNome) {
        this.labelNome = labelNome;
    }

    public JLabel getLabelCategoria() {
        return labelCategoria;
    }

    public void setLabelCategoria(JLabel labelCategoria) {
        this.labelCategoria = labelCategoria;
    }

    public JLabel getLabelQuantidade() {
        return labelQuantidade;
    }

    public void setLabelQuantidade(JLabel labelQuantidade) {
        this.labelQuantidade = labelQuantidade;
    }

    public void setInputCategoria(JTextField inputCategoria) {
        this.inputCategoria = inputCategoria;
    }

    public JLabel getValorUnitarioLabel() {
        return valorUnitarioLabel;
    }

    public void setValorUnitarioLabel(JLabel valorUnitarioLabel) {
        this.valorUnitarioLabel = valorUnitarioLabel;
    }

    public JTextField getInputValor() {
        return inputValor;
    }

    public void setInputValor(JTextField inputValor) {
        this.inputValor = inputValor;
    }

    public JTextField getInputQuantidade() {
        return inputQuantidade;
    }

    public void setInputQuantidade(JTextField inputQuantidade) {
        this.inputQuantidade = inputQuantidade;
    }

    public JButton getCadastrarButton() {
        return cadastrarButton;
    }

    public void setCadastrarButton(JButton cadastrarButton) {
        this.cadastrarButton = cadastrarButton;
    }
}
