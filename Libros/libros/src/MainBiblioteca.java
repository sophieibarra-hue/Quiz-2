public class MainBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(5);

        // Registro de libros válidos
        biblioteca.registrarLibro("Cien Años de Soledad", "Gabriel García Márquez", "978-1", 3);
        biblioteca.registrarLibro("1984", "George Orwell", "978-2", 2);

        // Intento de errores (ISBN duplicado y copias negativas)
        biblioteca.registrarLibro("Otro Libro", "Autor X", "978-1", 1);
        biblioteca.registrarLibro("Libro Inválido", "Autor Y", "978-3", -5);

        // Préstamos y devoluciones
        biblioteca.prestarLibro("978-2");
        biblioteca.prestarLibro("978-2");
        biblioteca.prestarLibro("978-2"); // Falla por falta de copias

        biblioteca.devolverLibro("978-2");

        // Mostrar el estado final del catálogo
        System.out.println("\n--- ESTADO DEL CATÁLOGO ---");
        biblioteca.mostrarCatalogo();
    }
}
