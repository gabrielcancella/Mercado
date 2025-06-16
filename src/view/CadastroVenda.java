package view;

import controllers.AfiliadoController;
import controllers.MetodoPagamentoController;
import controllers.ProdutoController;
import controllers.VendaController;
import models.entity.ItensVendaEntity;
import models.entity.MetodoPagamentoEntity;
import models.entity.ProdutoEntity;
import models.entity.VendaEntity;
import models.interfaces.Tela;
import models.button.ButtonEditor;
import models.button.ButtonRenderer;
import models.table.ProdutoVendaTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroVenda implements Tela {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private JPanel background;
    private JTextField compradorField;
    private JTable produtos;
    private JComboBox produtoBox;
    private JComboBox quantidadeBox;
    private JButton adicionarProdutoButton;
    private JScrollPane ListaVenda;
    private JLabel valorTotalLabel; 
    private JButton realizarVenda;
    private JComboBox met_pagBox;
    private JButton voltarButton;

    private ProdutoVendaTableModel tableModel;

    public CadastroVenda() {
        tableModel = new ProdutoVendaTableModel();
        produtos.setModel(tableModel);

        produtos.getColumn("Ação").setCellRenderer(new ButtonRenderer());
        produtos.getColumn("Ação").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, this));

        compradorField.setForeground(Color.GRAY);
        compradorField.setText("Digite o CPF (opcional)");

        compradorField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (compradorField.getText().equals("Digite o CPF (opcional)")) {
                    compradorField.setText("");
                    compradorField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (compradorField.getText().trim().isEmpty()) {
                    compradorField.setForeground(Color.GRAY);
                    compradorField.setText("Digite o CPF (opcional)");
                }
            }
        });

        voltarButton.addActionListener(_ -> {
            ViewManager.backToMainScreen();
        });


        popularCombos();
        configurarBotoes();
    }

    private void popularCombos() {
        produtos.setFont(new Font("SansSerif", Font.PLAIN, 12));
        produtos.setRowHeight(16);

        produtoBox.removeAllItems();
        List<ProdutoEntity> produtosList = ProdutoController.getAllProdutos();
        for (ProdutoEntity produto : produtosList) {
            produtoBox.addItem(produto);
        }
        produtoBox.setSelectedItem(null);

        quantidadeBox.removeAllItems();
        for (int i = 1; i <= 50; i++) {
            quantidadeBox.addItem(String.valueOf(i));
        }

        met_pagBox.removeAllItems();
        List<MetodoPagamentoEntity> metodos = MetodoPagamentoController.getAll();
        for (MetodoPagamentoEntity metodo : metodos) {
            met_pagBox.addItem(metodo);
        }
        met_pagBox.setSelectedItem(null);
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

        realizarVenda.addActionListener(e -> {
            String cpf = compradorField.getText().trim();
            boolean afiliadoExiste = false;

            if (!cpf.isEmpty() && !cpf.equals("Digite o CPF (opcional)")) {
                if (!cpf.matches("\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Formato de CPF inválido.\nDigite 11 dígitos ou no formato 000.000.000-00.");
                    return;
                }

                cpf = cpf.replaceAll("\\D", "");

                if (AfiliadoController.conferirAfiliado(cpf)) {
                    afiliadoExiste = true;
                } else {
                    JOptionPane.showMessageDialog(null, "CPF não encontrado entre os afiliados.");
                    return;
                }
            }



            double valorTotal = 0.0;
            List<ItensVendaEntity> itens = tableModel.getItens();

            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Adicione ao menos um produto.");
                return;
            }
            for (ItensVendaEntity item : itens) {
                valorTotal += item.getProduto().getPreco() * item.getQuantidade();
            }

            for (ItensVendaEntity item : itens) {
                if (!VendaController.possuiEstoque(item)) {
                    JOptionPane.showMessageDialog(null,
                            String.format("Estoque insuficiente para o produto '%s'.", item.getProduto().getNome()),
                            "Erro de Estoque",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (afiliadoExiste) {
                valorTotal *= 0.9;
            }

            MetodoPagamentoEntity metodoSelecionado = (MetodoPagamentoEntity) met_pagBox.getSelectedItem();
            if (metodoSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Selecione o método de pagamento.");
                return;
            }

            VendaEntity venda = new VendaEntity();
            venda.setCpf(cpf.isEmpty() || cpf.equals("Digite o CPF (opcional)") ? null : cpf);
            venda.setMet_pag(metodoSelecionado.getId());
            venda.setData(java.time.LocalDateTime.now());
            venda.setValor(valorTotal);

            long vendaId = VendaController.concluirVenda(venda);

            if (vendaId > 0) {
                for (ItensVendaEntity item : itens) {
                    if (!VendaController.retirarEstoque(item)) {
                        JOptionPane.showMessageDialog(null,
                                "Falha ao baixar estoque. Operação cancelada.",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        VendaController.cancelarVenda(vendaId, itens);
                        return;
                    }
                }
                for (ItensVendaEntity item : itens) {
                    item.setVenda(vendaId);
                    item.setValor_unitario(item.getProduto().getPreco());
                    VendaController.salvarItem(item);
                }

                if (afiliadoExiste) {
                    JOptionPane.showMessageDialog(null,
                            String.format("Venda efetuada com sucesso!\nValor final com 10%% de desconto: R$ %.2f", valorTotal),
                            "Venda Concluída com Desconto",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
                }

                tableModel.limpar();
                atualizarValorTotal();
                compradorField.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao salvar a venda.");
            }
        });


    }

    public void atualizarValorTotal() {
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
