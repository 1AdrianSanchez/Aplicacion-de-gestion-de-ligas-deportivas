package com.example.TFG.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Clase que representa un Equipo
 * Esta mapeada a la coleccion "equipos" de MongoDB
 */
@Document(collection = "equipos") //indicamos que se almacena como documento
public class Equipo {
    @Id //id unico para cada documento
    private String id;
    private String nombre;
    private String deporte;
    private String capitan;
    private List<String> miembros;

    /**
     * Constructor vacio. necesario para que Spring cree instancias
     */
    public Equipo() {
    }

    /**
     * Constructor con todos los atributos menos el ID
     * ID se genera automaticamente en MongoDB
     * @param nombre
     * @param deporte
     * @param capitan
     * @param miembros lista de IDs de los miembros del equipo
     */
    public Equipo(String nombre, String deporte, String capitan, List<String> miembros) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.capitan = capitan;
        this.miembros = miembros;
    }

    //Getters y setters
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

    /**
     * ToString
     * @return cadena
     */
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
