package com.actividadJPA.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ActividadJPA.proyecto.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
}

