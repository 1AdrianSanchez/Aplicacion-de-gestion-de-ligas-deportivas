package com.example.TFG.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal que actua como pagina de inicio
 */
@Controller
public class IndexController {

    /**
     * Muestra la vista principal (http://localhost:8080/)
     * @return nombre de la plantilla HTML
     */
    @GetMapping("/")
    public String inicio() {
        return "index";
    }
}
