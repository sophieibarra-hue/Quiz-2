public class MainPunto {
    public static void main(String[] args) {
        Punto p1 = new Punto();
        Punto p2 = new Punto(3.0, 4.0);

        System.out.print("Punto 1 (Origen): ");
        p1.mostrarCoordenadas();

        System.out.print("Punto 2: ");
        p2.mostrarCoordenadas();
        System.out.println("Distancia al origen de P2: " + p2.calcularDistanciaDesdeOrigen());

        p2.mover(1.0, -1.0);
        System.out.print("Punto 2 tras mover(1, -1): ");
        p2.mostrarCoordenadas();
    }
}
