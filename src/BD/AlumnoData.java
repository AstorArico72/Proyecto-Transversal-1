/*
 * Barrionuevo Pablo: Edito para que funcione con el SQL de Astor
 */
package BD;

/**
 * AlumnoData
 * @author melid
 */
public class AlumnoData {
    //Constantes
    final private String 
        TABLA = "alumno",
        CAMPOS[] = {"ID_Alumno","Nombre","Legajo","Estado","FechaNacimiento","Correo","Teléfono","FechaInscripción","Comentarios"};
    //Atributos
    private java.sql.Connection con;
    
    //Constructores
    /**
     * Alumno data para trabajar con la BD de la tabla alumno.
     * @param con 
     */
    public AlumnoData(Conexion con){
        this.con = con.getConexion();
    }    
    //Metodos
    public int guardarAlumno(Recursos.Alumno alumno){
        int idnuevo = 0;
        String sql = "INSERT INTO "+ TABLA +" ("
                + CAMPOS[1] +", "
                + CAMPOS[2] +", "
                + CAMPOS[3] +", "
                + CAMPOS[4] +", "
                + CAMPOS[5] +", "
                + CAMPOS[6] +", "
                + CAMPOS[7] +", "
                + CAMPOS[8] +") "
                + "VALUES (?,?,?,?,?,?,?,?);";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setString(   1, alumno.getNombre());
            ps.setInt(      2, alumno.getLegajo());
            ps.setInt(      3, alumno.getEstado());
            ps.setDate(     4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setString(   5, alumno.getCorreo());
            ps.setString(   6, alumno.getTelefono());
            ps.setDate(     7, java.sql.Date.valueOf(alumno.getFechaInscripcion()));
            ps.setString(   8, alumno.getComentarios());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()) idnuevo = rs.getInt(1);
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Guardar:\n"+ex.getMessage());
        }
        return idnuevo;
    }
    
    public java.util.List<Recursos.Alumno> obtenerAlumnos(){
        Recursos.Alumno alumno;
        java.util.ArrayList<Recursos.Alumno> alumnos = new java.util.ArrayList<>();
        String sql ="SELECT * FROM "+ TABLA +";";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                alumno = new Recursos.Alumno();
                alumno.setIdAlumno(rs.getInt(CAMPOS[0]));
                alumno.setNombre(rs.getString(CAMPOS[1]));
                alumno.setLegajo(rs.getInt(CAMPOS[2]));
                alumno.setEstado(rs.getInt(CAMPOS[3]));
                alumno.setFechaNacimiento(java.time.LocalDate.parse(rs.getString(CAMPOS[4])));
                alumno.setCorreo(rs.getString(CAMPOS[5]));
                alumno.setTelefono(rs.getString(CAMPOS[6]));
                alumno.setFechaInscripcion(java.time.LocalDate.parse(rs.getString(CAMPOS[7])));
                alumno.setComentarios(rs.getString(CAMPOS[8]));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Listar:\n"+ex.getMessage());
        }
        return alumnos;
    }
    
    public Recursos.Alumno buscarAlumno(int id){
        Recursos.Alumno alumno = null;
        String sql  = "SELECT * FROM "+ TABLA +" WHERE "+ CAMPOS[0] +"=?;";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            alumno= new Recursos.Alumno();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(CAMPOS[0]));
                alumno.setNombre(rs.getString(CAMPOS[1]));
                alumno.setLegajo(rs.getInt(CAMPOS[2]));
                alumno.setEstado(rs.getInt(CAMPOS[3]));
                alumno.setFechaNacimiento(java.time.LocalDate.parse(rs.getString(CAMPOS[4])));
                alumno.setCorreo(rs.getString(CAMPOS[5]));
                alumno.setTelefono(rs.getString(CAMPOS[6]));
                if(rs.getString(CAMPOS[7])!=null)
                alumno.setFechaInscripcion(java.time.LocalDate.parse(rs.getString(CAMPOS[7])));
                else{
                    alumno.setFechaInscripcion(null);
                }
                alumno.setComentarios(rs.getString(CAMPOS[8]));
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Buscar:\n"+ex.getMessage());
        }
        return alumno;
    }
     public void desactivarAlumno(int id){
        String sql = "UPDATE "+ TABLA +" SET "+ CAMPOS[3] +"=0 WHERE "+ CAMPOS[0] +"=?;";
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Desactivar:\n"+ex.getMessage());
        }
     }
     
     public void actualizarAlumno(Recursos.Alumno alumno){
        String sql = "UPDATE "+ TABLA +" SET "
                + CAMPOS[1] +"=?, "
                //+ CAMPOS[2] +"=?, " //Legajo
                + CAMPOS[4] +"=?, "
                + CAMPOS[5] +"=?, "
                + CAMPOS[6] +"=?, "
                + CAMPOS[7] +"=?, "
                + CAMPOS[8] +"=? "
                + "WHERE "+ CAMPOS[0] +"=?;";
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, alumno.getNombre());
            //ps.setInt(2, alumno.getLegajo());
            ps.setDate(  2, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setString(3, alumno.getCorreo());
            ps.setString(4, alumno.getTelefono());
            ps.setDate(  5, java.sql.Date.valueOf(alumno.getFechaInscripcion()));
            ps.setString(6, alumno.getComentarios());
            ps.setInt(7, alumno.getIdAlumno());
            ps.executeUpdate();
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Actualizar:\n"+ex.getMessage());
        }
     }
}
