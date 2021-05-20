/*
 * Barrionuevo Pablo: modifico para usar con el sql de Astor
 */
package Recursos;

/**
 *
 * @author melid
 */
public class Inscripcion {
    //
    final private String DEF[] = {"0",java.time.LocalDate.now().toString(),"8.5"};
    //Atributos
    private int idCursada;
    private Alumno alumno;
    private Materia materia;
    private java.time.LocalDate fechInsc;
    private double nota;

    public Inscripcion() {
        idCursada = Integer.parseInt(DEF[0]);
        alumno = new Alumno();
        materia = new Materia();
        fechInsc = java.time.LocalDate.parse(DEF[1]);
        nota = Double.parseDouble(DEF[2]);
    }

    public Inscripcion(Alumno alumno, Materia materia, java.time.LocalDate fechInsc,double nota) {
        idCursada = Integer.parseInt(DEF[0]);
        this.alumno = alumno;
        this.materia = materia;
        this.fechInsc = fechInsc;
        this.nota = nota;
    }

    public Inscripcion(int idCursada, Alumno alumno, Materia materia, java.time.LocalDate fechInsc, double nota) {
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
