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
public class MateriaData {
    //Atributos
    private Connection con;
    //Constructores
    public MateriaData (Conexion con){
        try {
            this.con = con.getConexion();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
    }
    //Metodos
    public void guardarMateria(Materia ma){
        String sql = "Insert into materia (nombre, anio, activo) Values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, ma.getNombreMateria());
            ps.setInt(2, ma.getAnio());
            ps.setBoolean(3, ma.isEstado());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                ma.setIdMateria(rs.getInt(1));
            }
            ps.close();
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
    }
    
    public List <Materia> obtenerMaterias(){
        Materia mat;
        ArrayList <Materia> materias = new ArrayList<>();
        String sql ="Select * From materia " ;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                mat = new Materia();
                mat.setNombreMateria(rs.getString("nombre"));
                mat.setIdMateria(rs.getInt("idmateria"));
                mat.setEstado(rs.getBoolean("activo"));
                mat.setAnio(rs.getInt("anio"));
                
                materias.add(mat);
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
        return materias;
    }
    
    public Materia buscarMateria(int id){
        Materia mat = new Materia();
        String sql  = "Select * From materia where idMateria =?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                mat.setNombreMateria(rs.getString("nombre"));
                mat.setIdMateria(rs.getInt("idmateria"));
                mat.setEstado(rs.getBoolean("activo"));
                mat.setAnio(rs.getInt("anio"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexiion");
        }
        return mat;
    }
     public void desactivarMateria(int id){
         String sql = " update meteria set activo=false where idMateria=?";
         PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
             ps.setInt(1, id);
             ps.executeUpdate();
             ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
     }
     
     public void actualizarMateria(Materia mat){
         String sql = "update materia set nombre=?, anio=?, where idMateria=?";
        try {
             try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                 ps.setString(1, mat.getNombreMateria());
                 ps.setInt(2, mat.getAnio());
                 ps.setInt(3, mat.getIdMateria());
                 
                 ps.executeUpdate();
             }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
     }
    
}
        
    
    

