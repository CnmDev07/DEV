import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "admin";

        String nombre, fecha_nacimiento, curso, consulta_SQL;
        Double nota_media;
        int filasInsertadas;
        Scanner sc = new Scanner(System.in);

        try(Connection conexion = DriverManager.getConnection(url, usuario, contraseña)){
            System.out.println("Introduce nombre de alumno: ");
            nombre = sc.nextLine();

            System.out.println("Introduce fecha de nacimiento (YYYY-MM-DD): ");
            fecha_nacimiento = sc.nextLine();

            System.out.println("Introduce nota media: ");
            nota_media = sc.nextDouble();
            sc.nextLine();

            System.out.println("Introduce curso del alumno: ");
            curso = sc.nextLine();

            consulta_SQL ="INSERT INTO estudiantes (nombre, fecha_nacimiento, nota_media, curso) VALUES (?, ?, ?, ?)";

            try(PreparedStatement statement = conexion.prepareStatement(consulta_SQL)){
                statement.setString(1, nombre);
                statement.setString(2, fecha_nacimiento);
                statement.setDouble(3, nota_media);
                statement.setString(4, curso);

                filasInsertadas = statement.executeUpdate();
                System.out.println("Se han insertado " + filasInsertadas + " registros en la base de datos.");

            }catch(SQLException e){
                e.getMessage();
            }
        }catch (SQLException e){
            e.getMessage();
        }
        sc.close();
    }
}
