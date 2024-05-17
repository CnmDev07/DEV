import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "admin";
        String consulta_SQL;
        String curso;
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println("Introduce el curso de los alumnos que desea mostrar");
        curso = sc.nextLine();
        
        consulta_SQL = "SELECT nombre, fecha_nacimiento FROM estudiantes WHERE curso = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement statement = conexion.prepareStatement(consulta_SQL)) {
            
            statement.setString(1, curso);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                    System.out.println("Nombre: " + nombre + ", Fecha de nacimiento: " + fechaNacimiento);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
