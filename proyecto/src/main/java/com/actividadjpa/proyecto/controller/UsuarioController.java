package com.actividadjpa.proyecto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.actividadjpa.proyecto.service.*;
import com.actividadjpa.proyecto.model.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
public class UsuarioController {

    private final UsuarioService usuarioServicio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioServicio, PasswordEncoder passwordEncoder) {
        this.usuarioServicio = usuarioServicio;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listarUsuarios(@RequestParam(required = false) String nombre,
                                 @PageableDefault(size = 5) Pageable page,
                                 Model model) {

        Page<Usuario> usuarios;

        if (nombre != null && !nombre.isBlank()) {
            usuarios = usuarioServicio.buscarPorNombre(nombre, page);
            model.addAttribute("nombre", nombre);
        } else {
            usuarios = usuarioServicio.listar(page);
        }

        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Rol.values());
        return "usuarios/formulario";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public String crearUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
             result.rejectValue("contrasena", "error.usuario", "La contraseña es obligatoria.");
        } else if (usuario.getContrasena().length() < 6) {
             result.rejectValue("contrasena", "error.usuario", "Mínimo 6 caracteres.");
        }
        
        if (result.hasErrors()) {
            model.addAttribute("roles", Rol.values());
            return "usuarios/formulario";
        }

        String passEncriptada = passwordEncoder.encode(usuario.getContrasena());
        
        usuarioServicio.crear(usuario.getNombre(), passEncriptada, usuario.getRol());
        
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/editar")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", Rol.values());
        return "usuarios/formulario";
    }

	@PostMapping("/{id}")
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	public String actualizarUsuario(@PathVariable Long id, @Valid Usuario usuario, BindingResult result, Model model) {
		
        if (usuario.getContrasena() != null && !usuario.getContrasena().isBlank()) {
             if (usuario.getContrasena().length() < 6) {
                result.rejectValue("contrasena", "error.usuario", "Mínimo 6 caracteres.");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", Rol.values());
            return "usuarios/formulario";
        }
        
        Usuario existente = usuarioServicio.obtenerPorId(id);
        String passwordFinal;

        if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
            passwordFinal = existente.getContrasena();
        } else {
            passwordFinal = passwordEncoder.encode(usuario.getContrasena());
        }
        
        usuarioServicio.actualizar(existente.getId(), usuario.getNombre(), usuario.getRol(), passwordFinal);
        
        return "redirect:/usuarios";
	}

    @PostMapping("/{id}/eliminar")
    @PreAuthorize("hasRole('ADMIN')")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminar(id);
        return "redirect:/usuarios";
    }
}

