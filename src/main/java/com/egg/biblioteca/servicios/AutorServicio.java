package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.InvalidArgumentException;
import com.egg.biblioteca.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(Long id){
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        }else{
            throw new InvalidArgumentException("No se encontro el autor con ID: " + id);
        }
    }

    @Transactional
    public void modificarAutor(String nombre, Long id){
        validar(nombre);     
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
           
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }else{
            throw new InvalidArgumentException("No se encontro el autor con ID: " + id);
        }
    }

    @Transactional
    public void eliminarAutor(Long id){
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            autorRepositorio.deleteById(id);
        }else{
            throw new InvalidArgumentException("No se encontro el autor con ID: " + id);
        }
    }

    private void validar(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new InvalidArgumentException("El nombre del autor no puede ser nulo");
        }
    }
}
