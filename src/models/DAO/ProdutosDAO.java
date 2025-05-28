package models.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.MySQLConnection;
import models.entity.ProdutoEntity;

public class ProdutosDAO {

    public static void cadastro(ProdutoEntity produto) {
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

    public static boolean produtoExiste(ProdutoEntity produto){
        String sql = "SELECT COUNT(*) FROM produtos WHERE nome = ?";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static List<ProdutoEntity> listaProtudos(){
        String sql = "SELECT * FROM produtos";
        List<ProdutoEntity> listaProduto = new ArrayList<>();

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nome = rs.getString("nome");
                long categoria = rs.getLong("categoria");
                double preco = rs.getDouble("preco");
                long quantidade = rs.getLong("quantidade");

                ProdutoEntity p = new ProdutoEntity(nome, quantidade, preco, categoria);
                listaProduto.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProduto;
    }


    public static boolean deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            int deletados = stmt.executeUpdate();
            return deletados > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



