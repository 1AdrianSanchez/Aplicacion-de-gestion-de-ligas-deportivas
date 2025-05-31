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

/**
 * Controlador para gestionar los alumnos
 * Incluye registro, listado y edicion de los mismos.
 */
@Controller
public class AlumnoController {

    @Autowired //implementa AlumnoRepository sin necesidad de constructor
    private AlumnoRepository alumnoRepository;

    /**
     * Muestra el formulario de registro de alumnos
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/registrar-alumno")
    public String mostrarFormularioAlumno(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("alumno", new Alumno()); //creamos un objeto Alumno vacio y para rellenar con el HTML
        return "registrar-alumno";
    }

    /**
     * Procesa el envio del formulario
     * @param alumno objeto Alumno, tiene los datos del formulario
     * @return nombre de la plantilla HTML
     */
    @PostMapping("/registrar-alumno")
    public String enviarFormularioAlumno(@ModelAttribute Alumno alumno) { //asigna automaticamente los datos del ALumno desde el formulario
        alumno.setRol("ALUMNO"); //le asignamos rol por defecto
        alumnoRepository.save(alumno); //lo guardamos en la BBDD
        return "redirect:/registrar-alumno"; //redirigimos para evitar volver a enviar el formulario
    }

    /**
     * Lista de todos los alumnos de la BBDD
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/alumnos")
    public String listarAlumnos(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("alumnos", alumnoRepository.findAll()); //añade todos los alumnos al modelo
        return "lista-alumnos";
    }

    /**
     * Muestra el formulario de edicion de un alumno
     * @param id ID del alumno que se edita
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/alumnos/{id}/editar")
    public String editarAlumno(@PathVariable String id, Model model) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(); //busca alumno por id, lanza excepcion si no existe
        model.addAttribute("alumno", alumno); //añade los datos actuales del alumno al formulario
        return "editar-alumno";
    }

    /**
     * Procesa el envio del formulario y actualiza los datos del alumno editado.
     * @param id ID del alumno que se edita
     * @param alumno objeto Alumno, tiene los nuevos datos
     * @return
     */
    @PostMapping("/alumnos/{id}/editar")
    public String actualizarAlumno(@PathVariable String id, @ModelAttribute Alumno alumno) {
        alumno.setId(id); //asignarle id para evitar duplicados
        alumnoRepository.save(alumno); //lo guardamos en la BBDD
        return "redirect:/alumnos"; //redirigimos al listado de alumnos
    }
}