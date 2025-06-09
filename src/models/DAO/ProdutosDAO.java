package models.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.MySQLConnection;
import models.entity.ProdutoComCategoriaEntity;
import models.entity.ProdutoEntity;

public class ProdutosDAO {
    public static List<ProdutoEntity> getAll(){
        String sql = "SELECT * FROM produtos";
        List<ProdutoEntity> produtos = new ArrayList<>();

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nome = rs.getString("nome");
                long categoria = rs.getLong("categoria");
                double preco = rs.getDouble("preco");
                long quantidade = rs.getLong("quantidade");

                ProdutoEntity p = new ProdutoEntity(nome, quantidade, preco, categoria);
                produtos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

    public static List<ProdutoComCategoriaEntity> getAllComCategoria() {
        String sql = """
            SELECT p.id, p.nome, p.preco, p.quantidade, c.nome AS categoria
            FROM produtos p
            JOIN categorias c ON p.categoria = c.id
        """;
        List<ProdutoComCategoriaEntity> produtos = new ArrayList<>();

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double preco = rs.getDouble("preco");
                long quantidade = rs.getLong("quantidade");

                ProdutoComCategoriaEntity p = new ProdutoComCategoriaEntity(id, nome, categoria, preco, quantidade);
                produtos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

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

    public static boolean excluir(int id) {
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



