package view;

import controllers.FornecedorController;
import models.interfaces.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarFornecedor implements Tela {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 275;

    private JPanel background;
    private JTextField nomeInput;
    private JTextField telefoneInput;
    private JTextField emailInput;
    private JButton registrarButton;
    private JButton voltarButton;

    public RegistrarFornecedor() {
        voltarButton.addActionListener(_ -> {
            ViewManager.setScreen(new Fornecedores());
        });
        registrarButton.addActionListener(_ -> {
            String nome = nomeInput.getText();
            String telefone = telefoneInput.getText();
            String email = emailInput.getText();

            try {
                FornecedorController.registrar(nome, telefone, email);
                this.limparCampos();
                JOptionPane.showMessageDialog(null, "Fornecedor registrado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao registrar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void limparCampos() {
        nomeInput.setText("");
        telefoneInput.setText("");
        emailInput.setText("");
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
