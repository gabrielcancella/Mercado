package view;

import models.interfaces.Tela;

import javax.swing.*;

public class TelaPrincipal implements Tela {
    private final int WIDTH = 400;
    private final int HEIGHT = 230;

    private JPanel background;
    private JButton produtosButton;
    private JButton vendasButton;
    private JButton cadastrarProdutoButton;
    private JButton cadastrarVendasButton;
    private JButton cadastrarAfiliadoButton;
    private JButton listarAfiliadosButton;
    private JButton categoriasButton;

    public TelaPrincipal() {
        ViewManager.setWindowSize(this.WIDTH, this.HEIGHT);

        cadastrarProdutoButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastroProduto());
        });

        cadastrarAfiliadoButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastrarAfiliado());
        });
        categoriasButton.addActionListener(_ -> {
            ViewManager.setScreen(new Categorias());
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
