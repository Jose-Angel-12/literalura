package com.gelse.literalura.repository;

import com.gelse.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTitulo(String Titulo);

    @Query("SELECT l FROM Libros l")
    List<Libros> obtenerLibrosRegistrados();

    @Query("SELECT l FROM Libros l WHERE l.idiomas ILIKE %:idioma%")
    List<Libros> obtenerLibrosPorIdiomas(String idioma);

//    @Query("SELECT a FROM Libros l JOIN l.autor a")
//    List<Autor> obtenerAutoresRegistrados();
}
