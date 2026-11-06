public class TareaProyecto {
    private String descripcion;
    private String estado; 

    public TareaProyecto(String descripcion) {
        this.descripcion = descripcion;
        this.estado = "Pendiente";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public void mostrarInfo() {
        System.out.println("  - Tarea: " + descripcion + " [" + estado + "]");
    }
}
