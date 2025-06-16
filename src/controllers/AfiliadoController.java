package controllers;

import models.DAO.AfiliadosDAO;
import models.entity.AfiliadoEntity;

import java.util.List;

public class AfiliadoController {

    public static List<AfiliadoEntity> listaAfiliados() {
        try {
            return AfiliadosDAO.getAfiliados();
        } catch (Exception e) {
            System.out.println("Erro ao buscar afiliados: %s".formatted(e.getMessage()));
            return List.of();
        }
    }

    public static boolean cadastrarAfiliado(AfiliadoEntity afiliado) {
        try {
            if (!AfiliadosDAO.afiliadoExiste(afiliado.getCpf())) {
                AfiliadosDAO.cadastro(afiliado);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar afiliado. %s".formatted(e.getMessage()));
            return false;
        }
    }

    public static boolean conferirAfiliado(String cpf) {
        return AfiliadosDAO.afiliadoExiste(cpf);
    }
}

