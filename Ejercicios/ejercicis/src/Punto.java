public class Punto {
    // Atributos Privados
    private double x;
    private double y;

    // Constructor que crea un punto en el origen (0,0)
    public Punto() {
        this.x = 0.0;
        this.y = 0.0;
    }

    // Constructor que recibe las coordenadas x e y
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Métodos de acceso (Getters y Setters)
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Desplaza las coordenadas sumando los deltas recibidos
    public void mover(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Calcula la distancia desde el origen (0,0) usando Pitágoras sqrt(x^2 + y^2)
    public double calcularDistanciaDesdeOrigen() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    // Muestra en consola el punto en formato (x, y)
    public void mostrarCoordenadas() {
        System.out.println("(" + this.x + ", " + this.y + ")");
    }
}
