/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Recursos.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author melid
 */
public class CursadaData {
    
   
    Conexion conexion;
    Connection con;
    
    public CursadaData (Conexion con){
        this.conexion = conexion;
    }
        
    
    
    
    List <Materia>obtenerMateriasNoCursadas(int id){
     List <Materia> matNoCur = new ArrayList<>();
     Materia mat = null;
     String sql = "Select * from materia where idMateria not in (Select materia.idMateria from materia"+"cursada where materia.idmateria =cursada.idMateria And cursada.idalumno=?";
             
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                mat = new Materia();
                mat.setNombreMateria(rs.getString("nombre"));
                mat.setIdMateria(rs.getInt("idmateria"));
                mat.setEstado(rs.getBoolean("activo"));
                mat.setAnio(rs.getInt("anio"));
                matNoCur.add(mat);
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
        return matNoCur;
}
}
