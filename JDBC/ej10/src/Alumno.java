import java.time.LocalDate;

public class Alumno {
    private int id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private double notaMedia;
    private String curso;

    public Alumno(int id, String nombre, LocalDate fechaNacimiento, double notaMedia, String curso){
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.notaMedia = notaMedia;
        this.curso = curso;
    }
    public Alumno(String nombre, LocalDate fechaNacimiento, double notaMedia, String curso){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.notaMedia = notaMedia;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre.length() > 30) ? nombre.substring(0,30) : nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = (curso.length() == 2) ? curso : null;
    }
    @Override
public String toString() {
    return String.format("| %-4d | %-20s | %-16s | %-10.2f | %-5s |",
                        id, nombre, fechaNacimiento, notaMedia, curso);
}
}

