package view;

import models.interfaces.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal implements Tela {
    private final int WIDTH = 300;
    private final int HEIGHT = 200;

    private JPanel background;
    private JButton produtosButton;
    private JButton vendasButton;
    private JButton cadastrarProdutoButton;
    private JButton cadastrarVendasButton;
    private JButton cadastrarAfiliadoButton;
    private JButton listarAfiliadosButton;

    public TelaPrincipal() {
        ViewManager.setWindowSize(this.WIDTH, this.HEIGHT);

        cadastrarProdutoButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastroProduto());
        });

        cadastrarAfiliadoButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastrarAfiliado());
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
