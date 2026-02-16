package com.actividadjpa.proyecto.configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.actividadjpa.proyecto.model.Producto;
import com.actividadjpa.proyecto.model.Rol;
import com.actividadjpa.proyecto.model.Usuario;
import com.actividadjpa.proyecto.repository.ProductoRepository;
import com.actividadjpa.proyecto.repository.UsuarioRepository;

@Configuration
public class InicializarDatos {

    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepo, 
                               ProductoRepository productoRepo, 
                               PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepo.count() == 0) {
                Usuario admin = new Usuario();
                admin.setNombre("admin");
                admin.setContrasena(encoder.encode("admin123"));
                admin.setRol(Rol.ADMIN);
                usuarioRepo.save(admin);

                Usuario manager = new Usuario();
                manager.setNombre("manager");
                manager.setContrasena(encoder.encode("manager123"));
                manager.setRol(Rol.MANAGER);
                usuarioRepo.save(manager);
                
                System.out.println("--- Usuarios iniciales creados: admin/admin123 y manager/manager123 ---");
            }

            if (productoRepo.count() == 0) {
                productoRepo.save(new Producto("Laptop Gaming", "Potente portátil para jugar", 1200.50));
                productoRepo.save(new Producto("Teclado Mecánico", "RGB y switches red", 85.00));
                productoRepo.save(new Producto("Ratón Ergonómico", "Inalámbrico con 16000 DPI", 45.99));
                
            }
        };
    }
}