package com.egg.biblioteca.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.InvalidArgumentException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") 
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales",editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn,
                       @RequestParam(required = false) String titulo,
                       @RequestParam(required = false) Integer ejemplares,
                       @RequestParam(required = false) Long idAutor,
                       @RequestParam(required = false) Long idEditorial,
                       ModelMap modelo) {
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado correctamente");
            return "redirect:/inicio";
        } catch (InvalidArgumentException ex) {
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {


        List<Libro> libros = libroServicio.listaLibros();
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
    }

    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales",editoriales);
        modelo.put("libro", libroServicio.buscarPorId(isbn));


        return "libro_modificar.html";
    }


    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, Long idAutor, Long idEditorial, ModelMap modelo)  {
        try {
            libroServicio.modificarLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            return "redirect:../lista";
        } catch (InvalidArgumentException ex) {
            modelo.put("error", ex.getMessage());
            return "libro_modificar.html";
        }
    }

}
