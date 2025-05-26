package controllers;
import models.DAO.ProdutosDAO;
import models.entity.ProdutosEntity;

import java.sql.Connection;

public class ProdutoController {
    public static boolean cadastrarProduto(ProdutosEntity produto) {
        try {
            if (!ProdutosDAO.produtoExiste(produto)){
                ProdutosDAO.cadastro(produto);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar produto. %s".formatted(e.getMessage()));
            return false;
        }

    }

}
