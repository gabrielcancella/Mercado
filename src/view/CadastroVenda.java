package view;

import models.interfaces.Tela;

import javax.swing.*;

public class CadastroVenda implements Tela {
    private JPanel background;
    private JTextField compradorField;
    private JTextField dataField;
    private JTable produtos;
    private JEditorPane editorPane1;

    public CadastroVenda() {

    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
