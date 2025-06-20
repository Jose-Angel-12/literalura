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
    private String idiomas;
    private Double descargas;
    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;

    public Libros() {
    }

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        //creo la relacion de autores para el id libro_id
        //si lo hago directamente no estaria ejecutanto setAutor() sino directamente autor lo cual es null
        List<Autor> autores = datosLibros.datosAutor().stream().map(d -> new Autor(d)).limit(0).collect(Collectors.toList());
        this.setAutor(autores);
        if (datosLibros.idiomas() != null && !datosLibros.idiomas().isEmpty()) {
            this.idiomas = datosLibros.idiomas().get(0).trim();
        } else {
            this.idiomas = "Desconocido"; // O maneja como prefieras
        }
        this.descargas = datosLibros.descargas();
    }

    //constructores
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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
        autor.forEach(a -> a.setLibros(this));// quiere decir que para cada autor tiene un id de un 1libro
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
        try{
            return """
                ------ LIBRO ------
                  Titulo: %s   
                  Autores: %s 
                  Idiomas: %s  
                  Descargas: %s
                -------------------
                """.formatted(titulo, autor.get(0).getNombre(), idiomas, descargas);
        }catch (IndexOutOfBoundsException e){
            return """
                ------ LIBRO ------
                  Titulo: %s   
                  Autores: %s 
                  Idiomas: %s  
                  Descargas: %s
                -------------------
                """.formatted(titulo, "Desconocidos", idiomas, descargas);
        }
    }
}
