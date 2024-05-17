import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) {
        
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "admin";
        int filasAfectadas = 0;
        
        Connection conexion = null;
        Statement statement = null;

        String consultaSQL = "UPDATE estudiantes SET nota_media = nota_media + 1 WHERE curso = '1B';";

        try {
            
            conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            
            statement = conexion.createStatement();
            
            filasAfectadas = statement.executeUpdate(consultaSQL);
            
            System.out.println("Se han actualizado " + filasAfectadas + " registros.");

        } catch (SQLException e) {
            
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
