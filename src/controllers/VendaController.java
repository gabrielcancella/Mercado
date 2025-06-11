package controllers;

import models.DAO.ItensVendaDAO;
import models.DAO.VendasDAO;
import models.dto.VendaRelatorioDTO;
import models.entity.ItensVendaEntity;
import models.entity.VendaEntity;

import java.sql.Date;
import java.util.List;

public class VendaController {

    public static long concluirVenda(VendaEntity venda) {
        return VendasDAO.salvar(venda);
    }

    public static void salvarItem(ItensVendaEntity item) {
        ItensVendaDAO.salvar(item);
    }

    public static List<VendaRelatorioDTO> listarVendas(String periodo, long met_pag, String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        Date data;
        if (periodo == null || periodo.isEmpty() || periodo.equalsIgnoreCase("Todos")) {
            data = null;
        } else if (periodo.equalsIgnoreCase("Últimas 24 horas")) {
            data = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        } else if (periodo.equalsIgnoreCase("Últimos 7 dias")) {
            data = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        } else if (periodo.equalsIgnoreCase("Últimos 30 dias")) {
            data = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000);
        } else if (periodo.equalsIgnoreCase("Últimos 60 dias")) {
            data = new Date(System.currentTimeMillis() - 60 * 24 * 60 * 60 * 1000);
        } else {
            data = new Date(System.currentTimeMillis() - 90 * 24 * 60 * 60 * 1000);
        }

        return VendasDAO.getAllRelatorio(data, met_pag, cpfLimpo);
    }
}


