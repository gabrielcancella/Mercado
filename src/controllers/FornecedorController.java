package controllers;

import models.DAO.FornecedoresDAO;
import models.entity.FornecedorEntity;

import java.util.List;

public class FornecedorController {
    public static List<FornecedorEntity> getAll() {
        return FornecedoresDAO.getAll();
    }

    public static void registrar(String nome, String telefone, String email) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        } else if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        } else if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio.");
        } else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
        if (telefoneLimpo.length() < 9 || telefoneLimpo.length() > 15) {
            throw new IllegalArgumentException("Telefone deve ter entre 9 e 15 dígitos.");
        }

        FornecedorEntity fornecedor = new FornecedorEntity(
                nome,
                telefoneLimpo,
                email
        );
        FornecedoresDAO.registrar(fornecedor);
    }
}
