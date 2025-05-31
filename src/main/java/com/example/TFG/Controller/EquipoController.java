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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/equipos")
    public String listarEquipos(Model model) {
        var equipos = equipoRepository.findAll();
        var alumnos = alumnoRepository.findAll();

        // Crear un mapa de ID de alumno -> nombre del alumno
        Map<String, String> idNombreMap = new HashMap<>();
        for (var alumno : alumnos) {
            idNombreMap.put(alumno.getId(), alumno.getNombre());
        }

        // Para cada equipo, crear listas con nombres de capitán y miembros
        // (asumiendo que capitan y miembros almacenan IDs)
        for (Equipo equipo : equipos) {
            // Cambiar ID de capitán por nombre
            if (equipo.getCapitan() != null) {
                String nombreCapitan = idNombreMap.get(equipo.getCapitan());
                equipo.setCapitan(nombreCapitan != null ? nombreCapitan : equipo.getCapitan());
            }

            // Cambiar IDs de miembros por nombres
            if (equipo.getMiembros() != null) {
                List<String> nombresMiembros = new ArrayList<>();
                for (String miembroId : equipo.getMiembros()) {
                    String nombreMiembro = idNombreMap.get(miembroId);
                    nombresMiembros.add(nombreMiembro != null ? nombreMiembro : miembroId);
                }
                equipo.setMiembros(nombresMiembros);
            }
        }

        model.addAttribute("equipos", equipos);
        model.addAttribute("alumnos", alumnos);
        return "lista-equipos"; // Nombre del HTML que crearás
    }
}