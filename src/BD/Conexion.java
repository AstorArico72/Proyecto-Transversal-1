/*
 * Barrionuevo Pablo: edito para resolver conflictos con los throws
 */
package BD;
/**
 *
 * @author melid
 */
public class Conexion {
    
    private String url = "jdbc:mysql://localhost/universidad-G1";
    private String usuario = "root";
    private String password = "";
    private java.sql.Connection conexion = null;
    
    public Conexion(){
        try {
            Class.forName ("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error Driver: "+ex);
        }
    }

    public Conexion (String url, String usuario, String password)throws ClassNotFoundException{
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        try {
            Class.forName ("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error Driver: \n"+ex);
        }
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
}
