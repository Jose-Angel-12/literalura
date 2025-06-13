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
        //this.autor = datosLibros.datosAutor().stream().map(a -> new DatosAutor(a.nombre(), a.fechaDeNacimeinto()));
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

    public void setAutor(String autor) {this.autor = autor; }

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
        return "Titulo: " + titulo +
                "Autores: " + autor +
                "Idiomas: " + idiomas +
                "Descargas: " + descargas;
    }
}
