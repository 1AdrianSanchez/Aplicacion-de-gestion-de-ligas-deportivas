package com.example.TFG.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa un Alumno
 * Esta mapeada a la coleccion "alumnos" de MongoDB
 */
@Document(collection = "alumnos") //indicamos que se almacena como documento
public class Alumno {
    @Id //id unico para cada documento
    private String id;
    private String nombre;
    private String curso;
    private String grupo;
    private String deporte;
    private String rol;

    /**
     * Constructor vacio. necesario para que Spring cree instancias
     */
    public Alumno() {
    }

    /**
     * Constructor con todos los atributos menos el ID
     * ID se genera automaticamente en MongoDB
     * @param nombre
     * @param curso
     * @param grupo
     * @param deporte
     * @param rol
     */
    public Alumno(String nombre, String curso, String grupo, String deporte, String rol) {
        this.nombre = nombre;
        this.curso = curso;
        this.grupo = grupo;
        this.deporte = deporte;
        this.rol = rol;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * ToString
     * @return cadena
     */
    @Override
    public String toString() {
        return "Alumno{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", curso='" + curso + '\'' +
                ", grupo='" + grupo + '\'' +
                ", deporte='" + deporte + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
