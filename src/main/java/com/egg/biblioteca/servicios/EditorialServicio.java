package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.InvalidArgumentException;
import com.egg.biblioteca.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre){
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales(){
        List <Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    @Transactional(readOnly = true)
    public Editorial buscarPorId(Long id){
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        }else{
            throw new InvalidArgumentException("No se encontro la editorial con ID: " + id);
        }
    }

    @Transactional
    public void modificarEditorial(String nombre, Long id){
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
           
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }else{
            throw new InvalidArgumentException("No se encontro la editorial con ID: " + id);
        }
    }

    @Transactional
    public void eliminarEditorial(Long id){
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            editorialRepositorio.deleteById(id);
        }else{
            throw new InvalidArgumentException("No se encontro la editorial con ID: " + id);
        }
    }

    private void validar(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new InvalidArgumentException("El nombre del autor no puede ser nulo");
        }
    }
}
