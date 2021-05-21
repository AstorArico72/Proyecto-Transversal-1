/*
 * Barrionuevo Pablo: edito para que funcione con el SQL de Astor
 */
package BD;

/**
 * Clase Inscripcion para usar con conexiones a la tabla materia de la BD
 *
 * @author melid
 */
public class InscripcionData {

    //..........................................................................Constantes
    final private String TINSCRIPCION = "inscripcion",
            TMATERIA = "materia",
            TALUMNO = "alumno",
            CAMPOSINSCRIPCION[] = {"ID_Inscripcion", "ID_Alumno", "ID_Materia", "FechaInscripcion", "Nota"},
            CAMPOSMATERIA[] = {"ID_Materia", "Nombre", "Año", "Estado"},
            CAMPOSALUMNO[] = {"ID_Alumno", "Nombre", "Legajo", "Estado", "FechaNacimiento", "Correo", "Teléfono", "FechaInscripción", "Comentarios"};
    //..........................................................................Atributos
    private java.sql.Connection con;

    //..........................................................................Constructores
    /**
     * Nueva instancia de InscripcionData
     *
     * @param con
     */
    public InscripcionData(Conexion con) {
        this.con = con.getConexion();
    }

    //..........................................................................Metodos
    /**
     * Busca el idAlumno de un alumno en las inscripciones, devolviendo una
     * lista de todas las materias donde no esta inscripto.
     *
     * @param idAlumno
     * @return Lista de Materias
     */
    public java.util.List<Recursos.Materia> obtenerMateriasNoCursadas(int idAlumno) {
        java.util.List<Recursos.Materia> matNoCur = new java.util.ArrayList<>();
        Recursos.Materia mat;
        //String sql = "SELECT * FROM materia where idMateria not in (Select materia.idMateria from materia"+"cursada where materia.idmateria =cursada.idMateria And cursada.idalumno=?";
        String sql = "SELECT " + TMATERIA + ".* FROM " + TMATERIA + ", " + TINSCRIPCION + " "
                + "WHERE " + TMATERIA + "." + CAMPOSMATERIA[0] + " <> " + TINSCRIPCION + "." + CAMPOSINSCRIPCION[2] + " AND " //id materias son distintos
                + TINSCRIPCION + "." + CAMPOSINSCRIPCION[1] + " = ?;";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql);//,java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();
            while (rs.next()) {
                mat = new Recursos.Materia();
                mat.setIdMateria(rs.getInt(CAMPOSMATERIA[0]));
                mat.setNombreMateria(rs.getString(CAMPOSMATERIA[1]));
                mat.setAnio(rs.getInt(CAMPOSMATERIA[2]));
                mat.setEstado(rs.getBoolean(CAMPOSMATERIA[3]));
                matNoCur.add(mat);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Obtener materias no cursadas del id:" + idAlumno + "\n" + ex.getMessage());
        }
        return matNoCur;
    }

    /**
     * Listar todas las materias donde esta inscripto el alumno buscar por
     * idAlumno
     *
     * @param idAlumno
     * @return Lista de Materias
     */
    public java.util.List<Recursos.Materia> listarMaterias(int idAlumno) {
        java.util.List<Recursos.Materia> respuesta = new java.util.ArrayList<>();
        Recursos.Materia mat;
        String sql = "SELECT " + TMATERIA + ".* FROM " + TMATERIA + ", " + TINSCRIPCION + " "
                + "WHERE " + TMATERIA + "." + CAMPOSMATERIA[0] + " = " + TINSCRIPCION + "." + CAMPOSINSCRIPCION[2] + " AND " //id materias son iguales
                + TINSCRIPCION + "." + CAMPOSINSCRIPCION[1] + " = ?;";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql);//,java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();
            while (rs.next()) {
                mat = new Recursos.Materia();
                mat.setIdMateria(rs.getInt(CAMPOSMATERIA[0]));
                mat.setNombreMateria(rs.getString(CAMPOSMATERIA[1]));
                mat.setAnio(rs.getInt(CAMPOSMATERIA[2]));
                mat.setEstado(rs.getBoolean(CAMPOSMATERIA[3]));
                respuesta.add(mat);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Listar Materias del idAlumno:" + idAlumno + "\n" + ex.getMessage());
        }
        return respuesta;
    }

    /**
     * Listar todos los alumnos inscriptos en la materia, buscar por idMateria
     *
     * @param idMateria
     * @return Lista de Alumnos
     */
    public java.util.List<Recursos.Alumno> listarAlumnos(int idMateria) {
        java.util.List<Recursos.Alumno> respuesta = new java.util.ArrayList<>();
        Recursos.Alumno alu;
        String sql = "SELECT " + TALUMNO + ".* FROM " + TALUMNO + ", " + TINSCRIPCION + " "
                + "WHERE " + TALUMNO + "." + CAMPOSALUMNO[0] + " = " + TINSCRIPCION + "." + CAMPOSINSCRIPCION[1] + " AND " //id alumnos son iguales
                + TINSCRIPCION + "." + CAMPOSINSCRIPCION[2] + " = ?;";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql);//,java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idMateria);
            rs = ps.executeQuery();
            while (rs.next()) {
                alu = new Recursos.Alumno();
                alu.setIdAlumno(rs.getInt(CAMPOSALUMNO[0]));
                alu.setNombre(rs.getString(CAMPOSALUMNO[1]));
                alu.setLegajo(rs.getInt(CAMPOSALUMNO[2]));
                alu.setEstado(rs.getInt(CAMPOSALUMNO[3]));
                alu.setFechaNacimiento(java.time.LocalDate.parse(rs.getString(CAMPOSALUMNO[4])));
                alu.setCorreo(rs.getString(CAMPOSALUMNO[5]));
                alu.setTelefono(rs.getString(CAMPOSALUMNO[6]));
                alu.setFechaInscripcion(java.time.LocalDate.parse(rs.getString(CAMPOSALUMNO[7])));
                alu.setComentarios(rs.getString(CAMPOSALUMNO[8]));
                respuesta.add(alu);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de Conexion al Listar Alumnos del idMateria:" + idMateria + "\n" + ex.getMessage());
        }
        return respuesta;
    }

    /**
     * Inscribir un alumno a una materia
     *
     * @param idAlumno
     * @param idMateria
     * @return el id de la inscripcion
     */
    public int inscribirAlumno(int idAlumno, Recursos.Materia materia, java.time.LocalDate fecha, double nota) {
        int idInscripcion = 0;
        String sql = "INSERT INTO " + TINSCRIPCION + " ("
                + CAMPOSINSCRIPCION[1] + ", "
                + CAMPOSINSCRIPCION[2] + ", "
                + CAMPOSINSCRIPCION[3] + ", "
                + CAMPOSINSCRIPCION[4] + ") VALUES (?,?,?,?);";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        String sql2 = "SELECT * FROM inscripcion WHERE ID_Alumno =" + idAlumno + " AND ID_Materia=" + materia.getIdMateria();

        try {
            ps = con.prepareStatement(sql2);
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, idAlumno);
                ps.setInt(2, materia.getIdMateria());
                ps.setDate(3, java.sql.Date.valueOf(fecha));
                ps.setDouble(4, nota);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idInscripcion = rs.getInt(1);
                }
            }else{
                System.out.println("El alumno ya esta Inscripto en esa materia.");
            }

            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al inscribir un alumno a una materia:\n" + ex.getMessage());
        }
        return idInscripcion;
    }

    /**
     * Guardar Inscripcion
     *
     * @param inscripcion
     * @return
     */
    public int guardarInscripcion(Recursos.Inscripcion inscripcion) {
        int idInscripcion = 0;
        String sql = "INSERT INTO " + TINSCRIPCION + " ("
                + CAMPOSINSCRIPCION[1] + ", "
                + CAMPOSINSCRIPCION[2] + ", "
                + CAMPOSINSCRIPCION[3] + ", "
                + CAMPOSINSCRIPCION[4] + ") VALUES (?,?,?,?);";
        java.sql.PreparedStatement ps;
        java.sql.ResultSet rs;
        try {
            ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.setDate(3, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));
            ps.setDouble(4, inscripcion.getNota());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idInscripcion = rs.getInt(1);
            }
            ps.close();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al inscribir un alumno a una materia:\n" + ex.getMessage());
        }
        return idInscripcion;
    }

    /**
     * Des-incribir alumno por id de inscripcion
     *
     * @param id
     */
    public void desinscribirAlumno(int id) {
        String sql = "DELETE FROM " + TINSCRIPCION + " WHERE " + CAMPOSINSCRIPCION[0] + " = ?;";
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al des-inscribir un alumno:\n" + ex.getMessage());
        }
    }

    public void desinscribirAlumno(Recursos.Inscripcion inscripcion) {
        String sql = "DELETE FROM " + TINSCRIPCION + " WHERE " + CAMPOSINSCRIPCION[0] + " = ?;";
        java.sql.PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, inscripcion.getIdInscripcion());
            ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al des-inscribir un alumno:\n" + ex.getMessage());
        }
    }
}
