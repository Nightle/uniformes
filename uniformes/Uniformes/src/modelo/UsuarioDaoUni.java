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
public class UsuarioDaoUni {
    
    ConexionUni cn = new ConexionUni();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    UsuarioUni usu = new UsuarioUni();
    
    public boolean validar(String usuario, String contraseña){
    boolean ok=false;
    ConexionUni conex = new ConexionUni();
    String sql = "select * from usuario";
    
        try {
            con = cn.getConnection();
            PreparedStatement ps = conex.getConnection().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            if(rs.getString("usuario").trim().equals(usuario) && rs.getString("contraseña").trim().equals(contraseña)){
            ok = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoUni.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ex");
            
        }
        return ok;
    }
    
    
}
