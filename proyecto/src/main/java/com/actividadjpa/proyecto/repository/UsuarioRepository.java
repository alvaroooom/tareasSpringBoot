package com.actividadjpa.proyecto.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.actividadjpa.proyecto.model.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Page<Usuario> findByNombre(String username, Pageable pageable);
	Usuario findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
