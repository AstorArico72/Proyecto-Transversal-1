/*
 * Barrionuevo Pablo: modifico para usar con el sql de Astor
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
    private java.time.LocalDate fechInsc;
    private double nota;

    public Cursada() {
    }

    public Cursada(Alumno alumno, Materia materia, java.time.LocalDate fechInsc,double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.fechInsc = fechInsc;
        this.nota = nota;
    }

    public Cursada(int idCursada, Alumno alumno, Materia materia, java.time.LocalDate fechInsc, double nota) {
        this.idCursada = idCursada;
        this.alumno = alumno;
        this.materia = materia;
        this.fechInsc = fechInsc;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Cursada{" + "alumno=" + alumno + ", materia=" + materia + ", nota=" + nota + ", inscripto en fecha="+ fechInsc +"}";
    }
}
