/*
 * Barrionuevo Pablo: modifico para usar con el sql de Astor
 */
package Recursos;

import java.time.LocalDate;

/**
 *
 * @author melid
 */
public class Inscripcion {
    //
    final private String DEF[] = {"0",java.time.LocalDate.now().toString(),"8.5"};
    //Atributos
    private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private java.time.LocalDate fechInsc;
    private double nota;

    public Inscripcion() {
        idInscripcion = Integer.parseInt(DEF[0]);
        alumno = new Alumno();
        materia = new Materia();
        fechInsc = java.time.LocalDate.parse(DEF[1]);
        nota = Double.parseDouble(DEF[2]);
    }

    public Inscripcion(Alumno alumno, Materia materia, java.time.LocalDate fechInsc,double nota) {
        idInscripcion = Integer.parseInt(DEF[0]);
        this.alumno = alumno;
        this.materia = materia;
        this.fechInsc = fechInsc;
        this.nota = nota;
    }

    public Inscripcion(int idCursada, Alumno alumno, Materia materia, java.time.LocalDate fechInsc, double nota) {
        this.idInscripcion = idCursada;
        this.alumno = alumno;
        this.materia = materia;
        this.fechInsc = fechInsc;
        this.nota = nota;
    }
    //..........................................................................Getters
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public LocalDate getFechaInscripcion() {
        return fechInsc;
    }

    public double getNota() {
        return nota;
    }
    
    //..........................................................................Setters
    public void setIdInscripcion(int idCursada) {
        this.idInscripcion = idCursada;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setFechaInscripcion(LocalDate fechInsc) {
        this.fechInsc = fechInsc;
    }

    public void setNota(double nota) {    
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Cursada{" + "alumno=" + alumno + ", materia=" + materia + ", nota=" + nota + ", inscripto en fecha="+ fechInsc +"}";
    }
}
