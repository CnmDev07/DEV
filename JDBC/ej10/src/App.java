import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AlumnoDao alumnoDao = new AlumnoDao();
        alumnoDao.conectar();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Menú:");
            System.out.println("1. Crear alumno");
            System.out.println("2. Leer alumno");
            System.out.println("3. Actualizar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Ver ID de todos los alumnos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Crear alumno
                    System.out.print("Ingrese el ID del alumno: ");
                    int id = scanner.nextInt();
                    System.out.print("Ingrese el nombre del alumno: ");
                    scanner.nextLine(); // Limpiar el buffer
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
                    String fechaNacimientoStr = scanner.next();
                    LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                    System.out.print("Ingrese la nota media del alumno: ");
                    double notaMedia = scanner.nextDouble();
                    System.out.print("Ingrese el curso del alumno: ");
                    String curso = scanner.next();

                    Alumno nuevoAlumno = new Alumno(id, nombre, fechaNacimiento, notaMedia, curso);
                    alumnoDao.create(nuevoAlumno);
                    System.out.println("Alumno creado con éxito.");
                    break;

                case 2:
                    // Leer alumno
                    System.out.print("Ingrese el ID del alumno a buscar: ");
                    int idBuscar = scanner.nextInt();
                    Alumno alumnoEncontrado = alumnoDao.read(idBuscar);
                    if (alumnoEncontrado != null) {
                        System.out.println("Alumno encontrado: " + alumnoEncontrado);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 3:
                    // Actualizar alumno
                    System.out.print("Ingrese el ID del alumno a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    Alumno alumnoActualizar = alumnoDao.read(idActualizar);
                    if (alumnoActualizar != null) {
                        System.out.print("Ingrese el nuevo nombre del alumno: ");
                        scanner.nextLine(); // Limpiar el buffer
                        String nuevoNombre = scanner.nextLine();
                        alumnoActualizar.setNombre(nuevoNombre);
                        alumnoDao.update(alumnoActualizar);
                        System.out.println("Alumno actualizado con éxito.");
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 4:
                    // Eliminar alumno
                    System.out.print("Ingrese el ID del alumno a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    alumnoDao.delete(idEliminar);
                    System.out.println("Alumno eliminado con éxito.");
                    break;

                case 5:
                    // Ver todos los alumnos
                    List<Alumno> listaAlumnos = alumnoDao.readAll();
                    System.out.println("Alumnos en la base de datos:");
                    for (Alumno alumno : listaAlumnos) {
                        System.out.println("ID: " + alumno.getId() + ", Nombre: " + alumno.getNombre());
                    }
                    break;
                case 6:
                System.out.println("Saliendo del programa");

                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}