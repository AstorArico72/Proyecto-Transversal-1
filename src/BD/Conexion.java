package BD;

/**
 * Clase para conexion de la base de datos para el TP de Universidad
 * @author Pablo
 */
public class Conexion {
    //Constantes de configuraciones predeterminadas
    private final String pred[] = {"mariadb","localhost","3306","universidadgrupo1","root",""};
    //Atributos
    private java.sql.Connection conexion = null; //null para control a futuro
    private String 
            usuario,                //Nombre del usuario
            contrasenia,            //Contraseña
            libreria,               //Libreria a usar
            direccion,              //direccion de la bd
            puerto,                 //Puerto de la conexion
            bd,                     //Nombre de la BD
            url,                    //Contiene informacion de conexion
            driver;                 //Contiene nombre de la clase para conectar
    //Variable que contiene un mensaje para devolución futura
    private String mensaje = "";
    //3 Constructores
    /**
     * Conectar predeterminado, para conectar a la bd localhost de Universidad, Usuario: root, sin Contraseña, puerto 3306.
     */
    public Conexion(){
        usuario = pred[4];
        contrasenia = pred[5];
        libreria = pred[0];
        direccion = pred[1];
        puerto = pred[2];
        bd = pred[3];
        armar();
        
    }
    /**
     * Conectar con usuario y contraseña, para conectar a la bd localhost de Universidad, puerto 3306.
     * @param usuario
     * @param contrasenia 
     */
    public Conexion(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        libreria = pred[0];
        direccion = pred[1];
        puerto = pred[2];
        bd = pred[3];
        armar();
    }
    /**
     * Conectar estableciendo todos los datos necesarios
     * @param usuario
     * @param contrasenia
     * @param libreria
     * @param direccion
     * @param puerto
     * @param bd 
     */
    public Conexion(String usuario, String contrasenia, String libreria, String direccion, String puerto, String bd) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.libreria = libreria;
        this.direccion = direccion;
        this.puerto = puerto;
        this.bd = bd;
        armar();
    }
    //Metodos tipo setters para modificar conexion
    /**
     * Modifica el usuario
     * @param usuario
     * @return una Conexion
     */
    public Conexion setUsuario(String usuario) {
        if(usuario.isEmpty()) usuario = pred[4];
        this.usuario = usuario;
        //armar();
        if(estaConectado()){
            desconectar();
            conectar();
        }
        return this;
    }
    /**
     * Modifica la contraseña del usuario para la conección.
     * @param contrasenia
     * @return una Conexion
     */
    public Conexion setContrasenia(String contrasenia) {
        if(contrasenia.isEmpty()) contrasenia = pred[5];
        this.contrasenia = contrasenia;
        //armar();
        if(estaConectado()){
            desconectar();
            conectar();
        }
        return this;
    }
    /**
     * Modifica el nombre de la libreria, desde donde se obtiene la clase Driver.
     * @param libreria
     * @return una Conexion
     */
    public Conexion setLibreria(String libreria) {
        if(libreria.isEmpty()) libreria = pred[0];
        this.libreria = libreria;
        armar();
        return this;
    }
    /**
     * Modifica la direccion hacia la base de datos.
     * @param direccion
     * @return una Conexion
     */
    public Conexion setDireccion(String direccion) {
        if(direccion.isEmpty()) direccion = pred[1];
        this.direccion = direccion;
        armar();
        return this;
    }
    /**
     * Modifica el puerto de acceso.
     * @param puerto
     * @return una Conexion
     */
    public Conexion setPuerto(String puerto) {
        if(puerto.isEmpty()) puerto = pred[2];
        this.puerto = puerto;
        armar();
        return this;
    }
    /**
     * Modifica el nombre de la base de datos.
     * @param bd
     * @return una Conexion
     */
    public Conexion setBd(String bd) {
        if(bd.isEmpty()) bd = pred[3];
        this.bd = bd;
        armar();
        return this;
    }

    /**
     * Getter para obtener el mensaje de devolucion.
     * @return Mensaje como String
     */
    public String getMensaje() {return mensaje;}
    
    //Metodos necesarios para trabajar con conexion
    /**
     * De uso interno, arma los string url y driver.
     */
    private void armar(){
        url = "jdbc:";       //tiene la forma: "jdbc:mariadb://localhost:3306/universidad"
        driver = "";         //tiene la forma: "org.mariadb.jdbc.Driver"
        switch(libreria){
            case "mysql":
                url += libreria;
                driver = "com."+libreria;
                mensaje = "Configurando conexión segun "+libreria;
                break;
            case "mariadb":
                url += libreria;
                driver = "org."+libreria;
                mensaje = "Configurando conexión segun "+libreria;
                break;
            default: //caso que sea otra cosa que pase como mariadb
                url += "mariadb";
                driver = "org.mariadb";
                mensaje = "Libreria desconocida, configurando conexión como mariadb";
        }
        url += "://"+direccion+":"+puerto+"/"+bd;
        driver += ".jdbc.Driver";
        if(estaConectado()){ //si esta conectado debe volver a conectarse
            desconectar();
            conectar();
        }
        System.out.println(mensaje);
    }
    /**
     * Metodo privado que devuelve true si la conexion no es nula
     */
    private boolean estaConectado(){return conexion != null;}
    
    /**
     * Conectar con la BD.
     * @return una Conexion
     */
    public Conexion conectar(){ //conectar y reconectar
        try {
            Class.forName(driver);
            conexion = (java.sql.Connection) java.sql.DriverManager.getConnection(url, usuario, contrasenia);
            mensaje = "Se conecto a la BD "+ bd;
        } catch (ClassNotFoundException | java.sql.SQLException ex) {
            conexion = null; //si no puede hacer getConnection que sea null
            mensaje = "Error al conectar a la BD "+ bd +":\n"+ ex;
        }
        System.out.println(mensaje);
        return this;
    }
    /**
     * Desconectar (cerrar la conexion) con la BD.
     */
    public void desconectar(){
        try {
            if(estaConectado()){
                conexion.close();
                mensaje = "Conexion cerrada a la BD"+ bd;
                conexion = null; //para futuros controles
            }
        } catch (java.sql.SQLException ex) {
            mensaje = "Error al cerrar la conexion a la BD "+ bd +":\n"+ ex;
        }
        System.out.println(mensaje);
        //No devuelvo nada luego de desconectar pretendo no continuar utilizando la conexion, hasta volver a conectar
    }
    
    //IDEAS:
    //Utilizar Metodos desde esta misma clase para resolver el TP
    //O usar los statement desde otra clase luego de realizar la conexion
    
    //ejemplo:
    public Conexion altaAlumno(Recursos.Alumno alumno){
        if(estaConectado()){
            //insertar en alumnos los datos de alumno
            mensaje = "Alta de Alumno en Desarrollo...";
        }else{
            mensaje = "Error alta de Alumno requiere primero una conección a la BD.";
        }
        System.out.println(mensaje);
        return this;
    }
    public Conexion bajaAlumno(int id){
        if(estaConectado()){
            mensaje = "Baja de Alumno en Desarrollo...";
            //por implementar en lugar de borrar modificar algo que dija que
            //no esta activo en la universidad
        }else{
            mensaje = "Error baja de Alumno requiere primero una conección a la BD.";
        }
        System.out.println(mensaje);
        return this;
    }
    public Conexion modificarAlumno(int id, Recursos.Alumno alumno){
        if(estaConectado()){
            mensaje = "Modificar Alumno en Desarrollo...";
            //update de un id de alumno con datos de alumno
        }else{
            mensaje = "Error modificar Alumno requiere primero una conección a la BD.";
        }
        System.out.println(mensaje);
        return this;
    }
    public Conexion altaMateria(Recursos.Materia materia){
        if(estaConectado()){
            mensaje = "Alta de Materia en Desarrollo...";
            //insert en Materias los datos de la materia
        }else{
            mensaje = "Error alta de Materia requiere primero una conección a la BD.";
        }
        System.out.println(mensaje);
        return this;
    }
    public Conexion bajaMateria(int id){
        if(estaConectado()){
            mensaje = "Baja de Materia en Desarrollo...";
            //eliminar materia? o modificar algo
        }else{
            mensaje = "Error baja de Materia requiere primero una conección a la BD.";
        }
        System.out.println(mensaje);
        return this;
    }
    public Conexion modificarMateria(int id, Recursos.Materia materia){
        if(estaConectado()){
            mensaje = "Modificar Materia en Desarrollo...";
            //update en Materias los datos de la materia
        }else{
            mensaje = "Error modificar Materia requiere primero una conección a la BD.";
        }
        System.out.println(mensaje);
        return this;
    }
}
