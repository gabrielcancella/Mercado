package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.MySQLConnection;
import models.entity.ProdutosEntity;

public class ProdutosDAO{
    public static void cadastro(ProdutosEntity produto){
        String sql = "INSERT INTO produtos (nome, categoria, preco, quantidade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
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


