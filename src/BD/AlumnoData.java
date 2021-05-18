package BD;
/**
 * Clase Data para la tabla alumnos de la BD
 * @author Pablo
 */
public class AlumnoData{
    //configuraciones para tener a mano
    private final String 
        TABLAALUMNOS = "alumnos",                                               //Nombre de la tabla alumnos
        CAMPOS[] = {"id_alumno","legajo","dni","apellidos","nombre","edad",
            "nacimiento","domicilio","contacto","fecha_inscripcion","estado"};     //Campos o encabezados de la tabla [0] es id, [ultimo] es estado
    
    //atributos
    private Conexion conexion = null;
    
    //constructores
    /**
     * 
     * @param conexion 
     */
    public AlumnoData(Conexion conexion){
        this.conexion = conexion;
    }
    //getters y setters
    
    //metodos para resolver el TP
    /**
     * Inserta una instrucción SQL a la lista de trabajo de la conexión con
     * los datos del alumno para luego al usar el metodo trabajar() de la conexión
     * insertar estos datos en la tabla Alumnos de la BD.
     * @param alumno Un Alumno
     * @return AlumnoData
     */
    public AlumnoData altaAlumno(Recursos.Alumno alumno){
        
        return this;
    }
    /**
     * Inserta una instrucción SQL a la lista de trabajo de la conexión con
     * lo necesario para cambia el estado del alumno a (0).
     * Luego al usar el metodo trabajar() de la conexión se realizarán los cambios a la BD.
     * @param id
     * @return 
     */
    public AlumnoData bajaAlumno(int id){
        
        return this;
    }
    /**
     * Inserta una instrucción SQL a la lista de trabajo de la conexión con
     * lo necesario para cambiar los datos segun el id al alumno.
     * Luego al usar el metodo trabajar() de la conexión se realizarán los cambios a la BD.
     * @param id
     * @param alumno
     * @return 
     */
    public AlumnoData modificarAlumno(int id, Recursos.Alumno alumno){
        
        return this;
    }
    //listar, buscar, obtener id, etc...
}
