package TPTransversal;
public class Universidad {

    /**
     * main
     * @param args 
     */
    public static void main(String[] args) {
        
        //Prueba de clases
        Recursos.Alumno a1 = new Recursos.Alumno();
        Recursos.Materia m1 = new Recursos.Materia();
        //Prueba de conexión
        BD.Conexion nuevaConexion = new BD.Conexion();                          //configuro conexion
        nuevaConexion.conectar();                                               //me conecto
            //trabajar con la bd
            //Ejemplo de nuevo uso, cada modificacion requiere que se re-conecte
        nuevaConexion.setUsuario("").setContrasenia("").setPuerto("").altaAlumno(a1).altaMateria(m1);
            //Ultimo mensaje de Conexion
        javax.swing.JOptionPane.showMessageDialog(null, nuevaConexion.getMensaje());
        nuevaConexion.desconectar();                                            //me desconecto
            //Prueba de Vistas
        Vistas.Principal.iniciar();
    }
    
}
