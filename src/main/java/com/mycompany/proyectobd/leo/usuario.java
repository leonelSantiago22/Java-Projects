/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobd.leo;

import com.mycompany.proyectobd.Conexion;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Leonel
 */
public class usuario {

    private String nombre_usuario;
    private String contrasena_usuario;
    private int tipo_usuario;
    private String correo_usuario;

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena_usuario() {
        return contrasena_usuario;
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public void crear_usuario(JTextField jTextField1_nombre, JTextField jTextField1_password,
            JTextField jTextField2_correo, int tipo_usuario) {
        Conexion objetoConexion = new Conexion();
           //validar los datos del usuario a ingresar  nombre
             String nombre = jTextField1_nombre.getText();
    String password = jTextField1_password.getText();
    String correo = jTextField2_correo.getText();
    int tipo = tipo_usuario + 1;
    
    // Validar los datos del usuario antes de ingresarlos a la base de datos
    if (nombre.isEmpty() || password.isEmpty() || correo.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    //correos validos: ejemplo@gmail.com , usuario_1@hotmail.com
    
    if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        JOptionPane.showMessageDialog(null, "El correo electrónico ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
     // Validar contraseña
      if (password.length() < 8) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
       }
    
    //ingresar la informacion a la base de datos 
        String consulta = "INSERT INTO cafeteria.usuario (nombre_usuario, contrasena_usuario, tipo_usuario, correo_usuario) VALUES (?, ?, ?, ?);";
        try {

            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombre_usuario());
            cs.setString(2, getContrasena_usuario());
            cs.setInt(3, getTipo_usuario());
            cs.setString(4, getCorreo_usuario());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Usuario creado con éxito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO INGRESAR AL USUARIO CORRECTAMENTE" + e.toString());
        }
    }

}
