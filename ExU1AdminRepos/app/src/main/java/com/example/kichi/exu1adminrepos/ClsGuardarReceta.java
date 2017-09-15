package com.example.kichi.exu1adminrepos;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Kichi on 15/9/2017.
 */

public class ClsGuardarReceta {
    public ClsConexion con;
    private Context context;

    public ClsGuardarReceta(Context context){
        this.context = context;
    }

    public void guardarReceta(String nombre,String descripcion,String preparacion,String foto){

        con = new ClsConexion();
        Connection connection = con.ConnectionHelper();
        String cadenaSql = "insert into Tb_Recetas values (?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(cadenaSql);
            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,descripcion);
            preparedStatement.setString(3,preparacion);
            preparedStatement.setString(4,foto);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            mensaje("Registro Correcto.");
        } catch (Exception e) {
            e.printStackTrace();
            mensaje("Error al Registrar.");
        }
    }
    private void mensaje(String msg){
        Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show();
    }
}
