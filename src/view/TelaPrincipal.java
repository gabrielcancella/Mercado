package view;

import models.interfaces.Tela;

import javax.swing.*;

public class TelaPrincipal implements Tela {
    private int WIDTH = 300;
    private int HEIGHT = 160;

    private JPanel background;
    private JButton produtosButton;
    private JButton vendasButton;
    private JButton cadastrarProdutoButton;
    private JButton cadastrarVendasButton;

    public TelaPrincipal() {
        cadastrarProdutoButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastroProduto());
        });

        ViewManager.setWindowSize(WIDTH, HEIGHT);
    }

    public JPanel getBackground() {
        return background;
    }
}
