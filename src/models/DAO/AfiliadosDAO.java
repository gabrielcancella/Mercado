package models.DAO;

import connection.MySQLConnection;
import models.entity.AfiliadoEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AfiliadosDAO {

    public void cadastroAfiliado(AfiliadoEntity afiliado){
        String sql = "INSERT INTO afiliados (nome, sobrenome, cpf, nascimento) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(sql)){
            stmt.setString(1, afiliado.getNome());
            stmt.setString(2, afiliado.getSobrenome());
            stmt.setString(3, afiliado.getCpf());
            stmt.setString(4, afiliado.getNascimento());

            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
