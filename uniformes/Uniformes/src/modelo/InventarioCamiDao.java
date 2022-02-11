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
import java.util.ArrayList;

/**
 *
 * @author troll
 */
public class InventarioCamiDao {
    ConexionUni cn = new ConexionUni();
    Connection con; 
    PreparedStatement ps; 
    ResultSet  rs; 
    InventarioCami inv = new InventarioCami();
    
    public ArrayList consultarDatos (String sql) throws SQLException{
        System.out.println(sql);
        con =cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        ArrayList InvC = new ArrayList();
        
        while(rs.next()){
            inv.setTalla(rs.getString("talla"));
            inv.setCantidadccred(rs.getString("cantidad_cc_red"));
            inv.setCantidadccper(rs.getString("cantidad_cc_per"));
            inv.setCantidadacred(rs.getString("cantidad_ac_red"));
            inv.setCantidadacv(rs.getString("cantidad_ac_v"));
            inv.setCantidadacper(rs.getString("cantidad_ac_per"));
            inv.setCodigo(rs.getInt("codigo"));
            
            InvC.add(new InventarioCami(inv.getTalla(),inv.getCantidadccred(),inv.getCantidadccper(),inv.getCantidadacred(),inv.getCantidadacv(),inv.getCantidadacper(),inv.getCodigo()));
            
            
        }
        
        return InvC;
    }
    
    public ArrayList listarcamisas() throws SQLException{
       String sql = "Select * from inventario_camisas";
       con = cn.getConnection();
       ps = con.prepareStatement(sql);
       rs = ps.executeQuery();
       ArrayList list = new ArrayList();
       while(rs.next()){
           inv.setTalla(rs.getString("talla"));
            inv.setCantidadccred(rs.getString("cantidad_cc_red"));
            inv.setCantidadccper(rs.getString("cantidad_cc_per"));
            inv.setCantidadacred(rs.getString("cantidad_ac_red"));
            inv.setCantidadacv(rs.getString("cantidad_ac_v"));
            inv.setCantidadacper(rs.getString("cantidad_ac_per"));
            inv.setCodigo(rs.getInt("codigo"));
            
            list.add(new InventarioCami(inv.getTalla(),inv.getCantidadccred(),inv.getCantidadccper(),inv.getCantidadacred(),inv.getCantidadacv(),inv.getCantidadacper(),inv.getCodigo()));
       }
       return list;
    } 
}
