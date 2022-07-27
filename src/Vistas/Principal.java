/*
 *RF_01
 *RF_02
 *RF_03
 *RF_04
 *RF_05
 *RF_06
 *RF_07
-----------
 *RF_10
 *RF_11
 *RF_12
 *RF_13
 *RF_14

 */
package Vistas;

import SQL.Conexion_BD;
import SQL.Metodos_SQL;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santo
 */
public final class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    
    String clave, nombre, apellidoP, apellidoM, domicilio, correro_electronico, area, sexo, estado_civil, tipo_usuario, tipo_horario;
    int año_nacimiento, sueldo_hora;
    private static Connection conexion;
    private static PreparedStatement senetencia_preparada;
    private static ResultSet resultado;
    Metodos_SQL metos_SQL = new Metodos_SQL();
    ImageIcon verde = new ImageIcon(getClass().getResource("/Iconos/check_verde.png"));
    ImageIcon rojo = new ImageIcon(getClass().getResource("/Iconos/check_rojo.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Iconos/check_warning.png"));
//    Inicio inicio=new Inicio();

    public Principal() {
        initComponents();
        setLocationRelativeTo(null);//para centrar la ventana
        IconoFormulario();
        imagen_png_guardar();
        imagen_png_guardar_horario();
        imagen_png_actualizar();
        imagen_png_eliminar();
        rellenar_combo();
        btnGuardar.setEnabled(false);
//        btnActualizar.setEnabled(false);
        bloquear();
        bloquear_eliminar();
        cargarDatosTabla();
        formatosCampos();
//        setExtendedState(MAXIMIZED_BOTH);
    }

    public void IconoFormulario() {
        URL url = getClass().getResource("/Iconos/Imagen-del-sistema.png");
        ImageIcon icono_formulario = new ImageIcon(url);
        setIconImage(icono_formulario.getImage());

    }

    public void imagen_gif_guardar() {
        ImageIcon check_gif;
        check_gif = new ImageIcon(getClass().getResource("/Iconos/check.gif"));
        Icon gif = new ImageIcon(check_gif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnGuardar.setIcon(gif);
    }

    public void imagen_png_guardar() {
        ImageIcon check_png;
        check_png = new ImageIcon(getClass().getResource("/Iconos/check_png.png"));
        Icon png = new ImageIcon(check_png.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnGuardar.setIcon(png);
    }

    public void imagen_gif_guardar_horario() {
        ImageIcon check_gif;
        check_gif = new ImageIcon(getClass().getResource("/Iconos/check.gif"));
        Icon gif = new ImageIcon(check_gif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnGuardarHorario.setIcon(gif);
    }

    public void imagen_png_guardar_horario() {
        ImageIcon check_png;
        check_png = new ImageIcon(getClass().getResource("/Iconos/check_png.png"));
        Icon png = new ImageIcon(check_png.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnGuardarHorario.setIcon(png);
    }

    public void imagen_gif_actualizar() {
        ImageIcon actualizar_gif;
        actualizar_gif = new ImageIcon(getClass().getResource("/Iconos/actualizar.gif"));
        Icon gif = new ImageIcon(actualizar_gif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnActualizar.setIcon(gif);
    }

    public void imagen_png_actualizar() {
        ImageIcon actualizar_png;
        actualizar_png = new ImageIcon(getClass().getResource("/Iconos/actualizar_png.png"));
        Icon png = new ImageIcon(actualizar_png.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnActualizar.setIcon(png);
    }

    public void imagen_gif_eliminar() {
        ImageIcon eliminar_gif;
        eliminar_gif = new ImageIcon(getClass().getResource("/Iconos/eliminar.gif"));
        Icon gif = new ImageIcon(eliminar_gif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnEliminar.setIcon(gif);
    }

    public void imagen_png_eliminar() {
        ImageIcon eliminar_png;
        eliminar_png = new ImageIcon(getClass().getResource("/Iconos/eliminar_png.png"));
        Icon png = new ImageIcon(eliminar_png.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        btnEliminar.setIcon(png);
    }

    public void contraseña_random() {
        String contraseña = "";
        String contraseña2 = "";
        contraseña = UUID.randomUUID().toString().toUpperCase().substring(0, 5);
        contraseña2 = UUID.randomUUID().toString().toLowerCase().substring(0, 5);
        txt_Contraseña_Random.setText(contraseña + contraseña2);
        txt_Contraseña_Random_Modificar.setText(contraseña + contraseña2);
    }

    public void validacion() {
        String mensaje = metos_SQL.buscar_clave(txtClaveAlta.getText());

        if (mensaje.equals("Existe la clave")) {
            lblInformacionClaveAlta.setText("<html><center>la Clave se encuantra Registrada");
            lblCheckBusquedaClave.setIcon(rojo);
        } else if (txtClaveAlta.getText().isEmpty()) {
            lblCheckBusquedaClave.setIcon(null);
            lblInformacionClaveAlta.setText("");
        } else {
            lblInformacionClaveAlta.setText("<html><center>La clave no se encuntra Registrada");
            lblCheckBusquedaClave.setIcon(verde);
        }
        if (txtClaveAlta.getText().length() < 8) {
            lblClaveCaracteresAlta.setText("<html><center> La Clave debe cumplir con un mimimo de 8 caracteres");
            lblCheckCaracteres.setIcon(rojo);
            if (txtClaveAlta.getText().isEmpty()) {
                lblCheckCaracteres.setIcon(null);
                lblClaveCaracteresAlta.setText("");
            }
        } else {
            lblClaveCaracteresAlta.setText("<html><center> Clave valida");
            lblCheckCaracteres.setIcon(verde);
        }

        if (txtClaveAlta.getText().isEmpty() || txtNombreAlta.getText().isEmpty() || txtApellidoPAlta.getText().isEmpty()
                || txtApellidoMAlta.getText().isEmpty() || txtDomicilioAlta.getText().isEmpty()
                || txt_Correo_ElectronicoAlta.getText().isEmpty() || txtSueldoHoraAlta.getText().isEmpty()
                || Combo_Año_NacimientoAlta.getSelectedItem().equals("Elegir Año de Nacimiento")
                || Combo_AreaAlta.getSelectedItem().equals("Elegir Area")
                || Combo_SexoAlta.getSelectedItem().equals("Elegir el sexo")
                || Combo_Estado_CivilAlta.getSelectedItem().equals("Elegir el estado civil")
                || Combo_Tipo_UsurioAlta.getSelectedItem().equals("Elegir el usuario")
                || Combo_Tipo_HorarioAlta.getSelectedItem().equals("Elegir el horario")
                || lblCheckBusquedaClave.getIcon() == rojo
                || lblCheckCaracteres.getIcon() == rojo) {
            btnGuardar.setEnabled(false);
        } else {
            btnGuardar.setEnabled(true);
        }
    }

    public void rellenar_combo() {
        for (int i = 2019; i >= 1869; i--) {
            Combo_Año_NacimientoAlta.addItem(String.valueOf(i));
            Combo_Año_Nacimiento_Actualizar.addItem(String.valueOf(i));
        }
    }

    public void limpiar() {
        txtClaveAlta.setText("");
        txtNombreAlta.setText("");
        txtApellidoPAlta.setText("");
        txtApellidoMAlta.setText("");
        txtDomicilioAlta.setText("");
        txt_Correo_ElectronicoAlta.setText("");
        Combo_Año_NacimientoAlta.setSelectedIndex(0);
        Combo_AreaAlta.setSelectedIndex(0);
        Combo_SexoAlta.setSelectedIndex(0);
        Combo_Estado_CivilAlta.setSelectedIndex(0);
        Combo_Tipo_UsurioAlta.setSelectedIndex(0);
        Combo_Tipo_HorarioAlta.setSelectedIndex(0);
        txtSueldoHoraAlta.setText("");
        lblCheckBusquedaClave.setIcon(null);
        lblInformacionClaveAlta.setText("");
        lblCheckCaracteres.setIcon(null);
        lblClaveCaracteresAlta.setText("");

//        txtBuscarClaveActualizar.setText("");
        txtNombreActualizar.setText("");
        txtApellidoPActualizar.setText("");
        txtApellidoMActualizar.setText("");
        txtDomicilioActualizar.setText("");
        Combo_Año_Nacimiento_Actualizar.setSelectedIndex(0);
        Combo_Area_Actualizar.setSelectedIndex(0);
        Combo_Tipo_Usurio_Actualizar.setSelectedIndex(0);
        Combo_Tipo_HorarioActualizar.setSelectedIndex(0);
        Combo_Sexo_Actualizar.setSelectedIndex(0);
        Combo_Estado_CivilActualizar.setSelectedIndex(0);
        txtSueldoActualizar.setText("");
        txtCorreo_ElectronicoActualizar.setText("");
//        lblCheckModificar.setIcon(null);
//        lblInformacionActualizar.setText("")
        bloquear();

    }

    public void busqueda_usuario() {
        String clave = txtBuscarClaveActualizar.getText();
        String mensaje = metos_SQL.buscar_clave(clave);

        if (mensaje.equals("Existe la clave")) {
            lblInformacionActualizar.setText("<html><center>Clave encontrada, Se pueden actualizar los datos");
            lblCheckModificar.setIcon(verde);
            desbloquear();

        } else {
            lblInformacionActualizar.setText("<html><center>Sin resultados en la busqueda");
            lblCheckModificar.setIcon(rojo);
            bloquear();
            limpiar();
        }
        if (txtBuscarClaveActualizar.getText().isEmpty()) {
            lblCheckModificar.setIcon(null);
            lblInformacionActualizar.setText("");
        }
    }

    public void modificar_datos() {
        try {
            conexion = Conexion_BD.conectar();
            String sql_busqueda_eliminacion = "SELECT * FROM datos_empleado WHERE clave=?";
            senetencia_preparada = conexion.prepareStatement(sql_busqueda_eliminacion);
            senetencia_preparada.setString(1, clave);
            resultado = senetencia_preparada.executeQuery();
            if (resultado.next()) {

                lbl_Check_Eliminar.setIcon(warning);
                lbl_Informacion_Eliminar.setText("<html><center>!ATENCION: CLAVE REGISTRADA!");
                txtNombreActualizar.setText(resultado.getString("nombre"));
                txtApellidoPActualizar.setText(resultado.getString("apellidoP"));
                txtApellidoMActualizar.setText(resultado.getString("apellidoM"));
                txtDomicilioActualizar.setText(resultado.getString("domicilio"));
                txtCorreo_ElectronicoActualizar.setText(resultado.getString("correo_electronico"));
                lbl_AñoNacimiento_Eliminar.setText(resultado.getString("año_nacimiento"));
                lbl_Area_Eliminar.setText(resultado.getString("area"));
                lbl_sexo_Eliminar.setText(resultado.getString("sexo"));
                lbl_EstadoCivil_Eliminar.setText(resultado.getString("estado_civil"));
                lbl_TipoUsuario_Eliminar.setText(resultado.getString("tipo_usuario"));
                lbl_TipoHorario_Eliminar.setText(resultado.getString("tipo_horario"));
                txtSueldoActualizar.setText(resultado.getString("sueldo_hora"));
                desbloquear_eliminar();

            } else {
                lbl_Check_Eliminar.setIcon(rojo);
                lbl_Informacion_Eliminar.setText("<html><center>Sin resultados en la busqueda");
                bloquear_eliminar();
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
        }
        limpar_label_eliminar();

    }

    public void bloquear() {
        for (Component c : pnl_Actualizar_Datos.getComponents()) {
            c.setEnabled(false);

        }
        for (Component b : pnl_Opciones_Actializar.getComponents()) {
            b.setEnabled(false);
        }
    }

    public void desbloquear() {
        for (Component c : pnl_Actualizar_Datos.getComponents()) {
            c.setEnabled(true);

        }
        for (Component b : pnl_Opciones_Actializar.getComponents()) {
            b.setEnabled(true);
        }
        validacion_datos();
    }
//    txtClaveActualizar.getText().isEmpty() || 

    public void validacion_datos() {
        if (txtNombreActualizar.getText().isEmpty() || txtApellidoPActualizar.getText().isEmpty()
                || txtApellidoMActualizar.getText().isEmpty() || txtDomicilioActualizar.getText().isEmpty()
                || txtCorreo_ElectronicoActualizar.getText().isEmpty() || txtSueldoActualizar.getText().isEmpty()
                || Combo_Año_Nacimiento_Actualizar.getSelectedItem().equals("Elegir Año de Nacimiento")
                || Combo_Area_Actualizar.getSelectedItem().equals("Elegir Area")
                || Combo_Sexo_Actualizar.getSelectedItem().equals("Elegir el sexo")
                || Combo_Estado_CivilActualizar.getSelectedItem().equals("Elegir el estado civil")
                || Combo_Tipo_Usurio_Actualizar.getSelectedItem().equals("Elegir el usuario")
                || Combo_Tipo_HorarioActualizar.getSelectedItem().equals("Elegir el horario")
                || lblCheckBusquedaClave.getIcon() == rojo
                || lblCheckCaracteres.getIcon() == rojo) {
            btnActualizar.setEnabled(false);
        } else {
            btnActualizar.setEnabled(true);
        }
    }

    public void bloquear_eliminar() {
        for (Component i : pnl_Eliminar.getComponents()) {
            i.setEnabled(false);

        }
        for (Component o : pnl_Opciones_Eliminar.getComponents()) {
            o.setEnabled(false);
        }
        imagen_png_eliminar();
    }

    public void desbloquear_eliminar() {
        for (Component z : pnl_Eliminar.getComponents()) {
            z.setEnabled(true);

        }
        for (Component l : pnl_Opciones_Eliminar.getComponents()) {
            l.setEnabled(true);
        }
        imagen_gif_eliminar();
    }

    public void limpiar_eliminar() {
        lbl_Nombre_Eliminar.setText("");
        lbl_ApellidoP_Eliminar.setText("");
        lbl_ApellidoM_Eliminar.setText("");
        lbl_Domicilio_Eliminar.setText("");
        lbl_CorreoElectronico_Eliminar.setText("");
        lbl_AñoNacimiento_Eliminar.setText("");
        lbl_Area_Eliminar.setText("");
        lbl_sexo_Eliminar.setText("");
        lbl_EstadoCivil_Eliminar.setText("");
        lbl_TipoUsuario_Eliminar.setText("");
        lbl_TipoHorario_Eliminar.setText("");
        lbl_SueldoHora_Eliminar.setText("");

    }

    public void buscarEliminarEmpleado(String clave) {
        try {
            conexion = Conexion_BD.conectar();
            String sql_busqueda_eliminacion = "SELECT * FROM datos_empleado WHERE clave=?";
            senetencia_preparada = conexion.prepareStatement(sql_busqueda_eliminacion);
            senetencia_preparada.setString(1, clave);
            resultado = senetencia_preparada.executeQuery();
            if (resultado.next()) {

                lbl_Check_Eliminar.setIcon(warning);
                lbl_Informacion_Eliminar.setText("<html><center>!ATENCION: CLAVE REGISTRADA!");
                lbl_Nombre_Eliminar.setText(resultado.getString("nombre"));
                lbl_ApellidoP_Eliminar.setText(resultado.getString("apellidoP"));
                lbl_ApellidoM_Eliminar.setText(resultado.getString("apellidoM"));
                lbl_Domicilio_Eliminar.setText(resultado.getString("domicilio"));
                lbl_CorreoElectronico_Eliminar.setText(resultado.getString("correo_electronico"));
                lbl_AñoNacimiento_Eliminar.setText(resultado.getString("año_nacimiento"));
                lbl_Area_Eliminar.setText(resultado.getString("area"));
                lbl_sexo_Eliminar.setText(resultado.getString("sexo"));
                lbl_EstadoCivil_Eliminar.setText(resultado.getString("estado_civil"));
                lbl_TipoUsuario_Eliminar.setText(resultado.getString("tipo_usuario"));
                lbl_TipoHorario_Eliminar.setText(resultado.getString("tipo_horario"));
                lbl_SueldoHora_Eliminar.setText(resultado.getString("sueldo_hora"));
                desbloquear_eliminar();

            } else {
                lbl_Check_Eliminar.setIcon(rojo);
                lbl_Informacion_Eliminar.setText("<html><center>Sin resultados en la busqueda");
                bloquear_eliminar();
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
        }
        limpar_label_eliminar();

    }

    public void limpar_label_eliminar() {
        if (jTextField12.getText().isEmpty()) {
            lbl_Check_Eliminar.setIcon(null);
            lbl_Informacion_Eliminar.setText("");
            limpiar_eliminar();
        }

    }

    public void eliminarEmpleadoPregunta() {
        int pregunta;
        pregunta = JOptionPane.showConfirmDialog(null, "Deseas eliminar el empleado?", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (pregunta == 0) {
            metos_SQL.eliminarEmpleado(jTextField12.getText());
            jTextField12.setText("");
            limpar_label_eliminar();
            limpiar_eliminar();
            bloquear_eliminar();
        } else if (pregunta == 1) {
            JOptionPane.showMessageDialog(null, "No se ah eliminado");
        }

    }

    DefaultTableModel table;
    String titulo_columnas[] = {"Clave", "Nombre", "Apellido Paterno", "Apelldio Materno",
        "Domicilio", "Correo Electronico", "Año Nacimiento", "Area ",
        "Sexo", "Estado Civil", "Tipo Usuario", "Tipo Horario", "Sueldo Hora"};
    String filas[] = new String[13];

    public void cargarDatosTabla() {
        table = new DefaultTableModel(null, titulo_columnas);
        try {
            conexion = Conexion_BD.conectar();
            String consulta_tabla = "SELECT * FROM datos_empleado";
            senetencia_preparada = conexion.prepareStatement(consulta_tabla);
            resultado = senetencia_preparada.executeQuery();
            while (resultado.next()) {
                filas[0] = resultado.getString(1);
                filas[1] = resultado.getString(2);
                filas[2] = resultado.getString(3);
                filas[3] = resultado.getString(4);
                filas[4] = resultado.getString(5);
                filas[5] = resultado.getString(6);
                filas[6] = resultado.getString(7);
                filas[7] = resultado.getString(8);
                filas[8] = resultado.getString(9);
                filas[9] = resultado.getString(10);
                filas[10] = resultado.getString(11);
                filas[11] = resultado.getString(12);
                filas[12] = resultado.getString(13);
                table.addRow(filas);

            }
            tbl_datosEmpleado.setModel(table);
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

        }
    }

    public void Busqueda_Filtrada(String clave) {
        table = new DefaultTableModel(null, titulo_columnas);
        try {
            conexion = Conexion_BD.conectar();
            String consulta_tabla = "SELECT * FROM datos_empleado WHERE clave LIKE " + '"' + clave + "%" + '"';
            senetencia_preparada = conexion.prepareStatement(consulta_tabla);
            resultado = senetencia_preparada.executeQuery();
            while (resultado.next()) {
                filas[0] = resultado.getString(1);
                filas[1] = resultado.getString(2);
                filas[2] = resultado.getString(3);
                filas[3] = resultado.getString(4);
                filas[4] = resultado.getString(5);
                filas[5] = resultado.getString(6);
                filas[6] = resultado.getString(7);
                filas[7] = resultado.getString(8);
                filas[8] = resultado.getString(9);
                filas[9] = resultado.getString(10);
                filas[10] = resultado.getString(11);
                filas[11] = resultado.getString(12);
                filas[12] = resultado.getString(13);
                table.addRow(filas);

            }
            tbl_datosEmpleado.setModel(table);
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

        }
    }

    public void label_mensaje_busqueda(String clave) {
        table = new DefaultTableModel(null, titulo_columnas);
        try {
            conexion = Conexion_BD.conectar();
            String consulta_tabla = "SELECT * FROM datos_empleado WHERE clave LIKE " + '"' + clave + "%" + '"';
            senetencia_preparada = conexion.prepareStatement(consulta_tabla);
            resultado = senetencia_preparada.executeQuery();
            if (resultado.next()) {
                lblCheckModificar1.setIcon(verde);
                lblInformacionActualizar1.setText("Resultados posibles");
            } else {
                lblCheckModificar1.setIcon(rojo);
                lblInformacionActualizar1.setText("Sin resultados");
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

        }
    }

    public void limpiar_label_buscar() {
        if (txt_Clve_Buscar.getText().isEmpty()) {
            lblCheckModificar1.setIcon(null);
            lblInformacionActualizar1.setText("");

        }

    }

    public void formatosCampos() {
        tbl_datosEmpleado.getColumnModel().getColumn(0).setPreferredWidth(120);
        tbl_datosEmpleado.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbl_datosEmpleado.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(6).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(8).setPreferredWidth(150);
        tbl_datosEmpleado.getColumnModel().getColumn(9).setPreferredWidth(120);
        tbl_datosEmpleado.getColumnModel().getColumn(10).setPreferredWidth(180);
        tbl_datosEmpleado.getColumnModel().getColumn(11).setPreferredWidth(160);
        tbl_datosEmpleado.getColumnModel().getColumn(12).setPreferredWidth(120);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        button1 = new java.awt.Button();
        jLabel62 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        button3 = new java.awt.Button();
        jLabel82 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        Lista = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_datosEmpleado = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        txt_Clve_Buscar = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        lblCheckModificar1 = new javax.swing.JLabel();
        lblInformacionActualizar1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtClaveAlta = new javax.swing.JTextField();
        txtNombreAlta = new javax.swing.JTextField();
        txtApellidoPAlta = new javax.swing.JTextField();
        txtApellidoMAlta = new javax.swing.JTextField();
        txtDomicilioAlta = new javax.swing.JTextField();
        Combo_Año_NacimientoAlta = new javax.swing.JComboBox<>();
        Combo_AreaAlta = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        lblInformacionClaveAlta = new javax.swing.JLabel();
        lblClaveCaracteresAlta = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Combo_Tipo_UsurioAlta = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txt_Contraseña_Random = new javax.swing.JTextField();
        btn_Contraseña_Random = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        Combo_Tipo_HorarioAlta = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        txtSueldoHoraAlta = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txt_Correo_ElectronicoAlta = new javax.swing.JTextField();
        Combo_SexoAlta = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        Combo_Estado_CivilAlta = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        lblCheckCaracteres = new javax.swing.JLabel();
        lblCheckBusquedaClave = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnl_Actualizar_Datos = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtNombreActualizar = new javax.swing.JTextField();
        txtApellidoPActualizar = new javax.swing.JTextField();
        txtApellidoMActualizar = new javax.swing.JTextField();
        txtDomicilioActualizar = new javax.swing.JTextField();
        Combo_Año_Nacimiento_Actualizar = new javax.swing.JComboBox<>();
        Combo_Area_Actualizar = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        Combo_Tipo_Usurio_Actualizar = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txt_Contraseña_Random_Modificar = new javax.swing.JTextField();
        btn_Contraseña_Random_Modificar = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        Combo_Tipo_HorarioActualizar = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        txtSueldoActualizar = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtCorreo_ElectronicoActualizar = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        Combo_Sexo_Actualizar = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        Combo_Estado_CivilActualizar = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        pnl_Opciones_Actializar = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnLimpiarActualizar = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtBuscarClaveActualizar = new javax.swing.JTextField();
        lblCheckModificar = new javax.swing.JLabel();
        lblInformacionActualizar = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnl_Eliminar = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lbl_Nombre_Eliminar = new javax.swing.JLabel();
        lbl_ApellidoP_Eliminar = new javax.swing.JLabel();
        lbl_ApellidoM_Eliminar = new javax.swing.JLabel();
        lbl_Domicilio_Eliminar = new javax.swing.JLabel();
        lbl_AñoNacimiento_Eliminar = new javax.swing.JLabel();
        lbl_Area_Eliminar = new javax.swing.JLabel();
        lbl_TipoUsuario_Eliminar = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        lbl_SueldoHora_Eliminar = new javax.swing.JLabel();
        lbl_TipoHorario_Eliminar = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        lbl_CorreoElectronico_Eliminar = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        lbl_EstadoCivil_Eliminar = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        lbl_sexo_Eliminar = new javax.swing.JLabel();
        pnl_Opciones_Eliminar = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        lbl_Check_Eliminar = new javax.swing.JLabel();
        lbl_Informacion_Eliminar = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel45 = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jComboBox35 = new javax.swing.JComboBox<>();
        jComboBox36 = new javax.swing.JComboBox<>();
        jComboBox37 = new javax.swing.JComboBox<>();
        jComboBox38 = new javax.swing.JComboBox<>();
        jComboBox39 = new javax.swing.JComboBox<>();
        jComboBox40 = new javax.swing.JComboBox<>();
        jComboBox41 = new javax.swing.JComboBox<>();
        jComboBox42 = new javax.swing.JComboBox<>();
        jComboBox43 = new javax.swing.JComboBox<>();
        jComboBox44 = new javax.swing.JComboBox<>();
        jComboBox45 = new javax.swing.JComboBox<>();
        jComboBox46 = new javax.swing.JComboBox<>();
        jComboBox47 = new javax.swing.JComboBox<>();
        jComboBox48 = new javax.swing.JComboBox<>();
        jPanel47 = new javax.swing.JPanel();
        btnGuardarHorario = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel41 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel84 = new javax.swing.JLabel();
        jMonthChooser4 = new com.toedter.calendar.JMonthChooser();
        jLabel85 = new javax.swing.JLabel();
        jYearChooser4 = new com.toedter.calendar.JYearChooser();
        jLabel94 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        btnGuardarHorario2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel48 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        jMonthChooser6 = new com.toedter.calendar.JMonthChooser();
        jLabel91 = new javax.swing.JLabel();
        jYearChooser6 = new com.toedter.calendar.JYearChooser();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel95 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnGuardarHorario1 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jToggleButton1.setText("jToggleButton1");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Buscar empleado");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 885, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", jPanel17);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"00:00", "false", null, null, null, null, null, null},
                {"01:00", "false", null, null, null, null, null, null},
                {"02:00", "false", null, null, null, null, null, null},
                {"03:00", "false", null, null, null, null, null, null},
                {"04:00", "false", null, null, null, null, null, null},
                {"05:00", "false", null, null, null, null, null, null},
                {"06:00", "false", null, null, null, null, null, null},
                {"07:00", "true", null, null, null, null, null, null},
                {"08:00", "true", null, null, null, null, null, null},
                {"09:00", "true", null, null, null, null, null, null},
                {"10:00", "true", null, null, null, null, null, null},
                {"11:00", "true", null, null, null, null, null, null},
                {"12:00", "true", null, null, null, null, null, null},
                {"13:00", null, null, null, null, null, null, null},
                {"14:00", null, null, null, null, null, null, null},
                {"15:00", null, null, null, null, null, null, null},
                {"16:00", null, null, null, null, null, null, null},
                {"17:00", null, null, null, null, null, null, null},
                {"18:00", null, null, null, null, null, null, null},
                {"19:00", null, null, null, null, null, null, null}
            },
            new String [] {
                "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        button1.setLabel("Generar Reporte");
        button1.setName(""); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel62.setText("jLabel62");

        jLabel65.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel65.setText("ID:");

        jTextField18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        button3.setLabel("Generar Reporte");
        button3.setName(""); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel82.setText("Correo Electronico");

        jLabel86.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel86.setText("Nombre:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EnterTime");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel16.setText("Buscar empleado");

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tbl_datosEmpleado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_datosEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13"
            }
        ));
        jScrollPane2.setViewportView(tbl_datosEmpleado);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_Clve_Buscar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Clve_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Clve_BuscarActionPerformed(evt);
            }
        });
        txt_Clve_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Clve_BuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Clve_BuscarKeyTyped(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel71.setText("Inserte clave del usuario para buscar datos:");

        lblInformacionActualizar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Clve_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblCheckModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInformacionActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addComponent(jSeparator5)))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCheckModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel71)
                                .addComponent(txt_Clve_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInformacionActualizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Lista.addTab("Buscar Trabjador", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("Alta de un nuevo empleado");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inserte Datos del Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Apellido Paterno:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Apellido Materno:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Domicilio:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Año Nacimiento:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Area:");

        txtClaveAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtClaveAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveAltaActionPerformed(evt);
            }
        });
        txtClaveAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveAltaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveAltaKeyTyped(evt);
            }
        });

        txtNombreAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNombreAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreAltaActionPerformed(evt);
            }
        });
        txtNombreAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreAltaKeyReleased(evt);
            }
        });

        txtApellidoPAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtApellidoPAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPAltaActionPerformed(evt);
            }
        });
        txtApellidoPAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoPAltaKeyReleased(evt);
            }
        });

        txtApellidoMAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtApellidoMAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMAltaActionPerformed(evt);
            }
        });
        txtApellidoMAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoMAltaKeyReleased(evt);
            }
        });

        txtDomicilioAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDomicilioAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioAltaActionPerformed(evt);
            }
        });
        txtDomicilioAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDomicilioAltaKeyReleased(evt);
            }
        });

        Combo_Año_NacimientoAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Año_NacimientoAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Año de Nacimiento" }));
        Combo_Año_NacimientoAlta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Año_NacimientoAltaItemStateChanged(evt);
            }
        });

        Combo_AreaAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_AreaAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Area", "Recuersos Humanos", "Contabilidad", "Sistemas", " " }));

        lblInformacionClaveAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblClaveCaracteresAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Tipo usuario");

        Combo_Tipo_UsurioAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Tipo_UsurioAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el usuario", "Trabajador", " " }));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Contraseña:");

        txt_Contraseña_Random.setEditable(false);
        txt_Contraseña_Random.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Contraseña_Random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Contraseña_RandomActionPerformed(evt);
            }
        });

        btn_Contraseña_Random.setBackground(new java.awt.Color(255, 255, 255));
        btn_Contraseña_Random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/dados.png"))); // NOI18N
        btn_Contraseña_Random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Contraseña_RandomActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setText("<html><center>Generar contraseña aleatoria (Opcional)");

        jLabel63.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel63.setText("Tipo Horario");

        Combo_Tipo_HorarioAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Tipo_HorarioAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el horario", "Producion", "Venta", "Sistemas", "Administrativos", " " }));

        jLabel64.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel64.setText("Sueldo por hora");

        txtSueldoHoraAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSueldoHoraAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSueldoHoraAltaActionPerformed(evt);
            }
        });
        txtSueldoHoraAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSueldoHoraAltaKeyReleased(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel72.setText("Correo Electronico");

        txt_Correo_ElectronicoAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Correo_ElectronicoAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Correo_ElectronicoAltaActionPerformed(evt);
            }
        });
        txt_Correo_ElectronicoAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Correo_ElectronicoAltaKeyReleased(evt);
            }
        });

        Combo_SexoAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_SexoAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el sexo", "Maculino", "Femenino" }));
        Combo_SexoAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_SexoAltaActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel55.setText("Sexo");

        Combo_Estado_CivilAlta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Estado_CivilAlta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el estado civil", "Soltero", "Casado", "Viudo" }));

        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel56.setText("Estado civil");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel42.setText("<html><center>Ingresar en forma decimal y entera");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtClaveAlta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCheckCaracteres, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(lblCheckBusquedaClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(lblClaveCaracteresAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblInformacionClaveAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtApellidoPAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel21)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(Combo_Tipo_UsurioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel55))
                                        .addComponent(txtDomicilioAlta)
                                        .addComponent(Combo_Año_NacimientoAlta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Combo_AreaAlta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(Combo_Tipo_HorarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel56))
                                        .addComponent(txtApellidoMAlta))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSueldoHoraAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_Contraseña_Random, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_Contraseña_Random))
                                        .addComponent(txt_Correo_ElectronicoAlta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Combo_SexoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Combo_Estado_CivilAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInformacionClaveAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCheckBusquedaClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblClaveCaracteresAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtClaveAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCheckCaracteres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNombreAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtApellidoPAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidoMAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtDomicilioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Combo_Año_NacimientoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(Combo_AreaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(Combo_Tipo_UsurioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(Combo_Tipo_HorarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(Combo_SexoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(Combo_Estado_CivilAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtSueldoHoraAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txt_Correo_ElectronicoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_Contraseña_Random, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Contraseña_Random, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(191, 295));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setText("Guardar");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel25.setText("Limpiar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel24))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel25)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addGap(7, 7, 7)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        Lista.addTab("Registrar", jPanel2);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel7.setText("Modificar datos del empleado");

        pnl_Actualizar_Datos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inserte Datos del Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Nombre:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Apellido Paterno:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Apellido Materno:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Domicilio:");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setText("Año Nacimiento:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel27.setText("Area:");

        txtNombreActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNombreActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActualizarActionPerformed(evt);
            }
        });
        txtNombreActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreActualizarKeyReleased(evt);
            }
        });

        txtApellidoPActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtApellidoPActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPActualizarActionPerformed(evt);
            }
        });
        txtApellidoPActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoPActualizarKeyReleased(evt);
            }
        });

        txtApellidoMActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtApellidoMActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMActualizarActionPerformed(evt);
            }
        });
        txtApellidoMActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoMActualizarKeyReleased(evt);
            }
        });

        txtDomicilioActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDomicilioActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActualizarActionPerformed(evt);
            }
        });
        txtDomicilioActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDomicilioActualizarKeyReleased(evt);
            }
        });

        Combo_Año_Nacimiento_Actualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Año_Nacimiento_Actualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Año de Nacimiento" }));
        Combo_Año_Nacimiento_Actualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Año_Nacimiento_ActualizarItemStateChanged(evt);
            }
        });

        Combo_Area_Actualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Area_Actualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Area", "Recuersos Humanos", "Contabilidad", "Sistemas", " " }));
        Combo_Area_Actualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Area_ActualizarItemStateChanged(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("Tipo usuario");

        Combo_Tipo_Usurio_Actualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Tipo_Usurio_Actualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el usuario", "Trabajador", " " }));
        Combo_Tipo_Usurio_Actualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Tipo_Usurio_ActualizarItemStateChanged(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel31.setText("Contraseña:");

        txt_Contraseña_Random_Modificar.setEditable(false);
        txt_Contraseña_Random_Modificar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Contraseña_Random_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Contraseña_Random_ModificarActionPerformed(evt);
            }
        });

        btn_Contraseña_Random_Modificar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Contraseña_Random_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/dados.png"))); // NOI18N
        btn_Contraseña_Random_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Contraseña_Random_ModificarActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel32.setText("<html><center>Generar contraseña aleatoria (Opcional)");

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel67.setText("Tipo Horario");

        Combo_Tipo_HorarioActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Tipo_HorarioActualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el horario", "Producion", "Venta", "Sistemas", "Administrativos" }));
        Combo_Tipo_HorarioActualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Tipo_HorarioActualizarItemStateChanged(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel68.setText("Sueldo por hora");

        txtSueldoActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSueldoActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSueldoActualizarActionPerformed(evt);
            }
        });
        txtSueldoActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSueldoActualizarKeyReleased(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel73.setText("Correo Electronico");

        txtCorreo_ElectronicoActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCorreo_ElectronicoActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreo_ElectronicoActualizarActionPerformed(evt);
            }
        });
        txtCorreo_ElectronicoActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreo_ElectronicoActualizarKeyReleased(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel57.setText("Sexo");

        Combo_Sexo_Actualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Sexo_Actualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el sexo", "Maculino", "Femenino" }));
        Combo_Sexo_Actualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Sexo_ActualizarItemStateChanged(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel74.setText("Estado civil");

        Combo_Estado_CivilActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Combo_Estado_CivilActualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir el estado civil", "Soltero", "Casado", "Viudo" }));
        Combo_Estado_CivilActualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_Estado_CivilActualizarItemStateChanged(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel41.setText("<html><center>Ingresar en forma decimal y entera");

        javax.swing.GroupLayout pnl_Actualizar_DatosLayout = new javax.swing.GroupLayout(pnl_Actualizar_Datos);
        pnl_Actualizar_Datos.setLayout(pnl_Actualizar_DatosLayout);
        pnl_Actualizar_DatosLayout.setHorizontalGroup(
            pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Combo_Tipo_HorarioActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDomicilioActualizar)
                                .addComponent(txtApellidoMActualizar)
                                .addComponent(Combo_Año_Nacimiento_Actualizar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Combo_Area_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                                .addComponent(Combo_Tipo_Usurio_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Combo_Estado_CivilActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                                        .addComponent(jLabel57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Combo_Sexo_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(23, 23, 23)
                        .addComponent(txtNombreActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(txtApellidoPActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSueldoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Contraseña_Random_Modificar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_Actualizar_DatosLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Contraseña_Random_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo_ElectronicoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        pnl_Actualizar_DatosLayout.setVerticalGroup(
            pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtApellidoPActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtApellidoMActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtDomicilioActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Combo_Año_Nacimiento_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(Combo_Area_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(Combo_Tipo_Usurio_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(Combo_Tipo_HorarioActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnl_Actualizar_DatosLayout.createSequentialGroup()
                        .addComponent(Combo_Sexo_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(Combo_Estado_CivilActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(txtSueldoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtCorreo_ElectronicoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txt_Contraseña_Random_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_Actualizar_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Contraseña_Random_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        pnl_Opciones_Actializar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnLimpiarActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiarActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        btnLimpiarActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActualizarActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel33.setText("Actualizar datos");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel34.setText("Limpiar");

        javax.swing.GroupLayout pnl_Opciones_ActializarLayout = new javax.swing.GroupLayout(pnl_Opciones_Actializar);
        pnl_Opciones_Actializar.setLayout(pnl_Opciones_ActializarLayout);
        pnl_Opciones_ActializarLayout.setHorizontalGroup(
            pnl_Opciones_ActializarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_Opciones_ActializarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(49, 49, 49))
            .addGroup(pnl_Opciones_ActializarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnl_Opciones_ActializarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_Opciones_ActializarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel33))
                    .addComponent(btnLimpiarActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnl_Opciones_ActializarLayout.setVerticalGroup(
            pnl_Opciones_ActializarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_Opciones_ActializarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiarActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addContainerGap(318, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel28.setText("Inserte clave del usuario para actualizar datos:");

        txtBuscarClaveActualizar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscarClaveActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClaveActualizarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarClaveActualizarKeyTyped(evt);
            }
        });

        lblInformacionActualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(pnl_Actualizar_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnl_Opciones_Actializar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarClaveActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCheckModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblInformacionActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtBuscarClaveActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInformacionActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCheckModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(pnl_Opciones_Actializar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(pnl_Actualizar_Datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );

        Lista.addTab("Modificar", jPanel3);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel8.setText("Eliminar empleado");

        pnl_Eliminar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel35.setText("Nombre:");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel36.setText("Apellido Paterno:");

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel37.setText("Apellido Materno:");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel38.setText("Domicilio:");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel39.setText("Año Nacimiento:");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel40.setText("Area:");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel43.setText("Tipo usuario");

        lbl_Nombre_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_Nombre_Eliminar.setText("*");

        lbl_ApellidoP_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_ApellidoP_Eliminar.setText("*");

        lbl_ApellidoM_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_ApellidoM_Eliminar.setText("*");

        lbl_Domicilio_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_Domicilio_Eliminar.setText("*");

        lbl_AñoNacimiento_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_AñoNacimiento_Eliminar.setText("*");

        lbl_Area_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_Area_Eliminar.setText("*");

        lbl_TipoUsuario_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_TipoUsuario_Eliminar.setText("*");

        jLabel69.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel69.setText("Tipo Horario");

        jLabel70.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel70.setText("Sueldo por hora");

        lbl_SueldoHora_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_SueldoHora_Eliminar.setText("*");

        lbl_TipoHorario_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_TipoHorario_Eliminar.setText("*");

        jLabel79.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel79.setText("Correo Electronico");

        lbl_CorreoElectronico_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_CorreoElectronico_Eliminar.setText("*");

        jLabel87.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel87.setText("Estado civil");

        lbl_EstadoCivil_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_EstadoCivil_Eliminar.setText("*");

        jLabel92.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel92.setText("Sexo");

        lbl_sexo_Eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_sexo_Eliminar.setText("*");

        javax.swing.GroupLayout pnl_EliminarLayout = new javax.swing.GroupLayout(pnl_Eliminar);
        pnl_Eliminar.setLayout(pnl_EliminarLayout);
        pnl_EliminarLayout.setHorizontalGroup(
            pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_EliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_EliminarLayout.createSequentialGroup()
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_sexo_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_EliminarLayout.createSequentialGroup()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_EstadoCivil_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_EliminarLayout.createSequentialGroup()
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_CorreoElectronico_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_EliminarLayout.createSequentialGroup()
                        .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(25, 25, 25)
                        .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_Nombre_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_ApellidoP_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_ApellidoM_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Domicilio_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_AñoNacimiento_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Area_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_TipoUsuario_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_TipoHorario_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_SueldoHora_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))))
                .addGap(206, 206, 206))
        );
        pnl_EliminarLayout.setVerticalGroup(
            pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_EliminarLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lbl_Nombre_Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lbl_ApellidoP_Eliminar))
                .addGap(13, 13, 13)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lbl_ApellidoM_Eliminar))
                .addGap(13, 13, 13)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(lbl_Domicilio_Eliminar))
                .addGap(9, 9, 9)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lbl_AñoNacimiento_Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lbl_Area_Eliminar))
                .addGap(16, 16, 16)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(lbl_TipoUsuario_Eliminar))
                .addGap(18, 18, 18)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_EliminarLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel70))
                    .addGroup(pnl_EliminarLayout.createSequentialGroup()
                        .addComponent(lbl_TipoHorario_Eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_SueldoHora_Eliminar)))
                .addGap(18, 18, 18)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(lbl_CorreoElectronico_Eliminar))
                .addGap(18, 18, 18)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(lbl_EstadoCivil_Eliminar))
                .addGap(13, 13, 13)
                .addGroup(pnl_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(lbl_sexo_Eliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_Opciones_Eliminar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel46.setText("Eliminar");

        javax.swing.GroupLayout pnl_Opciones_EliminarLayout = new javax.swing.GroupLayout(pnl_Opciones_Eliminar);
        pnl_Opciones_Eliminar.setLayout(pnl_Opciones_EliminarLayout);
        pnl_Opciones_EliminarLayout.setHorizontalGroup(
            pnl_Opciones_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_Opciones_EliminarLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_Opciones_EliminarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(69, 69, 69))
        );
        pnl_Opciones_EliminarLayout.setVerticalGroup(
            pnl_Opciones_EliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_Opciones_EliminarLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel46)
                .addGap(434, 434, 434))
        );

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel52.setText("Inserte clave del usuario para eliminar datos:");

        jTextField12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });

        lbl_Informacion_Eliminar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator6)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(pnl_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnl_Opciones_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_Check_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_Informacion_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_Check_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_Informacion_Eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(pnl_Opciones_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(pnl_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Lista.addTab("Eliminar", jPanel6);

        jLabel115.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel115.setText("Creacion de horarios");

        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Basicos"));

        jLabel116.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel116.setText("ID Horario:");

        jTextField42.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel117.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel117.setText("Tiempo Maximo de Retraso:");

        jTextField43.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel118.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel118.setText("Min");

        jLabel119.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel119.setText("Nombre de horario:");

        jTextField44.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44jTextField23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel116)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel117)
                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel118))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder("Horario"));

        jLabel120.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel120.setText("Dia");

        jLabel121.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel121.setText("Entrada");

        jLabel122.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel122.setText("Salida");

        jCheckBox7.setText("Lunes");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jCheckBox8.setText("Martes");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jCheckBox17.setText("Miercoles");
        jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox17ActionPerformed(evt);
            }
        });

        jCheckBox18.setText("Jueves");
        jCheckBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox18ActionPerformed(evt);
            }
        });

        jCheckBox19.setText("Viernes");
        jCheckBox19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox19ActionPerformed(evt);
            }
        });

        jCheckBox20.setText("Sabado");
        jCheckBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox20ActionPerformed(evt);
            }
        });

        jCheckBox21.setText("Domingo");
        jCheckBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox21ActionPerformed(evt);
            }
        });

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox37.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox38.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox39.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox40.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox41.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox42.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox43.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox44.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox45.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox46.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox47.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox48.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jCheckBox18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCheckBox19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jCheckBox8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jLabel120)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel120)
                    .addComponent(jLabel121)
                    .addComponent(jLabel122))
                .addGap(39, 39, 39)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox8)
                    .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox17)
                    .addComponent(jComboBox37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox18)
                    .addComponent(jComboBox38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox19)
                    .addComponent(jComboBox39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox20)
                    .addComponent(jComboBox40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox21)
                    .addComponent(jComboBox41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnGuardarHorario.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarHorarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarHorarioMouseExited(evt);
            }
        });
        btnGuardarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHorarioActionPerformed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel78.setText("Guardar");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addComponent(btnGuardarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(76, 76, 76))))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnGuardarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel78)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel115))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel115)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        Lista.addTab("Horario", jPanel43);

        jLabel75.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel75.setText("Reporte Assistencia");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"00:00", "false", null, null, null, null, null, null},
                {"01:00", "false", null, null, null, null, null, null},
                {"02:00", "false", null, null, null, null, null, null},
                {"03:00", "false", null, null, null, null, null, null},
                {"04:00", "false", null, null, null, null, null, null},
                {"05:00", "false", null, null, null, null, null, null},
                {"06:00", "false", null, null, null, null, null, null},
                {"07:00", "true", null, null, null, null, null, null},
                {"08:00", "true", null, null, null, null, null, null},
                {"09:00", "true", null, null, null, null, null, null},
                {"10:00", "true", null, null, null, null, null, null},
                {"11:00", "true", null, null, null, null, null, null},
                {"12:00", "true", null, null, null, null, null, null},
                {"13:00", null, null, null, null, null, null, null},
                {"14:00", null, null, null, null, null, null, null},
                {"15:00", null, null, null, null, null, null, null},
                {"16:00", null, null, null, null, null, null, null},
                {"17:00", null, null, null, null, null, null, null},
                {"18:00", null, null, null, null, null, null, null},
                {"19:00", null, null, null, null, null, null, null}
            },
            new String [] {
                "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel76.setText("Clave Empleado");

        jTextField20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField20.setText("16240226");

        jLabel83.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel83.setText("Quincena");

        jComboBox6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel84.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel84.setText("Mes");

        jMonthChooser4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel85.setText("Año");

        jLabel94.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel94.setText("Semana");

        jComboBox7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(378, Short.MAX_VALUE)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox7, 0, 96, Short.MAX_VALUE)
                .addGap(79, 79, 79))
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel76)
                        .addComponent(jLabel83)
                        .addComponent(jLabel84)
                        .addComponent(jLabel85))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jYearChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField20)
                            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMonthChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                    .addContainerGap(337, Short.MAX_VALUE)))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel41Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel76))
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel83)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(19, 19, 19)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMonthChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel84))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jYearChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel85))
                    .addContainerGap(28, Short.MAX_VALUE)))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnGuardarHorario2.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarHorario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/exportar.png"))); // NOI18N
        btnGuardarHorario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarHorario2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarHorario2MouseExited(evt);
            }
        });
        btnGuardarHorario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHorario2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Exportar");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnGuardarHorario2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btnGuardarHorario2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator9)
                            .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Assitencia", jPanel20);

        jLabel66.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel66.setText("Reporte Nomina");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );

        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel80.setText("Clave Empleado");

        jTextField22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField22.setText("16240226");

        jLabel89.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel89.setText("Quincena");

        jComboBox8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel90.setText("Mes");

        jMonthChooser6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel91.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel91.setText("Año");

        jComboBox9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel95.setText("Semana");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox9, 0, 96, Short.MAX_VALUE)
                .addGap(124, 124, 124))
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel48Layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel80)
                        .addComponent(jLabel89)
                        .addComponent(jLabel90)
                        .addComponent(jLabel91))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jYearChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField22)
                            .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMonthChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                    .addContainerGap(337, Short.MAX_VALUE)))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel48Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel48Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel80))
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel89)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(19, 19, 19)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMonthChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel90))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jYearChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel91))
                    .addContainerGap(28, Short.MAX_VALUE)))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnGuardarHorario1.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarHorario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/exportar.png"))); // NOI18N
        btnGuardarHorario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarHorario1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarHorario1MouseExited(evt);
            }
        });
        btnGuardarHorario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHorario1ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel58.setText("Exportar");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnGuardarHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel58)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(btnGuardarHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel58)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Nomina", jPanel23);

        Lista.addTab("Reportes", jTabbedPane2);

        getContentPane().add(Lista);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("Acerca del sitema");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cerrar sesion");
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button3ActionPerformed

    private void jCheckBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox21ActionPerformed

    private void jCheckBox20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox20ActionPerformed

    private void jCheckBox19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox19ActionPerformed

    private void jCheckBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox18ActionPerformed

    private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox17ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jTextField44jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44jTextField23ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarEmpleadoPregunta();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        imagen_png_eliminar();

    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        if (btnEliminar.isEnabled()) {
            imagen_gif_eliminar();
        } else {

        }

    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnLimpiarActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActualizarActionPerformed
        limpiar();
        lblCheckModificar.setIcon(null);
        lblInformacionActualizar.setText("");
        txtBuscarClaveActualizar.setText("");
    }//GEN-LAST:event_btnLimpiarActualizarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        modificar_datos();


    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        imagen_png_actualizar();
    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered
        if (btnActualizar.isEnabled()) {
            imagen_gif_actualizar();
        } else {
            imagen_png_actualizar();
        }
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        clave = txtClaveAlta.getText();
        nombre = txtNombreAlta.getText();
        apellidoP = txtApellidoPAlta.getText();
        apellidoM = txtApellidoMAlta.getText();
        domicilio = txtDomicilioAlta.getText();
        correro_electronico = txt_Correo_ElectronicoAlta.getText();
        año_nacimiento = Integer.parseInt((String) Combo_Año_NacimientoAlta.getSelectedItem());
        area = Combo_AreaAlta.getSelectedItem().toString();
        sexo = Combo_SexoAlta.getSelectedItem().toString();
        estado_civil = Combo_Estado_CivilAlta.getSelectedItem().toString();
        tipo_usuario = Combo_Tipo_UsurioAlta.getSelectedItem().toString();
        tipo_horario = Combo_Tipo_HorarioAlta.getSelectedItem().toString();
        sueldo_hora = Integer.parseInt((String) txtSueldoHoraAlta.getText());

        metos_SQL.guardar_datos_empleado(clave, nombre, apellidoP, apellidoM, domicilio, correro_electronico, año_nacimiento, area, sexo, estado_civil, tipo_usuario, tipo_horario, sueldo_hora);
        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        imagen_png_guardar();
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        if (btnGuardar.isEnabled()) {
            imagen_gif_guardar();
        } else {
            imagen_png_guardar();
        }
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void txtSueldoHoraAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueldoHoraAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSueldoHoraAltaActionPerformed

    private void btn_Contraseña_RandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Contraseña_RandomActionPerformed
        contraseña_random();
    }//GEN-LAST:event_btn_Contraseña_RandomActionPerformed

    private void txt_Contraseña_RandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Contraseña_RandomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Contraseña_RandomActionPerformed

    private void txtDomicilioAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioAltaActionPerformed

    private void txtApellidoMAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMAltaActionPerformed

    private void txtApellidoPAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPAltaActionPerformed

    private void txtNombreAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreAltaActionPerformed

    private void btnGuardarHorarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarHorarioMouseEntered
        imagen_gif_guardar_horario();
    }//GEN-LAST:event_btnGuardarHorarioMouseEntered

    private void btnGuardarHorarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarHorarioMouseExited
        imagen_png_guardar_horario();
    }//GEN-LAST:event_btnGuardarHorarioMouseExited

    private void btnGuardarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorarioActionPerformed

    private void btnGuardarHorario1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarHorario1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorario1MouseEntered

    private void btnGuardarHorario1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarHorario1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorario1MouseExited

    private void btnGuardarHorario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHorario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorario1ActionPerformed

    private void btnGuardarHorario2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarHorario2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorario2MouseEntered

    private void btnGuardarHorario2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarHorario2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorario2MouseExited

    private void btnGuardarHorario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHorario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarHorario2ActionPerformed

    private void txt_Clve_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Clve_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Clve_BuscarActionPerformed

    private void txt_Correo_ElectronicoAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Correo_ElectronicoAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Correo_ElectronicoAltaActionPerformed

    private void Combo_SexoAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_SexoAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_SexoAltaActionPerformed

    private void txtBuscarClaveActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClaveActualizarKeyReleased
        busqueda_usuario();
    }//GEN-LAST:event_txtBuscarClaveActualizarKeyReleased

    private void txtClaveAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txtClaveAltaKeyReleased

    private void txtClaveAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveAltaActionPerformed

    private void txtClaveAltaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveAltaKeyTyped
        if (txtClaveAlta.getText().length() >= 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtClaveAltaKeyTyped

    private void txtNombreAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txtNombreAltaKeyReleased

    private void txtApellidoPAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txtApellidoPAltaKeyReleased

    private void txtApellidoMAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txtApellidoMAltaKeyReleased

    private void txtDomicilioAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txtDomicilioAltaKeyReleased

    private void txtSueldoHoraAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoHoraAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txtSueldoHoraAltaKeyReleased

    private void txt_Correo_ElectronicoAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Correo_ElectronicoAltaKeyReleased
        validacion();
    }//GEN-LAST:event_txt_Correo_ElectronicoAltaKeyReleased

    private void Combo_Año_NacimientoAltaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Año_NacimientoAltaItemStateChanged
        validacion();
    }//GEN-LAST:event_Combo_Año_NacimientoAltaItemStateChanged

    private void txtCorreo_ElectronicoActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreo_ElectronicoActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreo_ElectronicoActualizarActionPerformed

    private void txtSueldoActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueldoActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSueldoActualizarActionPerformed

    private void btn_Contraseña_Random_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Contraseña_Random_ModificarActionPerformed
        contraseña_random();
    }//GEN-LAST:event_btn_Contraseña_Random_ModificarActionPerformed

    private void txt_Contraseña_Random_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Contraseña_Random_ModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Contraseña_Random_ModificarActionPerformed

    private void txtDomicilioActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioActualizarActionPerformed

    private void txtApellidoMActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMActualizarActionPerformed

    private void txtApellidoPActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPActualizarActionPerformed

    private void txtNombreActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActualizarActionPerformed

    private void txtBuscarClaveActualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClaveActualizarKeyTyped
        if (txtBuscarClaveActualizar.getText().length() >= 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtBuscarClaveActualizarKeyTyped

    private void txtNombreActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreActualizarKeyReleased
        validacion_datos();
    }//GEN-LAST:event_txtNombreActualizarKeyReleased

    private void txtApellidoPActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPActualizarKeyReleased
        validacion_datos();
    }//GEN-LAST:event_txtApellidoPActualizarKeyReleased

    private void txtApellidoMActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMActualizarKeyReleased
        validacion_datos();
    }//GEN-LAST:event_txtApellidoMActualizarKeyReleased

    private void txtDomicilioActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioActualizarKeyReleased
        validacion_datos();
    }//GEN-LAST:event_txtDomicilioActualizarKeyReleased

    private void Combo_Año_Nacimiento_ActualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Año_Nacimiento_ActualizarItemStateChanged
        validacion_datos();
    }//GEN-LAST:event_Combo_Año_Nacimiento_ActualizarItemStateChanged

    private void Combo_Area_ActualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Area_ActualizarItemStateChanged
        validacion_datos();
    }//GEN-LAST:event_Combo_Area_ActualizarItemStateChanged

    private void Combo_Tipo_Usurio_ActualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Tipo_Usurio_ActualizarItemStateChanged
        validacion_datos();
    }//GEN-LAST:event_Combo_Tipo_Usurio_ActualizarItemStateChanged

    private void Combo_Tipo_HorarioActualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Tipo_HorarioActualizarItemStateChanged
        validacion_datos();
    }//GEN-LAST:event_Combo_Tipo_HorarioActualizarItemStateChanged

    private void Combo_Sexo_ActualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Sexo_ActualizarItemStateChanged
        validacion_datos();
    }//GEN-LAST:event_Combo_Sexo_ActualizarItemStateChanged

    private void Combo_Estado_CivilActualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_Estado_CivilActualizarItemStateChanged
        validacion_datos();
    }//GEN-LAST:event_Combo_Estado_CivilActualizarItemStateChanged

    private void txtSueldoActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoActualizarKeyReleased
        validacion_datos();
    }//GEN-LAST:event_txtSueldoActualizarKeyReleased

    private void txtCorreo_ElectronicoActualizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreo_ElectronicoActualizarKeyReleased
        validacion_datos();
    }//GEN-LAST:event_txtCorreo_ElectronicoActualizarKeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        buscarEliminarEmpleado(jTextField12.getText());
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        if (jTextField12.getText().length() >= 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jTextField12KeyTyped

    private void txt_Clve_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Clve_BuscarKeyTyped
        if (txt_Clve_Buscar.getText().length() >= 8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txt_Clve_BuscarKeyTyped

    private void txt_Clve_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Clve_BuscarKeyReleased
        Busqueda_Filtrada(txt_Clve_Buscar.getText());
        label_mensaje_busqueda(txt_Clve_Buscar.getText());
        limpiar_label_buscar();
    }//GEN-LAST:event_txt_Clve_BuscarKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        inicio.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_AreaAlta;
    private javax.swing.JComboBox<String> Combo_Area_Actualizar;
    private javax.swing.JComboBox<String> Combo_Año_NacimientoAlta;
    private javax.swing.JComboBox<String> Combo_Año_Nacimiento_Actualizar;
    private javax.swing.JComboBox<String> Combo_Estado_CivilActualizar;
    private javax.swing.JComboBox<String> Combo_Estado_CivilAlta;
    private javax.swing.JComboBox<String> Combo_SexoAlta;
    private javax.swing.JComboBox<String> Combo_Sexo_Actualizar;
    private javax.swing.JComboBox<String> Combo_Tipo_HorarioActualizar;
    private javax.swing.JComboBox<String> Combo_Tipo_HorarioAlta;
    private javax.swing.JComboBox<String> Combo_Tipo_UsurioAlta;
    private javax.swing.JComboBox<String> Combo_Tipo_Usurio_Actualizar;
    private javax.swing.JTabbedPane Lista;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarHorario;
    private javax.swing.JButton btnGuardarHorario1;
    private javax.swing.JButton btnGuardarHorario2;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarActualizar;
    private javax.swing.JButton btn_Contraseña_Random;
    private javax.swing.JButton btn_Contraseña_Random_Modificar;
    private java.awt.Button button1;
    private java.awt.Button button3;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox<String> jComboBox35;
    private javax.swing.JComboBox<String> jComboBox36;
    private javax.swing.JComboBox<String> jComboBox37;
    private javax.swing.JComboBox<String> jComboBox38;
    private javax.swing.JComboBox<String> jComboBox39;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox40;
    private javax.swing.JComboBox<String> jComboBox41;
    private javax.swing.JComboBox<String> jComboBox42;
    private javax.swing.JComboBox<String> jComboBox43;
    private javax.swing.JComboBox<String> jComboBox44;
    private javax.swing.JComboBox<String> jComboBox45;
    private javax.swing.JComboBox<String> jComboBox46;
    private javax.swing.JComboBox<String> jComboBox47;
    private javax.swing.JComboBox<String> jComboBox48;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private com.toedter.calendar.JMonthChooser jMonthChooser4;
    private com.toedter.calendar.JMonthChooser jMonthChooser6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JToggleButton jToggleButton1;
    private com.toedter.calendar.JYearChooser jYearChooser4;
    private com.toedter.calendar.JYearChooser jYearChooser6;
    private javax.swing.JLabel lblCheckBusquedaClave;
    private javax.swing.JLabel lblCheckCaracteres;
    private javax.swing.JLabel lblCheckModificar;
    private javax.swing.JLabel lblCheckModificar1;
    private javax.swing.JLabel lblClaveCaracteresAlta;
    private javax.swing.JLabel lblInformacionActualizar;
    private javax.swing.JLabel lblInformacionActualizar1;
    private javax.swing.JLabel lblInformacionClaveAlta;
    private javax.swing.JLabel lbl_ApellidoM_Eliminar;
    private javax.swing.JLabel lbl_ApellidoP_Eliminar;
    private javax.swing.JLabel lbl_Area_Eliminar;
    private javax.swing.JLabel lbl_AñoNacimiento_Eliminar;
    private javax.swing.JLabel lbl_Check_Eliminar;
    private javax.swing.JLabel lbl_CorreoElectronico_Eliminar;
    private javax.swing.JLabel lbl_Domicilio_Eliminar;
    private javax.swing.JLabel lbl_EstadoCivil_Eliminar;
    private javax.swing.JLabel lbl_Informacion_Eliminar;
    private javax.swing.JLabel lbl_Nombre_Eliminar;
    private javax.swing.JLabel lbl_SueldoHora_Eliminar;
    private javax.swing.JLabel lbl_TipoHorario_Eliminar;
    private javax.swing.JLabel lbl_TipoUsuario_Eliminar;
    private javax.swing.JLabel lbl_sexo_Eliminar;
    private javax.swing.JPanel pnl_Actualizar_Datos;
    private javax.swing.JPanel pnl_Eliminar;
    private javax.swing.JPanel pnl_Opciones_Actializar;
    private javax.swing.JPanel pnl_Opciones_Eliminar;
    public javax.swing.JTable tbl_datosEmpleado;
    private javax.swing.JTextField txtApellidoMActualizar;
    private javax.swing.JTextField txtApellidoMAlta;
    private javax.swing.JTextField txtApellidoPActualizar;
    private javax.swing.JTextField txtApellidoPAlta;
    private javax.swing.JTextField txtBuscarClaveActualizar;
    private javax.swing.JTextField txtClaveAlta;
    private javax.swing.JTextField txtCorreo_ElectronicoActualizar;
    private javax.swing.JTextField txtDomicilioActualizar;
    private javax.swing.JTextField txtDomicilioAlta;
    private javax.swing.JTextField txtNombreActualizar;
    private javax.swing.JTextField txtNombreAlta;
    private javax.swing.JTextField txtSueldoActualizar;
    private javax.swing.JTextField txtSueldoHoraAlta;
    private javax.swing.JTextField txt_Clve_Buscar;
    private javax.swing.JTextField txt_Contraseña_Random;
    private javax.swing.JTextField txt_Contraseña_Random_Modificar;
    private javax.swing.JTextField txt_Correo_ElectronicoAlta;
    // End of variables declaration//GEN-END:variables
}