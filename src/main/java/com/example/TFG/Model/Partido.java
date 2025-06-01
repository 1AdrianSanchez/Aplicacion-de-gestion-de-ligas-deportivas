package com.example.TFG.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Clase que representa un Partido
 * Esta mapeada a la coleccion "partidos" de MongoDB
 */
@Document(collection = "partidos") //indicamos que se almacena como documento
public class Partido {
    @Id //id unico para cada documento
    private String id;
    private String equipoA;
    private String equipoB;
    private LocalDateTime fecha;
    private String lugar;
    private String resultado;

    /**
     * Constructor vacio. necesario para que Spring cree instancias
     */
    public Partido() {
    }

    /**
     * Constructor con todos los atributos menos el ID
     * ID se genera automaticamente en MongoDB
     * @param equipoA
     * @param equipoB
     * @param fecha
     * @param lugar
     * @param resultado
     */
    public Partido(String equipoA, String equipoB, LocalDateTime fecha, String lugar, String resultado) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resultado = resultado;
    }

    //Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(String equipoA) {
        this.equipoA = equipoA;
    }

    public String getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(String equipoB) {
        this.equipoB = equipoB;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * ToString
     * @return cadena
     */
    @Override
    public String toString() {
        return "Partido{" +
                "id='" + id + '\'' +
                ", equipoA='" + equipoA + '\'' +
                ", equipoB='" + equipoB + '\'' +
                ", fecha=" + fecha +
                ", lugar='" + lugar + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}