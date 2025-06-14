package com.gelse.literalura.repository;

import com.gelse.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libros, Long> {
}
