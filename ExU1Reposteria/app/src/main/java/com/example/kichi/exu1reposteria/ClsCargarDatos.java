package com.example.kichi.exu1reposteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Kichi on 15/9/2017.
 */

public class ClsCargarDatos {

    public ClsConexion con;

    public ArrayList<String> cargarRepos() throws SQLException {

        ArrayList<String> lista = new ArrayList<>();

        con = new ClsConexion();
        Connection connection = con.ConnectionHelper();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from Tb_Recetas");
        while (rs.next()) {
            //Se extraen los datos
            lista.add(rs.getString("codigo_receta"));
            lista.add(rs.getString("nombre_receta"));
            lista.add(rs.getString("descripcion_receta"));
            lista.add(rs.getString("preparacion_receta"));
            lista.add(rs.getString("foto_receta"));
        }
        st.close();
        connection.close();
        return lista;
    }

    public ArrayList<String> buscarDato(int codigo) throws SQLException {

        ArrayList<String> lista = new ArrayList<>();

        con = new ClsConexion();
        Connection connection = con.ConnectionHelper();
        Statement st = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("select nombre_receta,descripcion_receta,preparacion_receta,foto_receta from Tb_Recetas where codigo_receta = ? ");
        preparedStatement.setInt(1,codigo);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            //Se extraen los datos
            lista.add(rs.getString("nombre_receta"));
            lista.add(rs.getString("descripcion_receta"));
            lista.add(rs.getString("preparacion_receta"));
            lista.add(rs.getString("foto_receta"));
        }
        st.close();
        connection.close();
        return lista;
    }

    public ArrayList<String> buscarIngrediente(int codigo) throws SQLException {
        ArrayList<String> lista = new ArrayList<>();

        con = new ClsConexion();
        Connection connection = con.ConnectionHelper();
        Statement st = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("select detalle_ingrediente from Tb_Ingredientes where codigo_receta=(?)");
        preparedStatement.setInt(1,codigo);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            //Se extraen los datos
            lista.add(rs.getString("detalle_ingrediente"));
        }
        st.close();
        connection.close();
        return lista;
    }
}
