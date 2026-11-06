public class Apartamento {
    private int numero;
    private int habitaciones;

    public  Apartamento(int numero, int habitaciones) {
        this.numero = numero;
        this.habitaciones = habitaciones;
    }

    public int getNumero() {
        return numero;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void mostrarInformacion() {
        System.out.println("Apartamento número: " + numero);
        System.out.println("Número de habitaciones: " + habitaciones);
    }
}
