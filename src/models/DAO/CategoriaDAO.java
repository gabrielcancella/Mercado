package models.DAO;

import connection.MySQLConnection;
import models.entity.CategoriaEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public static List<CategoriaEntity> getAll() {
        String sql = "SELECT * FROM categorias ORDER BY id DESC";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<CategoriaEntity> categorias = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                categorias.add(new CategoriaEntity(id, nome));
            }

            rs.close();
            stmt.close();
            return categorias;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void cadastrar(String nome) {
        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizar(long id, String novoNome) {
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluir(long id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existeForekey(long id) {
        String sql = "SELECT 1 FROM produtos WHERE categoria = ? LIMIT 1";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
