package view;

import models.interfaces.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal implements Tela {
    private final int WIDTH = 400;
    private final int HEIGHT = 230;

    private JPanel background;
    private JButton listarProdutosButton;
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
        listarProdutosButton.addActionListener(_ -> {
            ViewManager.setScreen(new ListaProdutos());
        });
        cadastrarVendasButton.addActionListener(_ -> {
            ViewManager.setScreen(new CadastroVenda());
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
