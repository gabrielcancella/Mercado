package models.DAO;

import connection.MySQLConnection;
import models.entity.FornecedorEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresDAO {
    public static List<FornecedorEntity> getAll() {
        String sql = "SELECT * FROM fornecedores";
        List<FornecedorEntity> fornecedores = new ArrayList<>();

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");

                FornecedorEntity forn = new FornecedorEntity(
                        id,
                        nome,
                        telefone,
                        email
                );
                fornecedores.add(forn);
            }

            return fornecedores;
        } catch (SQLException e) {
            e.printStackTrace();
            return fornecedores;
        }
    }

    public static void registrar(FornecedorEntity fornecedor){
        String sql = "INSERT INTO fornecedores (nome, telefone, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
