package com.gelse.literalura.repository;

import com.gelse.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a")
    List<Autor> obtenerAutoresRegistrados();

    @Query("SELECT DISTINCT a FROM Autor a WHERE a.fechaDeNacimiento <= :anho AND a.fechaDeFallecimiento >= :anho")
    List<Autor> obtenerAutoresVivosPorAnho(Integer anho);
}
