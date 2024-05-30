import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final Scanner sc = new Scanner(System.in);
    private static AlumnoDao alumnoDao = new AlumnoDao();

    public static void main(String[] args) {
        alumnoDao.conectar();

        int opcion;

        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 7);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Crear alumno");
        System.out.println("2. Leer alumno");
        System.out.println("3. Actualizar alumno");
        System.out.println("4. Eliminar alumno");
        System.out.println("5. Ver todos los alumnos");
        System.out.println("6. Alumnos por curso");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcion() {
        int opcion = sc.nextInt();
        sc.nextLine(); 
        System.out.println("\n");
        return opcion;
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearAlumno();
                break;
            case 2:
                leerAlumno();
                break;
            case 3:
                actualizarAlumno();
                break;
            case 4:
                eliminarAlumno();
                break;
            case 5:
                verTodosLosAlumnos();
                break;
            case 6:
                alumnosPorCurso();
                break;
            case 7:
                alumnoDao.desconectar();
                logger.info("Saliendo del programa");
                break;
            default:
                logger.warning("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

    private static void crearAlumno() {
        String nombre;
        LocalDate fechaNacimiento;
        double notaMedia;
        String curso;
        Alumno nuevoAlumno = null;

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
        logger.info("Alumno creado con éxito.");
    }

    private static void leerAlumno() {
        int id;
        Alumno alumnoEncontrado = null;

        System.out.print("Ingrese el ID del alumno a buscar: ");
        id = sc.nextInt();
        alumnoEncontrado = alumnoDao.read(id);
        if (alumnoEncontrado != null) {
            logger.info("Alumno encontrado: " + alumnoEncontrado);
        } else {
            logger.warning("Alumno no encontrado.");
        }
    }

    private static void actualizarAlumno() {
        int idActualizar;
        Alumno alumnoActualizar;

        System.out.print("Ingrese el ID del alumno a actualizar: ");
        idActualizar = sc.nextInt();
        alumnoActualizar = alumnoDao.read(idActualizar);
        if (alumnoActualizar != null) {
            System.out.print("Ingrese el nuevo nombre del alumno: ");
            sc.nextLine(); 
            String nuevoNombre = sc.nextLine();
            alumnoActualizar.setNombre(nuevoNombre);
            alumnoDao.update(alumnoActualizar);
            logger.info("Alumno actualizado con éxito.");
        } else {
            logger.warning("Alumno no encontrado.");
        }
    }

    private static void eliminarAlumno() {
        int idEliminar;

        System.out.print("Ingrese el ID del alumno a eliminar: ");
        idEliminar = sc.nextInt();
        alumnoDao.delete(idEliminar);
        logger.info("Alumno eliminado con éxito.");
    }

    private static void verTodosLosAlumnos() {
        List<Alumno> listaAlumnos = alumnoDao.readAll();
        System.out.println("\nAlumnos en la base de datos:");
        System.out.println(" _____________________________________________________________________");
        System.out.println("| ID   | Nombre               | Fecha Nacimiento | Nota Media | Curso |");
        System.out.println("|------|----------------------|------------------|------------|-------|");
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno);
        }
        System.out.println(" ---------------------------------------------------------------------\n");
    }

    private static void alumnosPorCurso() {
        String cursoBuscar;
        List<Alumno> alumnosPorCurso = null;

        System.out.print("Introduce el curso: ");
        cursoBuscar = sc.nextLine();
        alumnosPorCurso = alumnoDao.leerPorCurso(cursoBuscar);

        if (alumnosPorCurso.isEmpty()) {
            logger.warning("No hay alumnos en ese curso.");
        } else {
            System.out.println("\nAlumnos en el curso " + cursoBuscar + ":");
            System.out.println(" _____________________________________________________________________");
            System.out.println("| ID   | Nombre               | Fecha Nacimiento | Nota Media | Curso |");
            System.out.println("|------|----------------------|------------------|------------|-------|");
            for (Alumno alumno : alumnosPorCurso) {
                System.out.println(alumno);
            }
            System.out.println(" ---------------------------------------------------------------------\n");
        }
    }
}
