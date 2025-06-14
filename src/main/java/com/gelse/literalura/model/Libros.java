package com.gelse.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity //se crea un tabla en la base de datos
@Table(name = "libros") // se le da el nombre de libros a la tabla
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera los valores del id automaticamente
    private Long Id;
    @Column(unique = true) //para queno se repitan los libros
    private String titulo;
    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;
    @ElementCollection
    private List<String> idiomas;
    private Double descargas;

    public Libros() {
    }

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autor = datosLibros.datosAutor().stream().map(d -> new Autor(d)).collect(Collectors.toList());
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

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
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
        return """
                ------ LIBRO ------
                  Titulo: %s   
                  Autores: %s 
                  Idiomas: %s  
                  Descargas: %s
                -------------------
                """.formatted(titulo, autor.get(0).getNombre(), idiomas.get(0), descargas);
    }
}
