package models.DAO;

import connection.MySQLConnection;
import models.entity.VendaEntity;

import java.sql.*;

public class VendasDAO {
    public static long salvar(VendaEntity venda) {
        String sql = "INSERT INTO vendas (data, valor) VALUES (?, ?)";
        long idGerado = -1;

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, Timestamp.valueOf(venda.getData()));
            stmt.setDouble(2, venda.getValor());
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
}