package BD;

/**
 * Clase para conexion de la base de datos para el TP de Universidad
 * @author Pablo
 */
public class Conexion {
    //Constantes
    private final String pred[] = {"mariadb","localhost","3306","universidadgrupo1","root",""};
    //Atributos
    private java.sql.Connection conexion;
    private String 
            usuario,                //Nombre del usuario
            contrasenia,            //Contraseña
            libreria,               //Libreria a usar
            direccion,              //direccion de la bd
            puerto,                 //Puerto de la conexion
            bd,                     //Nombre de la BD
            url,                    //Contiene informacion de conexion
            driver;                 //Contiene nombre de la clase para conectar
    //Constructores
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
    
    /**
     * Arma los String URL, y DRIVER para utilizar antes de conectar, o luego de modificar algo
     */
    public void armar(){
        url = "jdbc:";       //tiene la forma: "jdbc:mariadb://localhost:3306/universidad"
        driver = "";         //tiene la forma: "org.mariadb.jdbc.Driver"
        //try {
            //if(!conexion.isClosed())desconectar();
            switch(libreria){
                case "mysql":
                    url += libreria;
                    driver = "com."+libreria;
                    System.out.println("Configurando conexión segun "+libreria);
                    break;
                case "mariadb":
                    url += libreria;
                    driver = "org."+libreria;
                    System.out.println("Configurando conexión segun "+libreria);
                    break;
                default: 
                    url += "mariadb";
                    driver = "org.mariadb";
                    System.out.println("Libreria desconocida, configurando conexión como mariadb");
            }
            url += "://"+direccion+":"+puerto+"/"+bd;
            driver += ".jdbc.Driver";
        //} catch (java.sql.SQLException | java.lang.NullPointerException ex) {
        //    System.out.println("Error al armar la conexión a la BD "+bd+":\n"+ex);
        //}
        
    }
    /**
     * Conectar con la BD
     * @return 
     */
    public java.sql.Connection conectar(){
        try {
            Class.forName(driver);
            conexion = (java.sql.Connection) java.sql.DriverManager.getConnection(url, usuario, contrasenia);
            System.out.println("Se conecto a la BD "+ bd);
        } catch (ClassNotFoundException | java.sql.SQLException ex) {
            System.out.println("Error al conectar a la BD "+ bd +":\n"+ ex);
        }
        return conexion;
    }
    /**
     * Desconectar (cerrar la conexion) con la BD
     */
    public void desconectar(){
        try {
            conexion.close();
            System.out.println("Conexion cerrada a la BD"+ bd);
        } catch (java.sql.SQLException ex) {
            System.out.println("Error al cerrar la conexion a la BD "+ bd +":\n"+ ex);
        }
    }
    
    //IDEAS:
    //Metodos a utilizar para resolver el TP
    //1 - Alta de alumno
    //2 - Baja de alumno
    //3 - Modificar 1 alumno
    //...
    //O usar los statement desde otra clase luego de realizar la conexion
    
    //ejemplo:
    public boolean altaAlumno(){
        System.out.println("En Desarrollo...");
        return false;
    }
    public boolean bajaAlumno(){
        System.out.println("En Desarrollo...");
        return false;
    }
    public boolean modificarAlumno(){
        System.out.println("En Desarrollo...");
        return false;
    }
}
