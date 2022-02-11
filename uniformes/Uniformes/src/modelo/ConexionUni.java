/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author troll
 */
public class ConexionUni {
    Connection con;
    public String bd = "uniformes";
    public String login = "root";
    public String password = "";
    public String url = "jdbc:mysql://localhost:3306/"+bd+"?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    public ConexionUni(){
     try{
       Class.forName("com.mysql.cj.jdbc.Driver");
       con = DriverManager.getConnection(url,login,password);
     }catch(ClassNotFoundException | SQLException e){
       System.err.println("Error " + e);
     }
    }
    
    public Connection getConnection(){
    return con;
    }
}
