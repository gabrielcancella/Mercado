package models.DAO;

import connection.MySQLConnection;
import models.entity.AfiliadoEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AfiliadosDAO {
    public static List<AfiliadoEntity> getAfiliados() {
        String sql = "SELECT * FROM afiliados";
        List<AfiliadoEntity> afiliados = new ArrayList<>();

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String cpf = rs.getString("cpf");
                Date nascimento = rs.getDate("nascimento");

                AfiliadoEntity afiliado = new AfiliadoEntity();
                afiliado.setId(id);
                afiliado.setNome(nome);
                afiliado.setSobrenome(sobrenome);
                afiliado.setCpf(cpf);
                afiliado.setNascimento(nascimento);
                afiliados.add(afiliado);
            }
            return afiliados;
        } catch (SQLException e) {
            e.printStackTrace();
            return afiliados;
        }
    }

    public static void cadastro(AfiliadoEntity afiliado) {
        String sql = "INSERT INTO afiliados (nome, sobrenome, cpf, nascimento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, afiliado.getNome());
            stmt.setString(2, afiliado.getSobrenome());
            stmt.setString(3, afiliado.getCpf());
            stmt.setDate(4, afiliado.getNascimento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean afiliadoExiste(String cpf) {
        String sql = "SELECT COUNT(*) FROM afiliados WHERE cpf = ?";

        try (PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
