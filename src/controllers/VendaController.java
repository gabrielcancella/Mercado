package controllers;

import models.DAO.ItensVendaDAO;
import models.DAO.VendasDAO;
import models.entity.ItensVendaEntity;
import models.entity.VendaEntity;

public class VendaController {

    public static long concluirVenda(VendaEntity venda) {
        return VendasDAO.salvar(venda);
    }

    public static void salvarItem(ItensVendaEntity item) {
        ItensVendaDAO.salvar(item);
    }

}


