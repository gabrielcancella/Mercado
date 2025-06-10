package models.DAO;

import connection.MySQLConnection;
import models.entity.ItensVendaEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItensVendaDAO {
    public static void salvar(ItensVendaEntity item) {
        String sql = "INSERT INTO itens_venda (venda, produto, quantidade, valor_unitario)\n" +
                "VALUES (?, ?, ?, ?)\n" +
                "ON DUPLICATE KEY UPDATE\n" +
                "quantidade = quantidade + VALUES(quantidade);";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {

            stmt.setLong(1, item.getVenda());
            stmt.setLong(2, item.getProduto().getId());
            stmt.setLong(3, item.getQuantidade());
            stmt.setDouble(4, item.getValor_unitario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
