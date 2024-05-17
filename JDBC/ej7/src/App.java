
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "admin";
        String consulta_SQL;
        int filasAfectadas;

        String nombreAlumno;
        Scanner sc = new Scanner(System.in);
        
        try(Connection conexion = DriverManager.getConnection(url, usuario, contraseña)){
            System.out.println("Introduce alumno a eliminar: ");
            nombreAlumno = sc.nextLine();
            //nombreAlumno = "xxx' OR '1' = '1";  //SQLIjection
            consulta_SQL = "DELETE FROM estudiantes WHERE nombre='" + nombreAlumno + "'";

            try(Statement statement = conexion.createStatement()){
                filasAfectadas = statement.executeUpdate(consulta_SQL);
                System.out.println("Se han eliminado " + filasAfectadas + " registros");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        sc.close();
    }
}
