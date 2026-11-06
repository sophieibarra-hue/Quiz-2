public class MainProyecto {
    public static void main(String[] args) {
        // Crear un proyecto con espacio para maximo 2 tareas
        Proyecto miProyecto = new Proyecto("Sistema Academico", "Desarrollo del software escolar", 2);

        // Agregar tareas
        miProyecto.agregarTarea("Diseñar la base de datos");
        miProyecto.agregarTarea("Crear interfaz de usuario");

        // Intentar agregar una tercera tarea (supera el limite)
        miProyecto.agregarTarea("Realizar pruebas unitarias");

        // Mostrar informacion completa
        miProyecto.mostrarProyecto();
    }
}
