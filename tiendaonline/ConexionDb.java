/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espesw.tiendaonline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Marloon
 */
public class ConexionDb {
    public static Connection getConnection(){
        Connection conexion = null;
        try{
            String urldb = "jdbc:mysql://localhost:3306/tiendaonline";
            String usuariodb = "root";
            String passworddb = "";
            
            conexion=DriverManager.getConnection(urldb, usuariodb,passworddb);
            
            System.out.println("Conectado a la base de datos!!");
        }catch(SQLException e){
            //thrown e;
            System.err.println("Error de conexion a la Base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
