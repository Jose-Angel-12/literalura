package com.gelse.literalura.repository;

import com.gelse.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query(value = "SELECT a FROM Autor a")
    List<Autor> obtenerAutoresRegistrados();
}
