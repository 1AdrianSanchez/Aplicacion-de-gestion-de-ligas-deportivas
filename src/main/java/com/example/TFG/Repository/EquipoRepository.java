package com.example.TFG.Repository;

import com.example.TFG.Model.Equipo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipoRepository extends MongoRepository<Equipo, String> {
    @Aggregation("{ $group: { _id: '$deporte' } }")
    List<String> findDistinctDeportes();
}