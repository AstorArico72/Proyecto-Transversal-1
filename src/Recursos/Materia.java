/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

/**
 *
 * @author melid
 */
public class Materia {
    
    private int idMateria;
    private String nombreMateria;
    private int anio;
    private boolean estado;

    public Materia() {
    }

    public Materia(String nombreMateria, int anio, boolean estado) {
        this.nombreMateria = nombreMateria;
        this.anio = anio;
        this.estado = estado;
    }

    public Materia(int idMateria, String nombreMateria, int anio, boolean estado) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.anio = anio;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "nombreMateria=" + nombreMateria + ", anio=" + anio + '}';
    }
    
    
    
    
}
