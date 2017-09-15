package com.example.kichi.exu1adminrepos;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kichi on 15/9/2017.
 */

public class ClsGuardarIngredientes {

    public ClsConexion con;
    private Context context;

    public ClsGuardarIngredientes(Context context) {
        this.context = context;
    }


    public void guardaIngrediente(String[] ingrediente) throws SQLException {

        con = new ClsConexion();
        Connection connection = con.ConnectionHelper();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT TOP 1 codigo_receta from Tb_Recetas ORDER BY codigo_receta DESC");
        String datoConsultado = "";
        while (rs.next()) {
            //Se extraen los datos
            datoConsultado = rs.getString("codigo_receta");
        }

        for (int i=0;i<ingrediente.length;i++){
            String cadenaSql = "insert into Tb_Ingredientes values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(cadenaSql);
            preparedStatement.setString(1,datoConsultado);
            preparedStatement.setString(2,ingrediente[i]);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
            connection.close();
    }
}
