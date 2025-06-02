package controllers;

import models.DAO.CategoriaDAO;
import models.entity.CategoriaEntity;

import java.util.List;

public class CategoriaController {
    public static List<CategoriaEntity> getAllCategorias() {
        return CategoriaDAO.getAll();
    }

    public static void adicionarCategoria(String nome) {
        if (nome != null  && !nome.isEmpty()) {
            CategoriaDAO.cadastrarCategoria(nome);
        } else {
            throw new IllegalArgumentException("Categoria inválida");
        }
    }
}
