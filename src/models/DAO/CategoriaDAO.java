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

    public static void cadastrarCategoria(String nome) {
        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
