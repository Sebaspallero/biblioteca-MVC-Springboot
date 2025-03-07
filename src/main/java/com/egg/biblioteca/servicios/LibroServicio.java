package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.InvalidArgumentException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

    private final LibroRepositorio libroRepositorio;
    private final AutorRepositorio autorRepositorio;
    private final EditorialRepositorio editorialRepositorio;

    @Autowired
    public LibroServicio(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio, EditorialRepositorio editorialRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
        this.editorialRepositorio = editorialRepositorio;
    }

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, Long idAutor, Long idEditorial) {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listaLibros() {
        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional(readOnly = true)
    public Libro buscarPorId(Long isbn) {
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        if (respuesta.isPresent()) {
            return respuesta.get();
        }else{
            throw new InvalidArgumentException("No se encontro un libro con ID: " + isbn);
        }
    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, int ejemplares, Long idAutor, Long idEditorial) {
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor nuevoAutor = autorRepositorio.findById(idAutor)
            .orElseThrow(() -> new InvalidArgumentException("No se encontro el autor con ID: " + idAutor));

        Editorial nuevaEditorial = editorialRepositorio.findById(idEditorial)
            .orElseThrow(() -> new InvalidArgumentException("No se encontro la editorial con ID: " + idEditorial));

        Libro nuevolibro = libroRepositorio.findById(isbn)
            .orElseThrow(() -> new InvalidArgumentException("No se encontro el libro con ID: " + isbn));

        nuevolibro.setTitulo(titulo);
        nuevolibro.setEjemplares(ejemplares);
        nuevolibro.setEditorial(nuevaEditorial);
        nuevolibro.setAutor(nuevoAutor);

        libroRepositorio.save(nuevolibro);
    }

    @Transactional
    public void eliminarLibro(Long id){
        Libro libro = libroRepositorio.findById(id)
            .orElseThrow(() -> new InvalidArgumentException("No se encontro el libro con ID: " + id));

        libroRepositorio.delete(libro);
    }


    private void validar(Long isbn, String titulo, Integer ejemplares, Long idAutor, Long idEditorial) {
        if (isbn == null) {
            throw new InvalidArgumentException("el isbn no puede ser nulo ");
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new InvalidArgumentException("el nombre no puede ser nulo o estar vacío");
        }
        if (ejemplares == null) {
            throw new InvalidArgumentException("Los ejemplares no pueden ser nulo");
        }
        if (idAutor == null) {
            throw new InvalidArgumentException("El id del autor no puede ser nulo ");
        }
        if (idEditorial == null) {
            throw new InvalidArgumentException("El id de la editorial no puede ser nulo ");
        }
    }
}
