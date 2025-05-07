package com.example.TFG.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alumnos")
public class Alumno {
    @Id
    private String id;
    private String nombre;
    private String curso;
    private String grupo;
    private String deporte;
    private String rol;

    public Alumno() {
    }

    public Alumno(String nombre, String curso, String grupo, String deporte, String rol) {
        this.nombre = nombre;
        this.curso = curso;
        this.grupo = grupo;
        this.deporte = deporte;
        this.rol = rol;
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
