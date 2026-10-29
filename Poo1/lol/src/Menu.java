public class Menu {
    public static void main (String[] args) {
        Edificio edificio = new Edificio("Calle 100 #15-20, Bogota", 3);
        edificio.agregarApartamento(101, 2);
        edificio.agregarApartamento(102, 1);
        edificio.agregarApartamento(103, 3);

        edificio.agregarApartamento(607, 6);

        edificio.mostrarEdificio();
    }
    
}

