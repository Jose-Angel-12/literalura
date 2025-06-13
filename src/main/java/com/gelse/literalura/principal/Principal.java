package com.gelse.literalura.principal;

import com.gelse.literalura.model.Datos;
import com.gelse.literalura.model.DatosLibros;
import com.gelse.literalura.model.Libros;
import com.gelse.literalura.service.ConsumoApi;
import com.gelse.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = "\n" + """
                    --------------------------------------
                    Elija una opción a través de su número:
                    1 - Buscar libro por titulo 
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
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

    private void buscarLibro(){
        DatosLibros datosLibros = getDatosLibros().get();
        Libros libros = new Libros(datosLibros);

        System.out.println(libros);
    }
}
