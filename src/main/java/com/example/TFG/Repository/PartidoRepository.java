package com.example.TFG.Repository;

import com.example.TFG.Model.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio para Partido
 *
 * Hereda de MondoRepository lo que permite operaciones CRUD
 *
 * <Partido,String> indica que Partido es el tipo de entidad y String el tipo de dato del ID de Partido
 */
public interface PartidoRepository extends MongoRepository<Partido, String> {
}
