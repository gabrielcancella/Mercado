package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.entity.ProdutosEntity;

public class ProdutosDAO {
    private Connection conn;

    public ProdutosDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastro(ProdutosEntity produto) {
        String sql = "INSERT INTO produtos (nome, categoria, preco, quantidade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setLong(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setLong(4, produto.getQuantidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



