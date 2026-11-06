public class Biblioteca {
    private Libro[] catalogo;
    private int cantidadLibros;

    public Biblioteca(int capacidadMaxima) {
        this.catalogo = new Libro[capacidadMaxima];
        this.cantidadLibros = 0;
    }

    private void registrarLog(String mensaje) {
        System.out.println("[LOG]: " + mensaje);
    }

    public boolean registrarLibro(String titulo, String autor, String isbn, int cantidadCopias) {
        if (titulo == null || titulo.trim().isEmpty() || 
            autor == null || autor.trim().isEmpty() || 
            isbn == null || isbn.trim().isEmpty()) {
            registrarLog("ERROR: No se pueden registrar datos vacíos.");
            return false;
        }
        if (cantidadCopias <= 0) {
            registrarLog("ERROR: La cantidad de copias debe ser un número positivo.");
            return false;
        }
        if (cantidadLibros >= catalogo.length) {
            registrarLog("ERROR: El catálogo alcanzó la capacidad máxima del sistema.");
            return false;
        }
        if (buscarPorIsbn(isbn) != null) {
            registrarLog("ERROR: Ya existe un libro con el ISBN " + isbn);
            return false;
        }

        Libro nuevoLibro = new Libro(titulo, autor, isbn, cantidadCopias);
        catalogo[cantidadLibros] = nuevoLibro;
        cantidadLibros++;
        registrarLog("ÉXITO: Libro '" + titulo + "' (ISBN: " + isbn + ") registrado exitosamente.");
        return true;
    }

    public Libro buscarPorIsbn(String isbn) {
        for (int i = 0; i < cantidadLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(isbn)) {
                return catalogo[i];
            }
        }
        return null;
    }

    public boolean prestarLibro(String isbn) {
        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            registrarLog("ERROR: Intento de préstamo fallido. No existe un libro con ISBN " + isbn);
            return false;
        }

        if (libro.prestar()) {
            registrarLog("ÉXITO: Préstamo realizado para '" + libro.getTitulo() + "'.");
            return true;
        } else {
            registrarLog("ERROR: No hay copias disponibles de '" + libro.getTitulo() + "' para prestar.");
            return false;
        }
    }

    public boolean devolverLibro(String isbn) {
        Libro libro = buscarPorIsbn(isbn);
        if (libro == null) {
            registrarLog("ERROR: Intento de devolución fallido. No existe un libro con ISBN " + isbn);
            return false;
        }

        if (libro.devolver()) {
            registrarLog("ÉXITO: Devolución registrada para '" + libro.getTitulo() + "'.");
            return true;
        } else {
            registrarLog("ERROR: Todas las copias registradas de este libro ya están en catálogo.");
            return false;
        }
    }

    public void mostrarCatalogo() {
        registrarLog("CONSULTA: Mostrando catálogo completo.");
        if (cantidadLibros == 0) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        for (int i = 0; i < cantidadLibros; i++) {
            System.out.println(catalogo[i]);
        }
    }
}
