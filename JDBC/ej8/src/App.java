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
        
        String curso, nombre;
        Double notaCorte, notaMedia;
        Scanner sc = new Scanner(System.in);
        
        try(Connection conexion = DriverManager.getConnection(url, usuario, contraseña)){
            System.out.println("Introduce el curso: ");
            curso = sc.nextLine();

            System.out.println("introduce la nota de corte: ");
            notaCorte =sc.nextDouble();

            consulta_SQL = "SELECT nombre, nota_media FROM estudiantes WHERE curso = ? AND nota_media > ?";

            try(PreparedStatement statement = conexion.prepareStatement(consulta_SQL)){
                statement.setString(1, curso);
                statement.setDouble(2, notaCorte);

                try(ResultSet resultSet =statement.executeQuery()){
                    System.out.println("Alumnos de curso " + curso + " con nota mayor que " + notaCorte + ":");
                    while(resultSet.next()){
                        nombre = resultSet.getString("nombre");
                        notaMedia = resultSet.getDouble("nota_media");
                        System.out.println("Nombre: " + nombre + ", Nota Media: " + notaMedia);
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        sc.close();
    }
}
