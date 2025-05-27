package view;

import models.interfaces.Tela;

import javax.swing.*;

public class CadastroVenda implements Tela {
    private JPanel background;
    private JTextField compradorField;

    public CadastroVenda() {

    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
