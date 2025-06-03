package controllers;

import models.DAO.AfiliadosDAO;
import models.entity.AfiliadoEntity;

import java.sql.SQLException;

public class AfiliadoController {

    public static boolean cadastrarAfiliado(AfiliadoEntity afiliado) {
        try {
            if (!AfiliadosDAO.afiliadoExiste(afiliado)) {
                AfiliadosDAO.cadastro(afiliado);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar afiliado. %s".formatted(e.getMessage()));
            return false;
        }
    }
}
