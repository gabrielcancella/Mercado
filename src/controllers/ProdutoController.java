package controllers;

import models.DAO.CategoriaDAO;
import models.DAO.ProdutosDAO;
import models.entity.CategoriaEntity;
import models.entity.ProdutoEntity;

import java.util.List;

public class ProdutoController {
    public static boolean cadastrarProduto(ProdutoEntity produto) {
        try {
            if (!ProdutosDAO.produtoExiste(produto)) {
                ProdutosDAO.cadastro(produto);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar produto. %s".formatted(e.getMessage()));
            return false;
        }
    }

    public static List<ProdutoEntity> getAllProdutos() {
        return ProdutosDAO.getAll();
    }
}

