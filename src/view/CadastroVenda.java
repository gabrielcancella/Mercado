package view;

import controllers.ProdutoController;
import models.entity.ItensCompraEstoqueEntity;
import models.entity.ItensVendaEntity;
import models.entity.ProdutoEntity;
import models.interfaces.Tela;
import models.table.ProdutoVendaTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroVenda implements Tela {
    private JPanel background;
    private JTextField compradorField;
    private JTable produtos;
    private JEditorPane editorPane1;
    private JComboBox produtoBox;
    private JComboBox quantidadeBox;
    private JButton adicionarProdutoButton;
    private JScrollPane ListaVenda;
    private JLabel valorTotalLabel; // Apenas JLabel agora

    private ProdutoVendaTableModel tableModel;

    public CadastroVenda() {
        ViewManager.setWindowSize(500, 600);

        createUIComponents();

        tableModel = new ProdutoVendaTableModel();
        produtos.setModel(tableModel);

        popularCombos();
        configurarBotoes();
    }

    private void createUIComponents() {
        SwingUtilities.invokeLater(() -> {
            List<ProdutoEntity> produtos = ProdutoController.getAllProdutos();
            for (ProdutoEntity produto : produtos) {
                produtoBox.addItem(produto);
            }
            produtoBox.setSelectedItem(null);
        });

        for (int i = 1; i <= 50; i++) {
            quantidadeBox.addItem(String.valueOf(i));
        }
    }

    private void popularCombos() {
        List<ProdutoEntity> lista = ProdutoController.getAllProdutos();
        produtoBox.removeAllItems();
        for (ProdutoEntity produto : lista) {
            produtoBox.addItem(produto);
        }
        produtoBox.setSelectedItem(null);

        produtos.setFont(new Font("SansSerif", Font.PLAIN, 12));
        produtos.setRowHeight(16);

        quantidadeBox.removeAllItems();
        for (int i = 1; i <= 50; i++) {
            quantidadeBox.addItem(String.valueOf(i));
        }
    }

    private void configurarBotoes() {
        adicionarProdutoButton.addActionListener(e -> {
            ProdutoEntity produtoSelecionado = (ProdutoEntity) produtoBox.getSelectedItem();
            String quantidadeStr = (String) quantidadeBox.getSelectedItem();

            if (produtoSelecionado == null || quantidadeStr == null) {
                JOptionPane.showMessageDialog(null, "Selecione um produto e uma quantidade.");
                return;
            }

            int quantidade = Integer.parseInt(quantidadeStr);
            tableModel.adicionarProduto(produtoSelecionado, quantidade);
            atualizarValorTotal();
        });
    }

    private void atualizarValorTotal() {
        double total = 0.0;
        for (ItensVendaEntity item : tableModel.getItens()) {
            ProdutoEntity produto = item.getProduto();
            if (produto != null) {
                total += produto.getPreco() * item.getQuantidade();
            }
        }
        valorTotalLabel.setText(String.format("R$ %.2f", total));
    }

    @Override
    public JPanel getBackground() {
        return background;
    }
}
