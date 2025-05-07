package com.example.TFG.Repository;

import com.example.TFG.Model.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipoRepository extends MongoRepository<Equipo, String> {
}