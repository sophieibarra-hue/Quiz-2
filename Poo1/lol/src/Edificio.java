import java.util.ArrayList;

public class Edificio {
    private String direccion;
    private int capacidad;
    private ArrayList<Apartamento> apartamentos;

    public Edificio(String direccion, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.apartamentos = new ArrayList<>();
    }

    public void agregarApartamento(int numero, int habitaciones) {
        if (apartamentos.size() >= capacidad) {
            throw new IllegalStateException("Edificio lleno");
        }
        if (numero <=0 || habitaciones <= 0) {
            throw new IllegalArgumentException("Información invalida");
        }

        for (Apartamento apt : apartamentos) {
            if (apt.getNumero() == numero) {
                System.out.println("El apartamento que ingreso ya existe");
                return;
            }
        }
        apartamentos.add(new Apartamento(numero, habitaciones));
        System.out.println("Apartamento registrado con exito");
    }


        public void mostrarEdificio () {
        System.out.println("Edificio en: " + direccion);
        System.out.println("Capacidad del apartamento: " + capacidad);
        System.out.println("Apartamentos registrados : " + apartamentos.size());
    
    }

}
