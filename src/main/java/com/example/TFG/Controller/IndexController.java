package com.example.TFG.Controller;

import com.example.TFG.Model.Alumno;
import com.example.TFG.Model.Equipo;
import com.example.TFG.Model.Partido;
import com.example.TFG.Repository.AlumnoRepository;
import com.example.TFG.Repository.EquipoRepository;
import com.example.TFG.Repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class IndexController {

    @Autowired //implementa sin necesidad de constructor
    private PartidoRepository partidoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        List<Alumno> alumnos = alumnoRepository.findAll();
        List<Equipo> equipos = equipoRepository.findAll();
        List<Partido> partidos = partidoRepository.findAll();

        model.addAttribute("alumnos", alumnos);
        model.addAttribute("equipos", equipos);
        model.addAttribute("partidos", partidos);

        return "index";  // nombre del archivo .html sin extensión en /resources/templates
    }
}
