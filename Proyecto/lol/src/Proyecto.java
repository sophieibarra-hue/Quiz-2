public class Proyecto {
    private String nombre;
    private String descripcion;
    private TareaProyecto[] tareas;
    private int contadorTareas;

	
    public Proyecto(String nombre, String descripcion, int maxTareas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tareas = new TareaProyecto[maxTareas];
        this.contadorTareas = 0;
    }

    public boolean agregarTarea(String descripcionTarea) {
        if (contadorTareas < tareas.length) {
            tareas[contadorTareas] = new TareaProyecto(descripcionTarea);
            contadorTareas++;
            return true;
        } else {
            System.out.println("No se pueden añadir más tareas al proyecto (capacidad máxima alcanzada).");
            return false;
        }
    }

    public void mostrarProyecto() {
        System.out.println("Proyecto: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Tareas asignadas (" + contadorTareas + "/" + tareas.length + "):");
        for (int i = 0; i < contadorTareas; i++) {
            tareas[i].mostrarInfo();
        }
    }
}
