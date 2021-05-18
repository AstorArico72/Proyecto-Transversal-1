/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.time.LocalDate;

/**
 *
 * @author melid
 */
public class Alumno {
    
    private int idAlumno;
    private String nombre;
    private String apellido;
    private LocalDate fechNac;
    private int legajo;
    private boolean estado;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, LocalDate fechNac, int legajo, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechNac = fechNac;
        this.legajo = legajo;
        this.estado = estado;
    }

    public Alumno(int idAlumno, String nombre, String apellido, LocalDate fechNac, int legajo, boolean estado) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechNac = fechNac;
        this.legajo = legajo;
        this.estado = estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDate fechNac) {
        this.fechNac = fechNac;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellido=" + apellido + '}';
    }
   
    
}
