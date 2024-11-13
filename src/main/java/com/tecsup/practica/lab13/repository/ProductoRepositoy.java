package com.tecsup.practica.lab13.repository;
import com.tecsup.practica.lab13.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductoRepositoy extends JpaRepository<Producto, Long> {
}
