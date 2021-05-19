package universidad;
import java.util.HashMap;
import java.time.LocalDate;
import static universidad.Universidad.globalDTF;

public class Alumno {
    private String nombre;
    private int legajo;
    private int estado;
    private String correo;
    private String comentarios;
    private LocalDate fechaNacimiento;
    //Creo que ésos son todos los atributos privados.
    
    public Alumno (int id, String nombre, int estado, String nacimiento) {
        this.nombre = nombre;
        this.legajo = id; //Si el legajo es auto-incremental, entonces hay que borrar ése int.
        this.estado = estado;
        this.fechaNacimiento = LocalDate.parse (nacimiento, globalDTF); //El DTF estático de la clase Universidad.
    }
    /*
    SOBRECARGA DE CONSTRUCTORES, INICIO!
    */
    public Alumno (int id, String nombre, int estado, String nacimiento, String correo) {
        this.nombre = nombre;
        this.legajo = id;
        this.estado = estado;
        this.fechaNacimiento = LocalDate.parse (nacimiento, globalDTF);
        this.correo = correo;
    }
    
    public Alumno (int id, String nombre, int estado, String nacimiento, String correo, String comentarios) {
        this.nombre = nombre;
        this.legajo = id;
        this.estado = estado;
        this.fechaNacimiento = LocalDate.parse (nacimiento, globalDTF);
        this.correo = correo;
        this.comentarios = comentarios;
    }
    
    public HashMap <Integer, String> listaMaterias = new HashMap <> (); //Aquí guardamos las materias, va a usarse en la clase Inscripción.
    //El int es exportado de el ID de la materia, y el String es el nombre, también exportado de la clase Materia.
    
    public String getNombre () {
        return this.nombre;
    }
    
    public int getID () {
        return this.legajo;
    }
    
    public int getEstado () {
        return this.estado;
    }
    
    public LocalDate getFechaNacimiento () {
        return this.fechaNacimiento;
    }
}