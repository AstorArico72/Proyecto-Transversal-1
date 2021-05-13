package TPTransversal;
public class Universidad {

    /**
     * @param args 
     */
    public static void main(String[] args) {
        //Prueba de conexión
        BD.Conexion nuevaConexion = new BD.Conexion();                          //configuro conexion
        nuevaConexion.conectar();                                               //conecto
            //trabajar con la bd
        nuevaConexion.desconectar();                                            //desconecto
    }
    
}
