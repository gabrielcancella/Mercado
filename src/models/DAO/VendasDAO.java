package models.DAO;

import connection.MySQLConnection;
import models.dto.VendaRelatorioDTO;
import models.entity.VendaEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendasDAO {
    public static long salvar(VendaEntity venda) {
        String sql = "INSERT INTO vendas (data, valor, met_pag, cpf) VALUES (?, ?, ?, ?)";
        long idGerado = -1;

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(venda.getData()));
            stmt.setDouble(2, venda.getValor());
            stmt.setLong(3, venda.getMet_pag());

            if (venda.getCpf() == null || venda.getCpf().isEmpty()) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, venda.getCpf());
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGerado;
    }

    public static List<VendaRelatorioDTO> getAllRelatorio(Date dataInicial, long met_pag, String cpf) {
        String sql = """
            SELECT v.id, v.cpf, mp.metodo AS metodoPagamento, v.data AS dataVenda, v.valor AS valorTotal
            FROM vendas v
            JOIN metodo_pagamento mp ON v.met_pag = mp.id
            WHERE (? IS NULL OR v.data >= ?) AND (v.met_pag = ? OR ? = -1) AND (v.cpf LIKE ? OR ? = '')
        """;
        List<VendaRelatorioDTO> vendas = new ArrayList<>();

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataInicial);
            stmt.setLong(3, met_pag);
            stmt.setLong(4, met_pag);
            stmt.setString(5, "%" + cpf + "%");
            stmt.setString(6, cpf);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String cpfVenda = rs.getString("cpf");
                String metodoPagamento = rs.getString("metodoPagamento");
                Date dataVenda = rs.getDate("dataVenda");
                double valorTotal = rs.getDouble("valorTotal");

                VendaRelatorioDTO vendaDTO = new VendaRelatorioDTO(id, cpfVenda, metodoPagamento, dataVenda, valorTotal);
                vendas.add(vendaDTO);
            }

            return vendas;
        } catch (SQLException e) {
            e.printStackTrace();
            return vendas;
        }
    }
}