package com.example.TFG.Controller;

import com.example.TFG.Model.Alumno;
import com.example.TFG.Model.Partido;
import com.example.TFG.Repository.AlumnoRepository;
import com.example.TFG.Repository.EquipoRepository;
import com.example.TFG.Repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para gestionar los partidos
 * Incluye creacion y listado de los mismos
 * Incluye registro de resultados
 */
@Controller
public class PartidoController {

    @Autowired //implementa sin necesidad de constructor
    private PartidoRepository partidoRepository;
    @Autowired //implementa sin necesidad de constructor
    private EquipoRepository equipoRepository;
    @Autowired //implementa sin necesidad de constructor
    private AlumnoRepository alumnoRepository;

    /**
     * Muestra el formulario de creacion de partidos
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/partidos/nuevo")
    public String mostrarFormularioPartido(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("partido", new Partido()); //creamos un objeto Partido vacio y para rellenar con el HTML
        model.addAttribute("deportes", equipoRepository.findDistinctDeportes()); //lista de los deportes para poder seleccionarlos
        model.addAttribute("equipos", equipoRepository.findAll()); //lista de los equipos para poder seleccionarlos
        return "nuevo-partido";
    }

    /**
     * Procesa el envio del formulario
     * @param partido objeto Partido, tiene los datos del formulario
     * @return nombre de la plantilla HTML
     */
    @PostMapping("/partidos/nuevo")
    public String crearPartido(@ModelAttribute Partido partido) { //asigna automaticamente los datos del Partido desde el formulario
        partidoRepository.save(partido); //lo guardamos en la BBDD
        return "redirect:/partidos/nuevo"; //redirigimos para evitar volver a enviar el formulario
    }

    /**
     * Lista de todos los partidos de la BBDD
     * Transforma la informacion para mostrar los nombres en lugar de los IDs
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/partidos")
    public String verPartidos(Model model) { //Model permite pasar datos a la vista
        List<Partido> partidos = partidoRepository.findAll(); //obtenemos una lista de los partidos

        //Map para transformar IDs a nombres
        Map<String, String> equipoNombres = new HashMap<>();
        equipoRepository.findAll().forEach(eq -> equipoNombres.put(eq.getId(), eq.getNombre())); //insertamos en el map ID/nombre

        model.addAttribute("partidos", partidos); //añadimos los partidos al modelo
        model.addAttribute("equipoNombres", equipoNombres); // ñadimos los nombres modificados al modelo
        return "lista-partidos";
    }

    /**
     * Muestra el formulario de registro de resultados
     * Muestra la informacion del partido y el Map de nombres de los equipos
     * @param id ID del partido que se edita
     * @param model objeto Model, envia datos a la vista
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/partidos/{id}/registrar-resultado")
    public String mostrarFormularioResultado(@PathVariable String id, Model model) {
        Partido partido = partidoRepository.findById(id).orElseThrow(); //busca partido por id, lanza excepcion si no existe
        model.addAttribute("partido", partido); //añade el partido al modelo

        //Map para transformar IDs a nombres
        Map<String, String> equipoNombres = new HashMap<>();
        equipoRepository.findAll().forEach(eq -> equipoNombres.put(eq.getId(), eq.getNombre())); //insertamos en el map ID/nombre
        model.addAttribute("equipoNombres", equipoNombres); //añadimos los nombres modificados al modelo

        return "registrar-resultado";
    }

    /**
     * Registra el resultado de un partido
     * Solo puede hacerlo un usuario cuyo rol sea Capitan o profesor
     * @param id ID del partido que se edita
     * @param resultado Resultado del partido
     * @param usuarioId ID del usuario que ingresa el resultado
     * @return nombre de la plantilla HTML
     */
    @PostMapping("/partidos/{id}/resultado")
    public String registrarResultado(@PathVariable String id, @RequestParam String resultado, @RequestParam String usuarioId) {
        Alumno usuario = alumnoRepository.findById(usuarioId).orElse(null); //busca alumno por id
        if (usuario != null && ("CAPITAN".equals(usuario.getRol()) || "PROFESOR".equals(usuario.getRol()))) { //solo dejar si es CAPITAN O PROFESOR
            Partido partido = partidoRepository.findById(id).orElseThrow(); //busca partido por id, lanza excepcion si no existe
            partido.setResultado(resultado); //establecemos resultado
            partidoRepository.save(partido); //guardamos resultado
        }
        return "redirect:/partidos"; //redirigimos a la lista de partidos
    }
}
