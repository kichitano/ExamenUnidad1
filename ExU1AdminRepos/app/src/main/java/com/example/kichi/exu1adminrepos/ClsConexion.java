package com.example.kichi.exu1adminrepos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Corei7 on 14/09/2017.
 */

public class ClsConexion {
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String con = "jdbc:jtds:sqlserver://192.168.2.106;port=1433;databaseName=DB_Recetas;user=sa;password=123";
            connection = DriverManager.getConnection(con);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
