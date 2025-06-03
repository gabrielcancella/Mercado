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
            CategoriaDAO.cadastrar(nome);
        } else {
            throw new IllegalArgumentException("Categoria inválida");
        }
    }

    public static void atualizarCategoria(long id, String novoNome) {
        if (id > 0 && novoNome != null && !novoNome.isEmpty()) {
            CategoriaDAO.atualizar(id, novoNome);
        } else {
            throw new IllegalArgumentException("ID ou nome inválido");
        }
    }

    public static void excluirCategoria(long id) {
        if (id > 0) {
            CategoriaDAO.excluir(id);
        } else {
            throw new IllegalArgumentException("ID inválido");
        }
    }
}
