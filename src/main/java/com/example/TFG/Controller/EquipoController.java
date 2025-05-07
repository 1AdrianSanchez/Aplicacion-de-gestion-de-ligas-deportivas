package com.example.TFG.Controller;

import com.example.TFG.Model.Equipo;
import com.example.TFG.Repository.AlumnoRepository;
import com.example.TFG.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EquipoController {

    @Autowired //implementa sin necesidad de constructor
    private EquipoRepository equipoRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/registrar-equipo")
    public String mostrarFormularioEquipo(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("equipo", new Equipo()); //creamos un objeto Equipo vacio y para rellenar con el HTML
        model.addAttribute("alumnos", alumnoRepository.findAll()); //lista de los alumnos para poder seleccionarlos
        return "registrar-equipo";
    }

    @PostMapping("/registrar-equipo")
    public String crearEquipo(@ModelAttribute Equipo equipo) { //asigna automaticamente los datos del Equipo desde el formulario
        equipoRepository.save(equipo);
        return "redirect:/registrar-equipo"; //redirigimos para evitar volver a enviar el formulario
    }
}