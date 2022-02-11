/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author troll
 */
public class ModificarSudaderasDao {
    ConexionUni cn = new ConexionUni();
    Connection con; 
    PreparedStatement ps; 
    ResultSet  rs; 
    InventarioSuda inv = new InventarioSuda();
    
    public boolean adicionar (InventarioSuda inv){
        String sql = "insert into inventario_sudaderas(talla,cantidadcc,cantidadac,codigo) values('" + inv.getTalla() + "','" + inv.getCantidadcc() + "','" + inv.getCantidadac() + "','" + inv.getCodigo() + "')";
        
        con = cn.getConnection();
        
        System.out.println(sql);
        
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarSudaderasDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" error " + ex);
            return false;
        }
        return true;
    }
    
    public InventarioSuda consultardato (String talla) throws SQLException{
        String sql = "select *from inventario_sudaderas where talla='" + talla + "'";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        if(rs.next()){
            inv.setTalla(rs.getString("talla"));
            inv.setCantidadcc(rs.getString("cantidadcc"));
            inv.setCantidadac(rs.getString("cantidadac"));
            inv.setCodigo(rs.getInt("codigo"));
            return inv;
        }
        
        return null;
    }
    
    public boolean actualizar(InventarioSuda inv){
        String sql = "update inventario_sudaderas set cantidadcc='"+ inv.getCantidadcc() +"' ,cantidadac='"+ inv.getCantidadac() +"' where talla= '"+ inv.getTalla() +"'";

        
        con = cn.getConnection();
        
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarSudaderasDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    public boolean eliminar(String talla){
        String sql = "delete from inventario_sudaderas where talla='" + talla + "'";
        
        con = cn.getConnection();
        
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarSudaderasDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
