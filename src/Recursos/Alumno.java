/*
 * Barrionuevo Pablo: Edite para usar con el sql de Astor
 */
package Recursos;

import java.time.LocalDate;

/**
 *
 * @author melid
 */
public class Alumno {

    //..........................................................................Constantes
    //Constante con todos los valores default no requeridos
    private final String[] DEFAULT = {"1", "No posee", "No posee", java.time.LocalDate.now().toString(), "Sin comentarios"};

    //..........................................................................Atributos
    private int idAlumno;                       //id,                                       requerido (NULL) autoincremental
    private String nombre;                      //nombre y apellido o apellido y nombre,    requerido
    private int legajo;                         //numero de legajo,                         requerido
    private int estado;                         //0: inactivo, 1: cursando                  no req                                      DEFAULT[0]
    private java.time.LocalDate fechNac;        //YYYY-MM-DD                                requerido
    private String correo;                      //correo de contacto                        no req                                      DEFAULT[1]
    private String tel;                         //telefono de contacto                      no req                                      DEFAULT[2]
    private java.time.LocalDate fechIns;        //fecha idem formato                        no requerido fecha actual para inscribir    DEFAULT[3]
    private String comentarios;                 //comentarios                               no req                                      DEFAULT[4]

    //..........................................................................Constructores
    /**
     * Alumno con datos default
     */
    public Alumno() {
        //datos requeridos
        idAlumno = 0;
        nombre = "";
        legajo = 0;
        fechNac = java.time.LocalDate.now();
        //datos no requeridos
        completarDefault();
    }

    /**
     * Alumno con solamente datos requeridos
     *
     * @param idAlumno
     * @param nombre
     * @param legajo
     * @param fechNac
     */
    public Alumno(int idAlumno, String nombre, int legajo, java.time.LocalDate fechNac) {
        //datos requeridos
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.legajo = legajo;
        this.fechNac = fechNac;
        //datos no requeridos
        completarDefault();
    }

    public Alumno(String nombre, LocalDate fechNac, int estado) {
        this.nombre = nombre;
        this.estado = estado;
        this.fechNac = fechNac;
    }

    /**
     * Alumno con todos los datos
     *
     * @param idAlumno
     * @param nombre
     * @param legajo
     * @param estado
     * @param fechNac
     * @param correo
     * @param tel
     * @param fechIns
     * @param comentarios
     */
    public Alumno(
            int idAlumno, String nombre, int legajo, int estado,
            java.time.LocalDate fechNac, String correo, String tel,
            java.time.LocalDate fechIns, String comentarios) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.legajo = legajo;
        this.estado = estado;
        this.fechNac = fechNac;
        this.correo = correo;
        this.tel = tel;
        this.fechIns = fechIns;
        this.comentarios = comentarios;
    }
    //..........................................................................Getters de todos los atributos

    public int getIdAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public int getEstado() {
        return estado;
    }

    public java.time.LocalDate getFechaNacimiento() {
        return fechNac;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return tel;
    }

    public java.time.LocalDate getFechaInscripcion() {
        return fechIns;
    }

    public String getComentarios() {
        return comentarios;
    }

    //..........................................................................Setters de todos los atributos
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFechaNacimiento(java.time.LocalDate fechNac) {
        this.fechNac = fechNac;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String tel) {
        this.tel = tel;
    }

    public void setFechaInscripcion(java.time.LocalDate fechIns) {
        this.fechIns = fechIns;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Alumno{"
                + "idAlumno=" + idAlumno
                + ", nombre=" + nombre
                + ", legajo=" + legajo
                + ", estado=" + estado
                + ", fecha de Nacimiento=" + fechNac
                + ", correo=" + correo
                + ", telefono=" + tel
                + ", fecha de Inscripcion=" + fechIns
                + ", comentarios=" + comentarios + '}';
    }

    //..........................................................................Metodos Privados
    /**
     * Metodo privado para completar con valores default los datos no requeridos
     */
    private void completarDefault() {
        estado = Integer.parseInt(DEFAULT[0]);
        correo = DEFAULT[1];
        tel = DEFAULT[2];
        fechIns = java.time.LocalDate.parse(DEFAULT[3]);
        comentarios = DEFAULT[4];
    }

}
