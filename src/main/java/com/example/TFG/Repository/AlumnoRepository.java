package com.example.TFG.Repository;

import com.example.TFG.Model.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio para Alumno
 *
 * Hereda de MondoRepository lo que permite operaciones CRUD
 *
 * <Alumno,String> indica que Alumno es el tipo de entidad y String el tipo de dato del ID de Alumno
 */
public interface AlumnoRepository extends MongoRepository<Alumno,String> {
}