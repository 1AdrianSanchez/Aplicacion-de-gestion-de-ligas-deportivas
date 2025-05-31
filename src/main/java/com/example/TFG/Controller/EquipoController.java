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

/**
 * Controlador para gestionar los equipos
 * Incluye registro y listado de los mismos.
 */
@Controller
public class EquipoController {

    @Autowired //implementa sin necesidad de constructor
    private EquipoRepository equipoRepository;
    @Autowired //implementa sin necesidad de constructor
    private AlumnoRepository alumnoRepository;

    /**
     * Muestra el formulario de registro de equipos
     * Incluye los alumnos disponibles para asignar capitan y miembros
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/registrar-equipo")
    public String mostrarFormularioEquipo(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("equipo", new Equipo()); //creamos un objeto Equipo vacio y para rellenar con el HTML
        model.addAttribute("alumnos", alumnoRepository.findAll()); //lista de los alumnos para poder seleccionarlos
        return "registrar-equipo";
    }

    /**
     * Procesa el envio del formulario
     * @param equipo objeto Equipo, tiene los datos del formulario
     * @return nombre de la plantilla HTML
     */
    @PostMapping("/registrar-equipo")
    public String crearEquipo(@ModelAttribute Equipo equipo) { //asigna automaticamente los datos del Equipo desde el formulario
        equipoRepository.save(equipo); //lo guardamos en la BBDD
        return "redirect:/registrar-equipo"; //redirigimos para evitar volver a enviar el formulario
    }

    /**
     * Lista de todos los equipos de la BBDD
     * Transforma la informacion para mostrar los nombres en lugar de los IDs
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/equipos")
    public String listarEquipos(Model model) { //Model permite pasar datos a la vista
        var equipos = equipoRepository.findAll(); //obtenemos todos los equipos
        var alumnos = alumnoRepository.findAll(); //obtenemos todos los alumnos

        //Map para transformar IDs a nombres
        Map<String, String> idNombreMap = new HashMap<>();
        for (var alumno : alumnos) {
            idNombreMap.put(alumno.getId(), alumno.getNombre()); //asociamos el ID del alumno con el nombre del alumno
        }

        //Recorremos cada equipos para sustituir los IDs por los nombres
        for (Equipo equipo : equipos) {
            //Cambio e ID de campitan por su nombre
            if (equipo.getCapitan() != null) { //verificamos que haya un capitan
                String nombreCapitan = idNombreMap.get(equipo.getCapitan()); //buscamos el nombre del capitan en el Map
                equipo.setCapitan(nombreCapitan != null ? nombreCapitan : equipo.getCapitan()); //sustituimos el ID con el nombre
            }

            //Cambio de ID de miembros por sus nombres
            if (equipo.getMiembros() != null) { //verificamos si hay una lista de miembros
                List<String> nombresMiembros = new ArrayList<>();
                for (String miembroId : equipo.getMiembros()) { //recorremos la lista de miembros
                    String nombreMiembro = idNombreMap.get(miembroId); //buscamos el nombre del miembro en el Map
                    nombresMiembros.add(nombreMiembro != null ? nombreMiembro : miembroId); //agregamos el nombre a la nueva lista
                }
                equipo.setMiembros(nombresMiembros); //actualizamos l lista con los nombres
            }
        }

        model.addAttribute("equipos", equipos); //añade la lista modificada al modelo
        model.addAttribute("alumnos", alumnos); //añade los alumnos al modelo
        return "lista-equipos";
    }
}