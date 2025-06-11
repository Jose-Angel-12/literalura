package com.gelse.literalura.principal;

import com.gelse.literalura.model.Datos;
import com.gelse.literalura.model.DatosLibros;
import com.gelse.literalura.service.ConsumoApi;
import com.gelse.literalura.service.ConvierteDatos;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void muestraElMenu(){
        String datosJson = consumoApi.obtenerDatosApi(URL_BASE);
        System.out.println("Datos json "  + datosJson);

        var datosConvertidos = convierteDatos.obtenerDatos(datosJson, Datos.class);
        System.out.println(datosConvertidos);
    }
}
