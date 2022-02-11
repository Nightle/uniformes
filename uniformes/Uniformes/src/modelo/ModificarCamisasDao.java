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
public class ModificarCamisasDao {
    ConexionUni cn = new ConexionUni();
    Connection con; 
    PreparedStatement ps; 
    ResultSet  rs;
    InventarioCami inv = new InventarioCami();
    
    public boolean adicionar(InventarioCami inv){
        String sql = "insert into inventario_camisas(talla,cantidad_cc_red,cantidad_cc_per,cantidad_ac_red,cantidad_ac_v,cantidad_ac_per,codigo)values('"+ inv.getTalla() + "','" + inv.getCantidadccred() + "','" + inv.getCantidadccper() + "','" + inv.getCantidadacred() + "','" + inv.getCantidadacv() + "','" + inv.getCantidadacper() + "','" + inv.getCodigo() + "')";
        con = cn.getConnection();
        
        System.out.println(sql);
        
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarCamisasDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    public InventarioCami consultardato (String talla) throws SQLException{
        String sql = "select *from inventario_camisas where talla='" + talla + "'";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        if(rs.next()){
            inv.setTalla(rs.getString("talla"));
            inv.setCantidadccred(rs.getString("cantidad_cc_red"));
            inv.setCantidadccper(rs.getString("cantidad_cc_per"));
            inv.setCantidadacred(rs.getString("cantidad_ac_red"));
            inv.setCantidadacv(rs.getString("cantidad_ac_v"));
            inv.setCantidadacper(rs.getString("cantidad_ac_per"));
            inv.setCodigo(rs.getInt("codigo"));
            return inv;
        }
        
        return null;
    }
    
    public boolean actualizar(InventarioCami inv){
    String sql = "update inventario_camisas set cantidad_cc_red = '"+ inv.getCantidadccred() +"' ,cantidad_cc_per = '" + inv.getCantidadccper() + "' ,cantidad_ac_red = '" + inv.getCantidadacred() + "' ,cantidad_ac_v = '" + inv.getCantidadacv() + "' ,cantidad_ac_per = '" + inv.getCantidadacper() + "' where talla = '" + inv.getTalla() +"'";
    con = cn.getConnection();
    
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarCamisasDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
        
        return true;
    }
    
    public boolean eliminar(String talla){
        String sql = "delete from inventario_camisas where talla='" + talla + "'";
        con = cn.getConnection();
        
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarCamisasDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
        
        return true;
    }
}
