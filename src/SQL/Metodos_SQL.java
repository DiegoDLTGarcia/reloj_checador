/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import Vistas.Principal;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author santo
 */
public class Metodos_SQL {

    private static Connection conexion;
    private static PreparedStatement senetencia_preparada;
    private static ResultSet resultado;

    public void guardar_datos_empleado(String clave, String nombre, String apellidoP, String apellidoM, String domicilio,
            String correro_electronico, int año_nacimiento, String area, String sexo, String estado_civil,
            String tipo_usuario, String tipo_horario, int sueldo_hora) {
        try {
            conexion = Conexion_BD.conectar();
            String consulta = "INSERT INTO datos_empleado (clave,nombre,apellidoP,apellidoM,domicilio,correo_electronico,año_nacimiento,area,sexo,estado_civil,tipo_usuario,tipo_horario,sueldo_hora) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            senetencia_preparada = conexion.prepareStatement(consulta);
            senetencia_preparada.setString(1, clave);
            senetencia_preparada.setString(2, nombre);
            senetencia_preparada.setString(3, apellidoP);
            senetencia_preparada.setString(4, apellidoM);
            senetencia_preparada.setString(5, domicilio);
            senetencia_preparada.setString(6, correro_electronico);
            senetencia_preparada.setInt(7, año_nacimiento);
            senetencia_preparada.setString(8, area);
            senetencia_preparada.setString(9, sexo);
            senetencia_preparada.setString(10, estado_civil);
            senetencia_preparada.setString(11, tipo_usuario);
            senetencia_preparada.setString(12, tipo_horario);
            senetencia_preparada.setInt(13, sueldo_hora);
            int i = senetencia_preparada.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Datos Guardados");
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar los datos");
            }
            conexion.close();

        } catch (HeadlessException | SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

    }

    public static String buscar_clave(String clave) {
        String mensaje = null;
        try {
            conexion = Conexion_BD.conectar();
            String consulta = "SELECT clave FROM datos_empleado WHERE clave= ?";
            senetencia_preparada = conexion.prepareStatement(consulta);
            senetencia_preparada.setString(1, clave);
            resultado = senetencia_preparada.executeQuery();
            if (resultado.next()) {
                mensaje = "Existe la clave";
            } else {
                mensaje = "No Existe la clave";
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }

            return mensaje;

        }
    }

    public void modificar_datos(String nombre, String apellidoP, String apellidoM, String domicilio,
            String correro_electronico, int año_nacimiento, String area, String sexo, String estado_civil,
            String tipo_usuario, String tipo_horario, int sueldo_hora, String clave) {
        try {
            conexion = Conexion_BD.conectar();
            String consulta = "UPDATE datos_empleado SET nombre = ?, apellidoP=?,apellidoM=?,domicilio=?,correo_electronico=?,año_nacimiento=?,area=?,sexo=?,estado_civil=?,tipo_usuario=?,tipo_horario=?,sueldo_hora=? WHERE clave=?";
//            senetencia_preparada = conexion.prepareStatement(consulta);
            senetencia_preparada = conexion.prepareStatement(consulta);
            senetencia_preparada.setString(1, nombre);
            senetencia_preparada.setString(2, apellidoP);
            senetencia_preparada.setString(3, apellidoM);
            senetencia_preparada.setString(4, domicilio);
            senetencia_preparada.setString(5, correro_electronico);
            senetencia_preparada.setInt(6, año_nacimiento);
            senetencia_preparada.setString(7, area);
            senetencia_preparada.setString(8, sexo);
            senetencia_preparada.setString(9, estado_civil);
            senetencia_preparada.setString(10, tipo_usuario);
            senetencia_preparada.setString(11, tipo_horario);
            senetencia_preparada.setInt(12, sueldo_hora);
            senetencia_preparada.setString(13, clave);
            int i = senetencia_preparada.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Datos modificados");
            } else {
                JOptionPane.showMessageDialog(null, "No se modificaron losModificados");
            }
            conexion.close();
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

    }

    public void eliminarEmpleado(String clave) {
        try {
            conexion = Conexion_BD.conectar();
            String consulta_eliminacion = "DELETE FROM datos_empleado WHERE clave=?";
            senetencia_preparada = conexion.prepareStatement(consulta_eliminacion);
            senetencia_preparada.setString(1, clave);
            int resultado_fila_afectada = senetencia_preparada.executeUpdate();
            if (resultado_fila_afectada > 0) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el empleado");
            }
            conexion.close();
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static String buscarPass(String nombre, String contraseña) {
        String mensaje = null;
        try {
            conexion = Conexion_BD.conectar();
            String SSQL = "SELECT * FROM datos_administrador WHERE nombre=? AND contraseña=?";
            senetencia_preparada = conexion.prepareStatement(SSQL);
            senetencia_preparada.setString(1, nombre);
            senetencia_preparada.setString(2, contraseña);
            resultado = senetencia_preparada.executeQuery();
            if (resultado.next()) {
                mensaje = "Correcto";
            } else {
                mensaje = "Incorrecto";
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);

        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error: " + ex);
            }
            return mensaje;
        }
    }
}
