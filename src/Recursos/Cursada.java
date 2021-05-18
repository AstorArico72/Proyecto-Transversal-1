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
public class Cursada {
    
    private int idCursada;
    private Alumno alumno;
    private Materia materia;
    private double nota;

    public Cursada() {
    }

    public Cursada(Alumno alumno, Materia materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Cursada(int idCursada, Alumno alumno, Materia materia, double nota) {
        this.idCursada = idCursada;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Cursada{" + "alumno=" + alumno + ", materia=" + materia + ", nota=" + nota + '}';
    }
    
    
    
}
