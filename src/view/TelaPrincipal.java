package view;

import models.interfaces.Tela;

import javax.swing.*;

public class TelaPrincipal implements Tela {
    private final int WIDTH = 300;
    private final int HEIGHT = 160;

    private JPanel background;
    private JButton produtosButton;
    private JButton vendasButton;
    private JButton cadastrarProdutoButton;
    private JButton cadastrarVendasButton;
    private JButton cadastrarUsuarioButton;
    private JButton listarUsuariosButton;

    public TelaPrincipal() {
        cadastrarProdutoButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastroProduto());
        });

        ViewManager.setWindowSize(this.WIDTH, this.HEIGHT);
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
