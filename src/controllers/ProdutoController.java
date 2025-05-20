package controllers;

import models.DAO.ProdutosDAO;
import models.entity.ProdutosEntity;

public class ProdutoController {
    private ProdutosDAO dao;

    public ProdutoController(ProdutosDAO dao){
        this.dao = dao;
    }

    public void cadastrarProduto(String nome, long categoria, long preco, long quantidade){
        ProdutosEntity produto = new ProdutosEntity(nome,categoria, preco, quantidade);
        dao.cadastro(produto);
    }

}
