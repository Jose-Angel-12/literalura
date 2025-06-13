package com.gelse.literalura.model;

import java.util.List;

public class Libros {
    private String titulo;
    private String autor;
    private String idiomas;
    private Double descargas;

    public Libros() {
    }

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autor = datosLibros.datosAutor().get(0).nombre();
        this.idiomas = datosLibros.idiomas().get(0);
        this.descargas = datosLibros.descargas();
    }

    //constructores
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return """
                ------ LIBRO ------
                  Titulo: %s   
                  Autores: %s 
                  Idiomas: %s  
                  Descargas: %s
                -------------------
                """.formatted(titulo, autor, idiomas, descargas);
    }
}
