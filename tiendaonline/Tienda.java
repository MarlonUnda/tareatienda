/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espesw.tiendaonline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Tienda {
      public Tienda(String producto, int codigo, int cantidad, double precio, boolean stock) {
        this.producto = producto;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.stock = stock;
    }
      public Tienda() {
    // Constructor vacío
    }
     
    String producto;
    int codigo;
    int cantidad;
    double precio;
    boolean stock;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }
    
    public void GuardarDatosTienda() {
    Connection conexionDb = null;
    PreparedStatement parametro = null;
    
    try {
        // Establecer la conexión a la base de datos
        conexionDb = ConexionDb.getConnection();
        
        // Crear la sentencia SQL para la inserción
        String sentenciaSql = "INSERT INTO Tienda (nameProducto, cantidad, precio, stock) VALUES (?, ?, ?, ?)";
        
        // Configurar los parámetros de la sentencia SQL
        parametro = conexionDb.prepareStatement(sentenciaSql);
        parametro.setString(1, this.getProducto());
        parametro.setInt(2, this.getCantidad());
        parametro.setDouble(3, this.getPrecio());
        parametro.setBoolean(4, this.isStock());

        // Ejecutar la sentencia SQL
        parametro.executeUpdate();
        
        // Cerrar la conexión
        conexionDb.close();
        
        System.out.println("Registro guardado correctamente en la base de datos.");
    } catch (SQLException ex) {
        System.out.println("Error al intentar guardar el registro en la base de datos: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        // Cerrar los recursos en un bloque finally para asegurar que se cierren incluso si ocurre una excepción
        try {
            if (parametro != null) {
                parametro.close();
            }
            if (conexionDb != null) {
                conexionDb.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión o el PreparedStatement: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

    
    public void ModificarDatosTienda(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE tienda SET producto=?, cantidad=?, precio=?, stock=? WHERE codigo=?";
        try {
            //Configurar los parametros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getProducto());
            parametro.setInt(2, this.getCantidad());
            parametro.setDouble(3, this.getPrecio());
            parametro.setBoolean(4, this.isStock());
            parametro.setInt(5, this.getCodigo());

            //Ejecutar la sentencia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     
    
public ArrayList<Tienda> ObtenerTienda(){
    Connection conexionDb = ConexionDb.getConnection();
    ResultSet rsTiendas;
    
    var tienda = new ArrayList<Tienda>();
    //Ejecutar operaciones en la BD
    //Crear la sentencia SQL
    String sentenciaSql = "SELECT * FROM tienda";
    try {
        //Configurar los parametros de la sentencia SQL
        PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
        
        //Ejecutar la sentencia SQL
        rsTiendas = parametro.executeQuery();           
        
        while(rsTiendas.next()){              
            tienda.add(new Tienda(rsTiendas.getString("nameProducto"), rsTiendas.getInt("id"), rsTiendas.getInt("cantidad"), rsTiendas.getDouble("precio"), rsTiendas.getBoolean("stock")));
        }
        
        conexionDb.close();
        return tienda;
    } catch (SQLException ex) {
        ex.printStackTrace(); // Imprimir la traza de la excepción
        System.out.println("Error al obtener producto: " + ex.getMessage()); // Imprimir el mensaje de la excepción
    } finally {
        // Cerrar recursos si es necesario
        try {
            if (conexionDb != null) {
                conexionDb.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
    return null;
}



    public void EliminarDatosTienda() {
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM tienda WHERE id=?";
        try {
            //Configurar los parametros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getCodigo());

            //Ejecutar la sentencia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
