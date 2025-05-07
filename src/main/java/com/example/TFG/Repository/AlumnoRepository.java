package com.example.TFG.Repository;

import com.example.TFG.Model.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlumnoRepository extends MongoRepository<Alumno,String> {
}