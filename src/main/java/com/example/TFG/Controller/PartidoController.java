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

@Controller
public class PartidoController {

    @Autowired //implementa sin necesidad de constructor
    private PartidoRepository partidoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/partidos/nuevo")
    public String mostrarFormularioPartido(Model model) { //Model permite pasar datos a la vista
        model.addAttribute("partido", new Partido()); //creamos un objeto Partido vacio y para rellenar con el HTML
        model.addAttribute("deportes", equipoRepository.findDistinctDeportes());
        model.addAttribute("equipos", equipoRepository.findAll()); //lista de los equipos para poder seleccionarlos
        return "nuevo-partido";
    }

    @PostMapping("/partidos/nuevo")
    public String crearPartido(@ModelAttribute Partido partido) { //asigna automaticamente los datos del Partido desde el formulario
        partidoRepository.save(partido);
        return "redirect:/partidos/nuevo"; //redirigimos para evitar volver a enviar el formulario
    }

    @GetMapping("/partidos")
    public String verPartidos(Model model) {
//        model.addAttribute("partidos", partidoRepository.findAll());
        List<Partido> partidos = partidoRepository.findAll();

        // Mapeamos los IDs a nombres para poder mostrarlos fácilmente
        Map<String, String> equipoNombres = new HashMap<>();
        equipoRepository.findAll().forEach(eq -> equipoNombres.put(eq.getId(), eq.getNombre()));

        model.addAttribute("partidos", partidos);
        model.addAttribute("equipoNombres", equipoNombres); // añadimos al modelo
        return "lista-partidos";
    }

    @PostMapping("/partidos/{id}/resultado")
    public String registrarResultado(@PathVariable String id, @RequestParam String resultado, @RequestParam String usuarioId) {
        Alumno usuario = alumnoRepository.findById(usuarioId).orElse(null);
        if (usuario != null && ("CAPITAN".equals(usuario.getRol()) || "PROFESOR".equals(usuario.getRol()))) { //solo dejar si es CAPITAN O PROFESOR
            Partido partido = partidoRepository.findById(id).orElseThrow();
            partido.setResultado(resultado);
            partidoRepository.save(partido);
        }
        return "redirect:/partidos";
    }

    @GetMapping("/partidos/{id}/registrar-resultado")
    public String mostrarFormularioResultado(@PathVariable String id, Model model) {
        Partido partido = partidoRepository.findById(id).orElseThrow();
        model.addAttribute("partido", partido);

        // Añadir equipoNombres al modelo
        Map<String, String> equipoNombres = new HashMap<>();
        equipoRepository.findAll().forEach(eq -> equipoNombres.put(eq.getId(), eq.getNombre()));
        model.addAttribute("equipoNombres", equipoNombres);

        return "registrar-resultado";
    }

}
