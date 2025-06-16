package view;

import models.interfaces.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal implements Tela {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 230;

    private JPanel background;
    private JButton listarProdutosButton;
    private JButton relatorioVendasButton;
    private JButton cadastrarProdutoButton;
    private JButton cadastrarVendasButton;
    private JButton cadastrarAfiliadoButton;
    private JButton listarAfiliadosButton;
    private JButton categoriasButton;
    private JButton fornecedoresButton;

    public TelaPrincipal() {
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
        relatorioVendasButton.addActionListener(_ -> {
            ViewManager.setScreen(new RelatorioVendas());
        });
        fornecedoresButton.addActionListener(_ -> {
            ViewManager.setScreen(new Fornecedores());
        });
        listarAfiliadosButton.addActionListener(_ -> {
            ViewManager.setScreen(new ListaAfiliados());
        });
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
