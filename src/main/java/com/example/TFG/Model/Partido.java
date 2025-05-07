package com.example.TFG.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "partidos")
public class Partido {
    @Id
    private String id;
    private String equipoA;
    private String equipoB;
    private LocalDateTime fecha;
    private String lugar;
    private String resultado;

    public Partido() {
    }

    public Partido(String equipoA, String equipoB, LocalDateTime fecha, String lugar, String resultado) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.fecha = fecha;
        this.lugar = lugar;
        this.resultado = resultado;
    }

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