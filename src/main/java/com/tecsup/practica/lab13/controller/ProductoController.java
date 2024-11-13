package com.tecsup.practica.lab13.controller;

import com.tecsup.practica.lab13.exception.ResourceNotFoundException;
import com.tecsup.practica.lab13.model.Producto;
import com.tecsup.practica.lab13.repository.ProductoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoRepositoy productoRepository;

    // Obtener todos los productos
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Crear un nuevo producto
    @PostMapping("/productos")
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener un producto por su ID
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> listarProductoPorId(@PathVariable long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el ID: " + id));
        return ResponseEntity.ok(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable long id, @RequestBody Producto productoRequest) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el ID: " + id));

        producto.setNombre(productoRequest.getNombre());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setCantidad(productoRequest.getCantidad());
        producto.setCategoria(productoRequest.getCategoria());
        producto.setImagenUrl(productoRequest.getImagenUrl());

        Producto productoActualizado = productoRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto por su ID
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el ID: " + id));

        productoRepository.delete(producto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
