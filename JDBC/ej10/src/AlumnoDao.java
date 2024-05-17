import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao {
    private static final String url = "jdbc:mariadb://localhost:3306/estudiantes";
    private static final String user = "root";
    private static final String password = "admin";
    
    private Connection conexion;

    public AlumnoDao(){

    }

    public void conectar(){
        try{
            conexion = DriverManager.getConnection(url, user, password); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void create(Alumno alumno){
        String consultaSQL = "INSERT INTO estudiantes (id, nombre, fecha_nacimiento, nota_media, curso) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = conexion.prepareStatement(consultaSQL)){
            statement.setInt(1, alumno.getId());
            statement.setString(2, alumno.getNombre());
            statement.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
            statement.setDouble(4, alumno.getNotaMedia());
            statement.setString(5, alumno.getCurso());
            statement.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Alumno read (int id){
        String consultaSQL = "SELECT * FROM estudiantes WHERE id = ?";
        String nombre;
        LocalDate fechaNacimiento;
        double notaMedia;
        String curso;
        Alumno alumnoEncontrado = null;
        
        try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nombre = resultSet.getString("nombre");
                fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
                notaMedia = resultSet.getDouble("nota_Media");
                curso = resultSet.getString("curso");
                alumnoEncontrado = new Alumno(id, nombre, fechaNacimiento, notaMedia, curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnoEncontrado;
    }
    public void update (Alumno alumno){
        String consultaSQL = "UPDATE estudiantes SET nombre = ?, fecha_nacimiento = ?, nota_media = ?, curso = ?, WHERE id = ?";
        try(PreparedStatement statement = conexion.prepareStatement(consultaSQL)){
            statement.setString(1, alumno.getNombre());
            statement.setDate(2,Date.valueOf(alumno.getFechaNacimiento()) );
            statement.setDouble(3,alumno.getNotaMedia());
            statement.setString(4, alumno.getCurso());
            statement.setInt(5,alumno.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void delete (int id){
        String consultaSQL ="DELETE FROM estudiantes WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Alumno> readAll(){
        String consultaSQL = "SELECT * FROM estudiantes";
        List<Alumno> listaAlumnos = new ArrayList<>();
        
        try(PreparedStatement statement = conexion.prepareStatement(consultaSQL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
                double notaMedia = resultSet.getDouble("nota_media");
                String curso = resultSet.getString("curso");
                Alumno alumno = new Alumno (id, nombre, fechaNacimiento, notaMedia, curso);
                listaAlumnos.add(alumno);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listaAlumnos;
    }
    
}


