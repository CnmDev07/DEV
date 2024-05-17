import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "admin";
        String consulta_SQL;

        int opcion;
        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            do {
                System.out.println("Selecciona el tipo de informe:");
                System.out.println("1. Alumnos con bien");
                System.out.println("2. Alumnos con notable");
                opcion = sc.nextInt();

                if (opcion != 1 && opcion != 2) {
                    System.out.println("Opción no válida");
                }
            } while (opcion != 1 && opcion != 2);

            
            consulta_SQL = "SELECT nombre, nota_media FROM estudiantes WHERE nota_media BETWEEN ? AND ? ORDER BY nota_media ASC";

            try (PreparedStatement statement = conexion.prepareStatement(consulta_SQL)) {
                if (opcion == 1) {
                    
                    statement.setDouble(1, 6.0);
                    statement.setDouble(2, 6.9);
                } else {
                    
                    statement.setDouble(1, 7.0);
                    statement.setDouble(2, 8.9);
                }

                
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Informe alumnos:");
                    System.out.println("----------------");

                    while (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        double notaMedia = resultSet.getDouble("nota_media");
                        System.out.println("Nombre: " + nombre + ", Nota Media: " + notaMedia);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
