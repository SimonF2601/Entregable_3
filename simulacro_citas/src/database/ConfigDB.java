package database;
//Esta clase se encargara de establecer y cerrar la conexion con la base de datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Este atributo tendra el estado de la conexion
    public static Connection objConnection = null;
    //Método para concetar la BD
    public static Connection openConnection (){
        try {
            //Llamamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Creamos las variables de conexion
            String url ="jdbc:mysql://bfrmv0hwg9zip38cs0g8-mysql.services.clever-cloud.com:3306/bfrmv0hwg9zip38cs0g8";
            String username = "u3aptezoqhoqejxu";
            String password = "Hqvmt3Pi0MgXncBNNyvk";

            //Establcer conexion
            objConnection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Conecto exitosamente");
        } catch (ClassNotFoundException error) {
            System.out.println("ERROR >> Driver not installed "+ error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR >> error al conectar la base de datos");
        }

        return objConnection;
    }

    //Método para finalizar la conexion
    public static void closeConnection(){
        try {
            //Si hay una conexion actica entonces la cierra
            if(objConnection != null) objConnection.close();
            System.out.println("Se finalizo la conexion con exito");
        }catch (SQLException error) {
            System.out.println("Error: "+ error.getMessage());
        }
    }
}


