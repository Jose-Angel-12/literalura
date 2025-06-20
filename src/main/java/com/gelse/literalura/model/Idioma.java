package com.gelse.literalura.model;

public enum Idioma {
    INGLES("en"),
    ESPANHOL("es"),
    FRANCES("fr"),
    PORTUGUES("pt");

    private String abreviacion;

    Idioma(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getAbreviacion(){
        return abreviacion;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.abreviacion.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
