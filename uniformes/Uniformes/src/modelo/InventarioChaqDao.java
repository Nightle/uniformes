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
public class InventarioChaqDao {
    
    ConexionUni cn = new ConexionUni();
    Connection con; 
    PreparedStatement ps; 
    ResultSet  rs; 
    InventarioChaq inv = new InventarioChaq();
    
    public ArrayList consultarDatos (String sql) throws SQLException{
        System.out.println(sql);
        con =cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        ArrayList InvS = new ArrayList();
        
        while(rs.next()){
            inv.setTalla(rs.getString("talla"));
            inv.setCantidad(rs.getString("cantidad"));
            inv.setCodigo(rs.getInt("codigo"));
            InvS.add(new InventarioChaq(inv.getTalla(),inv.getCantidad(),inv.getCodigo()));
            
            
        }
        
        return InvS;
    }
    
    public ArrayList listarchaquetas() throws SQLException{
       String sql = "Select * from inventario_chaquetas";
       con = cn.getConnection();
       ps = con.prepareStatement(sql);
       rs = ps.executeQuery();
       ArrayList list = new ArrayList();
       while(rs.next()){
           inv.setTalla(rs.getString("talla"));
           inv.setCantidad(rs.getString("cantidad"));
           inv.setCodigo(rs.getInt("codigo"));
           list.add(new InventarioChaq(inv.getTalla(),inv.getCantidad(),inv.getCodigo()));
       }
       return list;
    }
    
}
