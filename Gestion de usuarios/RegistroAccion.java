import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroAccion {
    private String descripcion;
    private LocalDateTime fecha;

public RegistroAccion(String descripcion) {
    this.descripcion = descripcion;
    this.fecha = LocalDateTime.now();
}

public String getDescripcion() {
    return descripcion;
}

public LocalDateTime getFecha() {
    return fecha;
}

public void mostrar() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    System.out.println(fecha.format(formato) + "-" + descripcion);
}
}
