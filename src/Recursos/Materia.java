/*
 * Barrionuevo Pablo: edito para usar el SQL de Astor
 */
package Recursos;

/**
 *
 * @author melid
 */
public class Materia {
    
    //Constante Default
    final private String DEF[] = {"0","Nueva Materia","1","1"};
    //Atributos
    private int idMateria;
    private String nombreMateria;
    private int anio;
    private boolean estado;

    public Materia() {
        idMateria = Integer.parseInt(DEF[0]);
        nombreMateria = DEF[1];
        anio = Integer.parseInt(DEF[2]);
        estado = Boolean.parseBoolean(DEF[3]);
    }

    public Materia(String nombreMateria, int anio, boolean estado) {
        idMateria = Integer.parseInt(DEF[0]);
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
        return "Materia{" + "idMateria=" + idMateria + ", nombreMateria=" + nombreMateria + ", anio=" + anio + ", estado=" + estado + '}';
    }

    
}
