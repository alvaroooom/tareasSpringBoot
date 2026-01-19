package com.ActividadJPA.proyecto.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ActividadJPA.proyecto.model.Producto;
import com.ActividadJPA.proyecto.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos/list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/form";
    }

    @PostMapping
    public String crearProducto(@Valid @ModelAttribute("producto") Producto producto,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "productos/form";
        }
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        return "productos/form";
    }

    @PostMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, @Valid @ModelAttribute("producto") Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            return "productos/form";
        }
        producto.setId(id);
        productoService.actualizar(producto);
        return "redirect:/productos";
    }

    @PostMapping("/{id}/borrar")
    public String borrarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}

