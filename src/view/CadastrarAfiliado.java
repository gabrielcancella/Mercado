package view;

import controllers.AfiliadoController;
import models.DAO.AfiliadosDAO;
import models.entity.AfiliadoEntity;
import models.interfaces.Tela;

import javax.swing.*;
import java.sql.Date;

public class CadastrarAfiliado implements Tela {
    private int WIDTH = 350;
    private int HEIGHT = 330;

    private JPanel background;
    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JTextField cpfField;
    private JTextField nascimentoField;
    private JButton cadastrarButton;
    private JButton voltarButton;

    public CadastrarAfiliado() {
        ViewManager.setWindowSize(WIDTH, HEIGHT);
        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });

        cadastrarButton.addActionListener(_ -> {
            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            String cpf = cpfField.getText();
            String cpfLimpo = cpfField.getText().replaceAll("[^\\d]", "");
            String nascimento = nascimentoField.getText();

            if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || nascimento.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                return;
            } else if (!cpfLimpo.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(null, "CPF inválido. Deve conter 11 dígitos.");
                return;
            } else if (!nascimento.matches("\\d{2}/\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Use o formato dd/mm/aaaa.");
                return;
            } else if (nome.length() < 3 || sobrenome.length() < 3) {
                JOptionPane.showMessageDialog(null, "Nome e sobrenome devem ter pelo menos 3 caracteres.");
                return;
            }

            String[] partesData = nascimento.split("/");
            Date data = new Date((Integer.parseInt(partesData[2]) - 1900), (Integer.parseInt(partesData[1]) - 1), Integer.parseInt(partesData[0]));
            AfiliadoEntity afiliado = new AfiliadoEntity(nome, sobrenome, cpfLimpo, data);
            try {
                if (AfiliadoController.cadastrarAfiliado(afiliado)) {
                    JOptionPane.showMessageDialog(null, "Afiliado cadastrado com sucesso!");
                    this.limparCampos();

                } else {
                    JOptionPane.showMessageDialog(null, "Afiliado ja existente!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar afiliado: " + e.getMessage());
            }
        });
    }

    private void limparCampos() {
        nomeField.setText("");
        sobrenomeField.setText("");
        cpfField.setText("");
        nascimentoField.setText("");
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
