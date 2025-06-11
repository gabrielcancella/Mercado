package controllers;

import models.DAO.MetodoPagamentoDAO;
import models.entity.MetodoPagamentoEntity;

import java.util.List;

public class MetodoPagamentoController {
    public static List<MetodoPagamentoEntity> getAll() {
        return MetodoPagamentoDAO.getAll();
    }
}
