package com.actividadjpa.proyecto.service;

import java.util.List;

import com.actividadjpa.proyecto.model.Producto;

public interface ProductoService {

    List<Producto> listarTodos();

    Producto guardar(Producto producto);
    Producto obtenerPorId(Long id);
    Producto actualizar(Producto producto);
    void eliminar(Long id);
}

