package com.example.TFG.Controller;

import com.example.TFG.Model.Alumno;
import com.example.TFG.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlumnoController {

    @Autowired //implementa AlumnoRepository sin necesidad de constructor
    private AlumnoRepository alumnoRepository;

    @GetMapping("/registrar-alumno")
    public String mostrarFormularioAlumno(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("alumno", new Alumno()); //creamos un objeto Alumno vacio y para rellenar con el HTML
        return "registrar-alumno";
    }

    @PostMapping("/registrar-alumno")
    public String enviarFormularioAlumno(@ModelAttribute Alumno alumno) { //asigna automaticamente los datos del ALumno desde el formulario
        alumno.setRol("ALUMNO");
        alumnoRepository.save(alumno);
        return "redirect:/registrar-alumno"; //redirigimos para evitar volver a enviar el formulario
    }

    @GetMapping("/alumnos")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoRepository.findAll());
        return "lista-alumnos";
    }

    @GetMapping("/alumnos/{id}/editar")
    public String editarAlumno(@PathVariable String id, Model model) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow();
        model.addAttribute("alumno", alumno); //añade los datos actuales del alumno al formulario
        return "editar-alumno";
    }

    @PostMapping("/alumnos/{id}/editar")
    public String actualizarAlumno(@PathVariable String id, @ModelAttribute Alumno alumno) {
        alumno.setId(id); //asignarle id para evitar duplicados
        alumnoRepository.save(alumno);
        return "redirect:/alumnos";
    }
}