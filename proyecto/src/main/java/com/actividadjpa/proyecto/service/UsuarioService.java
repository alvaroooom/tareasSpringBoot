package com.actividadjpa.proyecto.service;

import java.util.List;
import com.actividadjpa.proyecto.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
	
	Usuario crear(String nombre, String contrasenaEnClaro, Rol rol);
	Usuario actualizar(Long id, String nuevoNombre, Rol nuevoRol, String contraseña);
	
	void cambiarContrasena(Long id, String contrasenaActualEnClaro, String nuevaContrasenaEnClaro);
	Usuario obtenerPorId(Long id);
	Usuario obtenerPorNombre(String nombre);
	
	Page<Usuario> buscarPorNombre(String nombre, Pageable pageable);
	List<Usuario> listar();
	Page<Usuario> listar(Pageable pageable);
	
	void eliminar(Long id);
	Usuario obtenerUsuarioConectado();
}
