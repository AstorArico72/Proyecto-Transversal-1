package BD;
/**
 * Clase para conexion de la base de datos para el TP de Universidad
 * IDEA: usar unicamente una conexion para resolver el TP, tanto para alumnos
 * como para materias, de esta forma no hacemos tanto uso de la bd, 
 * solamente cuando se requiera.
 * @author Pablo
 */
public class Conexion {
    //Constantes de configuraciones predeterminadas
    private final String[] PRED = {"mariadb","localhost","3306","universidadgrupo1","root",""};
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
    //Prueba de Instrucciones a ejecutar como listado de trabajo
    java.util.List<String> instrucciones = new java.util.ArrayList<>();
    //3 Constructores
    /**
     * Conectar predeterminado, para conectar a la bd localhost de Universidad, Usuario: root, sin Contraseña, puerto 3306.
     */
    public Conexion(){
        usuario = PRED[4];
        contrasenia = PRED[5];
        libreria = PRED[0];
        direccion = PRED[1];
        puerto = PRED[2];
        bd = PRED[3];
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
        libreria = PRED[0];
        direccion = PRED[1];
        puerto = PRED[2];
        bd = PRED[3];
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
        if(usuario.isEmpty()) usuario = PRED[4];
        this.usuario = usuario;
        //armar();
        reconectar();
        return this;
    }
    /**
     * Modifica la contraseña del usuario para la conección.
     * @param contrasenia
     * @return una Conexion
     */
    public Conexion setContrasenia(String contrasenia) {
        if(contrasenia.isEmpty()) contrasenia = PRED[5];
        this.contrasenia = contrasenia;
        //armar();
        reconectar();
        return this;
    }
    /**
     * Modifica el nombre de la libreria, desde donde se obtiene la clase Driver.
     * @param libreria
     * @return una Conexion
     */
    public Conexion setLibreria(String libreria) {
        if(libreria.isEmpty()) libreria = PRED[0];
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
        if(direccion.isEmpty()) direccion = PRED[1];
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
        if(puerto.isEmpty()) puerto = PRED[2];
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
        if(bd.isEmpty()) bd = PRED[3];
        this.bd = bd;
        armar();
        return this;
    }

    /**
     * Setter para modificar mensaje, se crea para poder hacer uso del mismo desde las clases hijas de conexion
     * @param mensaje 
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    //Getters
    /**
     * Getter para obtener el mensaje de devolucion.
     * @return Mensaje como String
     */
    public String getMensaje() {return mensaje;}
    /**
     * Getter de conexion.
     * @return java.sql.Connection utilizado en esta conexion.
     */
    public java.sql.Connection getConexion(){return conexion;}
    
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
        reconectar();//si esta conectado que se reconecte
        //prueba de lista de trabajo
        instrucciones = new java.util.ArrayList<>();
        System.out.println(mensaje);
    }
    /**
     * Metodo para saber si conexion es null.
     * @return true si no es null
     */
    public boolean estaConectado(){return conexion != null;}
    
    /**
     * Metodo para reconectar
     * @return un objeto de tipo Conexion
     */
    public Conexion reconectar(){
        if(estaConectado()){
            desconectar();
            conectar();
        }
        return this;
    }
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
            }else{
                mensaje = "Conexion ya cerrada a la BD"+ bd;
            }
        } catch (java.sql.SQLException ex) {
            mensaje = "Error al cerrar la conexion a la BD "+ bd +":\n"+ ex;
        }
        System.out.println(mensaje);
        //No devuelvo nada luego de desconectar pretendo no continuar utilizando la conexion, hasta volver a conectar
    }
    
    //Prueba para usar lista de trabajo
    /**
     * Agrega una instrucción a la lista de trabajo, luego usar el metodo trabajar()
     * La instrucción a agregar no se espera que devuelva nada, usar para insert o update
     * En desarrollo para mejoras
     * @param aAgregar String de una instrucción SQL
     * @return una Conexion
     */
    public Conexion agregarInstruccion(String aAgregar) {
        instrucciones.add(aAgregar);
        return this;
    }
    public Conexion trabajar(){
        //ejecutar todas las instrucciones almacenadas,
        //estas instrucciones no deberian devolver resultset
        //ver que pasa si error en algunas de ellas
        mensaje = "";
        instrucciones.forEach(instruccion -> {
            try {//no me gusta aca el try catch
                java.sql.ResultSet resultado = conexion.createStatement().executeQuery(instruccion);
                if(!resultado.next()) mensaje += "Instrucción SQL: "+ instruccion +".\n";
                else{ 
                    mensaje += "Instrucción SQL con resultSet!!: "+ instruccion +".\n";
                    //que hacer con el resultset
                }
            } catch (java.sql.SQLException ex) {
                mensaje += "No se logro ejecutar la instrucción SQL: "+ instruccion +"\nError: "+ ex +".\n";
            }
        });
        instrucciones.clear(); //vacio lo que ya se ejecuto
        System.out.println(mensaje);
        return this;
    }
}
