/*
 * Barrionuevo Pablo: modifico para que funcione con la SQL de Astor
 * Barrionuevo Pablo: edito actualizar por alguna razon da error de sql
 */
package BD;
/**
 * Clase Materia para usar con conexiones a la tabla materia de la BD
 * @author melid
 */
public class MateriaData {
    //Constantes
    final private String 
        TABLA = "materia",
        CAMPOS[] = {"ID_Materia","Nombre","Año","Estado"};
    //Atributos
    private java.sql.Connection con;
    //Constructores
    public MateriaData (Conexion con){
        this.con = con.getConexion();
    }
    //Metodos
    public int guardarMateria(Recursos.Materia ma){
        int idnuevo = 0;
        String sql = "INSERT INTO "+ TABLA +" ("+ CAMPOS[1] +", "+ CAMPOS[2] +", "+ CAMPOS[3] +") VALUES (?,?,?);";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ma.getNombreMateria());
            ps.setInt(2, ma.getAnio());
            ps.setBoolean(3, ma.isEstado());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                //ma.setIdMateria(rs.getInt(1));//esto modifica el id en el parametro pero no en la variable que se pasa
                idnuevo = rs.getInt(1);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Guardar:\n"+ex.getMessage());
        }
        return idnuevo;
    }
    
    public java.util.List<Recursos.Materia> obtenerMaterias(){
        Recursos.Materia mat;
        java.util.ArrayList<Recursos.Materia> materias = new java.util.ArrayList<>();
        String sql ="SELECT * FROM "+ TABLA +";";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql);//, java.sql.Statement.RETURN_GENERATED_KEYS);
            rs = ps.executeQuery();
            while(rs.next()){
                mat = new Recursos.Materia();
                mat.setIdMateria(rs.getInt(CAMPOS[0]));             //id
                mat.setNombreMateria(rs.getString(CAMPOS[1]));      //nombre
                mat.setAnio(rs.getInt(CAMPOS[2]));                  //anio
                mat.setEstado(rs.getBoolean(CAMPOS[3]));            //estado
                materias.add(mat);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Listar:\n"+ex.getMessage());
        }
        return materias;
    }
    
    public Recursos.Materia buscarMateria(int id){
        Recursos.Materia mat = null;
        String sql  = "SELECT * FROM "+ TABLA +" WHERE "+ CAMPOS[0] +"=?;";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            mat = new Recursos.Materia();
            ps = con.prepareStatement(sql);//, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                mat.setIdMateria(rs.getInt(CAMPOS[0]));
                mat.setNombreMateria(rs.getString(CAMPOS[1]));
                mat.setAnio(rs.getInt(CAMPOS[2]));
                mat.setEstado(rs.getBoolean(CAMPOS[3]));
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Buscar:\n"+ex.getMessage());
        }
        return mat;
    }
    public void desactivarMateria(int id){
        String sql = "UPDATE "+ TABLA +" SET "+ CAMPOS[3] +"=0 WHERE "+ CAMPOS[0] +"=?;";
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);//, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Desactivar:\n"+ex.getMessage());
        }
    }
    public void activarMateria(int id){
        String sql = "UPDATE "+ TABLA +" SET "+ CAMPOS[3] +"=1 WHERE "+ CAMPOS[0] +"=?;";
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);//, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Desactivar:\n"+ex.getMessage());
        }
     }
    public void actualizarMateria(Recursos.Materia mat){
        String sql = "UPDATE "+ TABLA +" SET "+ CAMPOS[1] +"=?, "+ CAMPOS[2] +"=? WHERE "+ CAMPOS[0] +"=?;"; //habia una , de mas
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);//, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, mat.getNombreMateria());
            ps.setInt(2, mat.getAnio());
            ps.setInt(3, mat.getIdMateria());
            ps.executeUpdate();
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Actualizar:\nSQL:"+ sql +"\n"+ex.getMessage());
        }
     }
    
}
        
    
    

