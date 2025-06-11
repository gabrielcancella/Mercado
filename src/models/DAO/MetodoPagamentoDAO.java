package models.DAO;

import connection.MySQLConnection;
import models.entity.MetodoPagamentoEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagamentoDAO {
    public static List<MetodoPagamentoEntity> getAll() {
        List<MetodoPagamentoEntity> metodos = new ArrayList<>();
        String sql = "SELECT * FROM metodo_pagamento";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String metodo = rs.getString("metodo");

                MetodoPagamentoEntity metodoPagamento = new MetodoPagamentoEntity(id, metodo);

                metodos.add(metodoPagamento);
            }

            return metodos;
        } catch (Exception e) {
            e.printStackTrace();
            return metodos;
        }
    }
}
