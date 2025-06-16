package com.gelse.literalura.repository;

import com.gelse.literalura.model.Autor;
import com.gelse.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTitulo(String Titulo);
    //List<Autor> findByAutores(String nombre);
}
