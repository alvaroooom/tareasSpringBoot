package com.actividadjpa.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.actividadjpa.proyecto.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
}

