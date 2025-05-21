package controllers;
import models.DAO.ProdutosDAO;
import models.entity.ProdutosEntity;

import java.sql.Connection;

public class ProdutoController {
    private ProdutosDAO dao;

    public ProdutoController(Connection conn) {
        this.dao = new ProdutosDAO(conn);
    }

    public boolean cadastrarProduto(ProdutosEntity produto) {
        try {
            dao.cadastro(produto);
            return true;
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar produto. %s".formatted(e.getMessage()));
            return false;
        }
    }

}
