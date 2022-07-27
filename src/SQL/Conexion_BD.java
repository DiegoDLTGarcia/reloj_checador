/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santo
 */
public class Conexion_BD {

    private static String URL = "jdbc:mysql://localhost:3306/bd_entertime";
    private static String Usuario = "root";
    private static String contraseña = "root";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(URL, Usuario, contraseña);
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: "+e);
        }
        return conexion;

    }

}
