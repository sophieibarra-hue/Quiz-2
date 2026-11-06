public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private int copiasTotales;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String isbn, int copiasTotales) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasTotales;
    }

    public String getTitulo() { 
        return titulo; 
    }
    
    public String getAutor() { 
        return autor; 
    }
    
    public String getIsbn() { 
        return isbn; 
    }
    
    public int getCopiasDisponibles() { 
        return copiasDisponibles; 
    }
    
    public int getCopiasTotales() { 
        return copiasTotales; 
    }

    public boolean prestar() {
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            return true;
        }
        return false;
    }

    public boolean devolver() {
        if (copiasDisponibles < copiasTotales) {
            copiasDisponibles++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Título: " + titulo + " | Autor: " + autor + 
               " | Copias Disponibles: " + copiasDisponibles + "/" + copiasTotales;
    }
}
