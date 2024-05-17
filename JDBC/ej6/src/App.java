import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "admin";
        String consulta_SQL;

        String nombre, mejorAlumno, peorAlumno;
        Double notaMedia, mejorNota, peorNota;

        try(Connection conexion = DriverManager.getConnection(url, usuario, contraseña)){
            consulta_SQL = "SELECT nombre, nota_media FROM estudiantes ORDER BY nota_media DESC"; 

            try(PreparedStatement statment = conexion.prepareStatement(consulta_SQL);
                ResultSet resultSet = statment.executeQuery()){
                
                System.out.println("Nombre\t\tNota Media\n");
                while(resultSet.next()){
                    nombre = resultSet.getString("nombre");
                    notaMedia = resultSet.getDouble("nota_media");

                    System.out.println(nombre + "\t\t" + notaMedia);
                }
                if(resultSet.first()){
                    mejorAlumno = resultSet.getString("nombre");
                    mejorNota = resultSet.getDouble("nota_media");
                    System.out.println("\nMejor Alumno:\nNombre: " + mejorAlumno + ", Nota Media: " + mejorNota);
                }
                if(resultSet.last()){
                    peorAlumno = resultSet.getString("nombre");
                    peorNota = resultSet.getDouble("nota_media");
                    System.out.println("\nPeor Alumno:\nNombre: " + peorAlumno + ", Nota media: " + peorNota);
                }
            }
        }catch(SQLException e){
            e.getMessage();
        }
            
    }
}
