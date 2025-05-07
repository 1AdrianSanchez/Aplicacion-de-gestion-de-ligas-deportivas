package com.example.TFG.Repository;

import com.example.TFG.Model.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidoRepository extends MongoRepository<Partido, String> {
}
