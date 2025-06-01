package com.example.TFG.Repository;

import com.example.TFG.Model.Equipo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repositorio para Equipo
 *
 * Hereda de MondoRepository lo que permite operaciones CRUD
 *
 * <Equipo,String> indica que Equipo es el tipo de entidad y String el tipo de dato del ID de Equipo
 */
public interface EquipoRepository extends MongoRepository<Equipo, String> {
    /**
     * Consulta personalizada para obtener los distintos deportes registrados en la colección.
     * @return lista de deportes cuyo nombre es distinto
     */
    @Aggregation("{ $group: { _id: '$deporte' } }") //agrupa usando como clave el nombre del deporte
    List<String> findDistinctDeportes();
}