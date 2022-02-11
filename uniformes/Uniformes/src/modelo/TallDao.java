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
public class TallDao {
    
    ConexionUni cn = new ConexionUni();
    Connection con;
    PreparedStatement ps; 
    ResultSet  rs;
    Talla tall = new Talla();
    
    public Talla consultaDato(int codigo) throws SQLException{
        String sql = "select * from tallas where codigo = " + codigo;
        con =cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            tall.setCodigo(rs.getInt("codigo"));
            tall.setTalla(rs.getString("talla"));
        }
        
        return tall;
    }
    
    public ArrayList consultaDatos() throws SQLException{
        String sql = "select * from tallas";
        ArrayList arrtall = new ArrayList();
        con =cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery(); 
        while(rs.next()){
            arrtall.add(new Talla(rs.getInt("codigo"), rs.getString("talla")));
        }
        return arrtall;
    }
}
