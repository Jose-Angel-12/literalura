package com.gelse.literalura.principal;

import com.gelse.literalura.model.Autor;
import com.gelse.literalura.model.Datos;
import com.gelse.literalura.model.DatosLibros;
import com.gelse.literalura.model.Libros;
import com.gelse.literalura.repository.AutorRepository;
import com.gelse.literalura.repository.LibroRepository;
import com.gelse.literalura.service.ConsumoApi;
import com.gelse.literalura.service.ConvierteDatos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final Logger log = LoggerFactory.getLogger(Principal.class);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        /*



                    5 - Listar libros por idiomas
        */
        var opcion = -1;
        while (opcion != 0) {
            var menu = "\n" + """
                    --------------------------------------
                    Elija una opción a través de su número:
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibroRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivoPorAnho();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private Optional<DatosLibros> getDatosLibros() {
        System.out.println("Escriba el nombre del libro que desea buscar");
        String tituloLibro = teclado.nextLine();
        //obtengo los datos de la api
        String json = consumoApi.obtenerDatosApi(URL_BASE + "?Search=" + tituloLibro.replace(" ", "+"));
        // System.out.println(json);
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
//        System.out.println(datos);
        //busco el libro digitado
        Optional<DatosLibros> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("\n");
            // System.out.println("Libro encontrado:");
//            System.out.println(libroBuscado);
        } else {
            System.out.println("Libro no encontrado");
        }
        return libroBuscado;
    }

    private void buscarLibro() {
        DatosLibros datosLibros = getDatosLibros().get();
        String titulo = datosLibros.titulo();

        Optional<Libros> libroExistente = libroRepository.findByTitulo(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("Este libro ya se encuentra en la base de datos");
        } else {
            Libros libroNuevo = new Libros(datosLibros);
            libroRepository.save(libroNuevo);
            System.out.println(libroNuevo);
        }
    }

    private void listarLibroRegistrados() {
        List<Libros> listaDeLibros = libroRepository.obtenerLibrosRegistrados();
        listaDeLibros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.obtenerAutoresRegistrados();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivoPorAnho(){
        System.out.println("Escriba el año del cual quiere ver los autores vivos");
        Integer anho = teclado.nextInt();
        List<Autor> autoresVivos = autorRepository.obtenerAutoresVivosPorAnho(anho);
        autoresVivos.forEach(System.out::println);
    }
}
