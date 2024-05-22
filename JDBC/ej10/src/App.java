import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AlumnoDao alumnoDao = new AlumnoDao();
        alumnoDao.conectar();

        int id;
        String nombre;
        LocalDate fechaNacimiento;
        double notaMedia;
        String curso;

        Alumno nuevoAlumno = null;


        List<Alumno> alumnosPorCurso = null;
        String cursoBuscar;

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n");
            System.out.println("Menú:");
            System.out.println("1. Crear alumno");
            System.out.println("2. Leer alumno");
            System.out.println("3. Actualizar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Ver todos los alumnos");
            System.out.println("6. Alumnos por curso");
            System.out.println("7. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    
                    System.out.print("Ingrese el nombre del alumno: ");
                    nombre = sc.nextLine();
                    System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
                    fechaNacimiento = LocalDate.parse(sc.next());
                    System.out.print("Ingrese la nota media del alumno: ");
                    notaMedia = sc.nextDouble();
                    System.out.print("Ingrese el curso del alumno: ");
                    curso = sc.next();

                    nuevoAlumno = new Alumno(nombre, fechaNacimiento, notaMedia, curso);
                    alumnoDao.create(nuevoAlumno);
                    System.out.println("Alumno creado con éxito.");
                    break;

                case 2:
                    
                    System.out.print("Ingrese el ID del alumno a buscar: ");
                    id = sc.nextInt();
                    Alumno alumnoEncontrado = alumnoDao.read(id);
                    if (alumnoEncontrado != null) {
                        System.out.println("Alumno encontrado: " + alumnoEncontrado);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 3:
                    
                    System.out.print("Ingrese el ID del alumno a actualizar: ");
                    int idActualizar = sc.nextInt();
                    Alumno alumnoActualizar = alumnoDao.read(idActualizar);
                    if (alumnoActualizar != null) {
                        System.out.print("Ingrese el nuevo nombre del alumno: ");
                        sc.nextLine(); 
                        String nuevoNombre = sc.nextLine();
                        alumnoActualizar.setNombre(nuevoNombre);
                        alumnoDao.update(alumnoActualizar);
                        System.out.println("Alumno actualizado con éxito.");
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 4:
                    
                    System.out.print("Ingrese el ID del alumno a eliminar: ");
                    int idEliminar = sc.nextInt();
                    alumnoDao.delete(idEliminar);
                    
                    break;

                    case 5:
                    List<Alumno> listaAlumnos = alumnoDao.readAll();
                    System.out.println("\n");
                    System.out.println("Alumnos en la base de datos:");
                    System.out.println(" _____________________________________________________________________");
                    System.out.println("| ID   | Nombre               | Fecha Nacimiento | Nota Media | Curso |");
                    System.out.println("|------|----------------------|------------------|------------|-------|");
                    for (Alumno alumno : listaAlumnos) {
                        System.out.println(alumno);
                    }
                    System.out.println(" ---------------------------------------------------------------------");
                    System.out.println("\n");
                    break;
                case 6:
                    System.out.println("Introduce el curso");
                    cursoBuscar = sc.nextLine();
                    alumnosPorCurso = alumnoDao.leerPorCurso(cursoBuscar);

                    if (alumnosPorCurso.isEmpty()){
                        System.out.println("No hay alumnos en ese curso");
                    }else {
                        System.out.println("\n");
                        System.out.println("Alumnos en la base de datos:");
                        System.out.println(" _____________________________________________________________________");
                        System.out.println("| ID   | Nombre               | Fecha Nacimiento | Nota Media | Curso |");
                        System.out.println("|------|----------------------|------------------|------------|-------|");
                        for (Alumno alumno : alumnosPorCurso) {
                            System.out.println(alumno);
                        }
                        System.out.println(" ---------------------------------------------------------------------");
                        System.out.println("\n");
                    }
                    break;
                case 7:
                System.out.println("Saliendo del programa");

                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 7);

        sc.close();
    }
}