
import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mariadb://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña ="admin";

        int id, filasEliminadas;
        String consulta_SQL;
        Scanner sc = new Scanner(System.in);

        try(Connection conexion = DriverManager.getConnection(url, usuario, contraseña)){
            System.out.println("Introduce el numero del alumno a eliminar");
            id = sc.nextInt();
            consulta_SQL = "DELETE FROM estudiantes WHERE id = ?";

            try(PreparedStatement statement = conexion.prepareStatement(consulta_SQL)){
                statement.setInt(1, id);
                filasEliminadas = statement.executeUpdate();

                if(filasEliminadas > 0){
                    System.out.println("Se ha eliminado el alumno con numero " + id);
                }else{
                    System.out.println("No se encontro ningun almuno con ese numero");
                }
            }catch(SQLException e){
                System.out.println("ei");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        sc.close();
    }
}
