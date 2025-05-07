package com.example.TFG.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "equipos")
public class Equipo {
    @Id
    private String id;
    private String nombre;
    private String deporte;
    private String capitan;
    private List<String> miembros;

    public Equipo() {
    }

    public Equipo(String nombre, String deporte, String capitan, List<String> miembros) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.capitan = capitan;
        this.miembros = miembros;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getCapitan() {
        return capitan;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

    public List<String> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<String> miembros) {
        this.miembros = miembros;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", deporte='" + deporte + '\'' +
                ", capitan='" + capitan + '\'' +
                ", miembros=" + miembros +
                '}';
    }
}
