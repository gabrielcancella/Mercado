package controllers;
import models.DAO.ProdutosDAO;
import models.entity.ProdutosEntity;

import java.sql.Connection;

public class ProdutoController {
    private ProdutosDAO dao;

    public ProdutoController(Connection conn) {
        this.dao = new ProdutosDAO(conn);
    }

    public void cadastrarProduto(ProdutosEntity produto) {
        dao.cadastro(produto);
    }

}
