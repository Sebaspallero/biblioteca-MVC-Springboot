package com.egg.biblioteca.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "libros")
public class Libro {
    
    @Id
    private Long isbn;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ejemplares")
    private Integer ejemplares;

    @Column(name = "alta")
    @Temporal(TemporalType.DATE)
    private Date alta;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    
    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    
    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", ejemplares=" + ejemplares + ", alta=" + alta
                + ", autor=" + autor + ", editorial=" + editorial + "]";
    }
}
