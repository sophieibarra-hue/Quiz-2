import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String nombreUsiario;
    private String contrasena;
    private Rol rol; 
    private ArrayList<RegistroAccion> historial;

    public Usuario (int id, String nombreCompleto, String nombreUsiario, String contrasena, Rol rol) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.nombreUsiario = nombreUsiario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.historial = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsiario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Rol getRol() {
        return rol;
    }

}
    }


}
I