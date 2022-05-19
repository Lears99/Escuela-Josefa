package Capa_Dato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    static Connection contacto = null;
    
    public static Connection getConexion(){
    
        String url = "jdbc:sqlserver://34.69.84.99:1433;database=BD_Josefa";
        //"jdbc:sqlserver://DESKTOP-1GACUKD:1433;databaseName=BD_Josefa"
        try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            JOptionPane.showConfirmDialog(null, "no se pudo establecer la conexion... revisar driver" + e.getMessage(),
                    "error de conexion",JOptionPane.ERROR_MESSAGE);
        }try{                                       //usuario , Contraseña   
            contacto = DriverManager.getConnection(url,"sqlserver","root"); // Aqui colocar el usuario y la contraseña de su base de datos
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, "error" + e.getMessage(),
                    "error de conexion",JOptionPane.ERROR_MESSAGE);
        }
        return contacto;    
    }
    public static ResultSet Consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
    try{
    declara=con.createStatement();
    ResultSet respuesta = declara.executeQuery(consulta);
    return respuesta;
} catch (SQLException e) {
        JOptionPane.showConfirmDialog(null, "error" + e.getMessage(),
                    "error de conexion",JOptionPane.ERROR_MESSAGE);
   }
    return null;
    }
}
