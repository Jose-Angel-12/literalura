package com.gelse.literalura.principal;

import com.gelse.literalura.model.Datos;
import com.gelse.literalura.model.DatosLibros;
import com.gelse.literalura.service.ConsumoApi;
import com.gelse.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = "\n" + """
                    1 - Buscar Libros 
                    
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

    private void buscarLibro() {

    }
}
