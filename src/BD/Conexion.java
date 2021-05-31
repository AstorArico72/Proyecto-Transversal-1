/*
 * Barrionuevo Pablo: edito para resolver conflictos con los throws
 * Barrionuevo Pablo: edito la conexion para que funcione con UniversidadGrupo1
 */
package BD;
/**
 *
 * @author melid
 */
public class Conexion {
    //Constante
    final private String 
        DRIVER = "org.mariadb.jdbc.Driver",
        CONF[] = {"localhost","8008","UniversidadGrupo1","root",""};
    //Atributos
    private String url = "jdbc:mysql://"+ CONF[0] +":"+ CONF[1] +"/"+ CONF[2];
    private String usuario = CONF[3];
    private String password = CONF[4];
    private java.sql.Connection conexion = null;
    
    public Conexion(){
        registrarDriver();
    }

    public Conexion (String url, String usuario, String password)throws ClassNotFoundException{
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        registrarDriver();
    }
    
    public java.sql.Connection getConexion(){
        if(conexion == null){
            try {
                conexion = java.sql.DriverManager.getConnection( url + "?useLegacyDatetimeCode=false&serverTimezone=UTC"// + "&user"+ usuario + "&password=" +password
                        ,usuario, password);
            } catch (java.sql.SQLException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al Conectar: \n"+ex);
            }
        }
        return conexion;
    }

    private void registrarDriver() {
        try {
            Class.forName (DRIVER);
        } catch (ClassNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error Driver:\n"+ex.getMessage());
        }
    }
}
