package com.gelse.literalura.model;

import java.util.List;

public class Libros {
    private String titulo;
    private List<DatosAutor> autor;
    private List<String> idiomas;
    private Double descargas;

    public Libros(){}

    public Libros(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autor = datosLibros.datosAutor();
        this.idiomas = datosLibros.idiomas();
        this.descargas = datosLibros.descargas();
    }

    //constructores
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
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
