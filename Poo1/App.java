import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    static ArrayList<Alquiler> alquileres = new ArrayList<>();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n========== SISTEMA DE ALQUILER DE VEHICULOS ==========");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar automovil");
            System.out.println("3. Registrar motocicleta");
            System.out.println("4. Mostrar vehiculos");
            System.out.println("5. Realizar alquiler");
            System.out.println("6. Mostrar alquileres");
            System.out.println("7. Finalizar alquiler");
            System.out.println("8. Consultar inventario");
            System.out.println("9. Mostrar clientes");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> registrarAutomovil();
                case 3 -> registrarMotocicleta();
                case 4 -> mostrarVehiculos();
                case 5 -> realizarAlquiler();
                case 6 -> mostrarAlquileres();
                case 7 -> finalizarAlquiler();
                case 8 -> mostrarInventario();
                case 9 -> mostrarClientes();
                case 10 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 10);

        sc.close();
    }

    static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            sc.nextLine();
        }

        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }


    static void registrarCliente() {
        System.out.println("\n===== REGISTRAR CLIENTE =====");

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        if (buscarCliente(documento) != null) {
            System.out.println("Ya existe un cliente con ese documento.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        clientes.add(new Cliente(documento, nombre, telefono));

        System.out.println("Cliente registrado correctamente.");
    }

    static void mostrarClientes() {
        System.out.println("\n===== CLIENTES REGISTRADOS =====");

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente c : clientes) {
            System.out.println("------------------------------");
            System.out.println("Documento: " + c.documento);
            System.out.println("Nombre: " + c.nombre);
            System.out.println("Telefono: " + c.telefono);
        }

        System.out.println("------------------------------");
        System.out.println("Total de clientes: " + clientes.size());
    }

    static void registrarAutomovil() {
        System.out.println("\n===== REGISTRAR AUTOMOVIL =====");

        System.out.print("Placa: ");
        String placa = sc.nextLine();

        if (buscarVehiculo(placa) != null) {
            System.out.println("Ya existe un vehiculo con esa placa.");
            return;
        }

        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        Automovil auto = new Automovil(placa, marca, modelo);

        vehiculos.add(auto);

        System.out.println("Automovil registrado correctamente.");

        registrarInventarioManual(auto);
    }

    static void registrarMotocicleta() {
        System.out.println("\n===== REGISTRAR MOTOCICLETA =====");

        System.out.print("Placa: ");
        String placa = sc.nextLine();

        if (buscarVehiculo(placa) != null) {
            System.out.println("Ya existe un vehiculo con esa placa.");
            return;
        }

        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        Motocicleta moto = new Motocicleta(placa, marca, modelo);

        vehiculos.add(moto);

        System.out.println("Motocicleta registrada correctamente.");
    }

    static void registrarInventarioManual(Vehiculo vehiculo) {
        System.out.println("\n===== INVENTARIO DEL AUTOMOVIL =====");
        System.out.println("Escriba FIN cuando termine de ingresar los elementos.");

        while (true) {
            System.out.print("\nNombre del elemento: ");
            String nombreElemento = sc.nextLine();

            if (nombreElemento.equalsIgnoreCase("FIN")) 
	    {
                break;
            }

            if (nombreElemento.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacio.");
                continue;
            }

            System.out.println("Estado del elemento:");
            System.out.println("1. Bueno");
            System.out.println("2. Regular");
            System.out.println("3. Danado");
            System.out.println("4. No tiene");
            System.out.print("Seleccione una opcion: ");

            int opcionEstado = leerEntero();
            String estadoElemento;

            switch (opcionEstado) {
                case 1 -> estadoElemento = "Bueno";
                case 2 -> estadoElemento = "Regular";
                case 3 -> estadoElemento = "Danado";
                case 4 -> estadoElemento = "No tiene";
                default -> {
                    System.out.println("Estado no valido. Intente nuevamente.");
                    continue;
                }
            }

            vehiculo.inventario.add(
                new ItemInventario(nombreElemento, estadoElemento)
            );

            System.out.println("Elemento agregado al inventario.");
        }

        System.out.println("Registro del inventario finalizado.");
    }

    static void mostrarInventario() {
        System.out.println("\n===== CONSULTAR INVENTARIO =====");

        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }

        for (Vehiculo v : vehiculos) {
            System.out.println("\nVehiculo: " + v.marca + " " + v.modelo);
            System.out.println("Placa: " + v.placa);

            if (v.inventario.isEmpty()) {
                System.out.println("No tiene elementos registrados en el inventario.");
            } else {
                for (ItemInventario item : v.inventario) {
                    System.out.println(
                        "- " + item.nombre + " | Estado: " + item.estado
                    );
                }
            }
        }
    }

    static void mostrarVehiculos() {
        System.out.println("\n===== VEHICULOS REGISTRADOS =====");

        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }

        for (Vehiculo v : vehiculos) {
            System.out.println("------------------------------");
            System.out.println("Tipo: " + v.getClass().getSimpleName());
            System.out.println("Placa: " + v.placa);
            System.out.println("Marca: " + v.marca);
            System.out.println("Modelo: " + v.modelo);
            System.out.println("Tarifa diaria: $" + v.tarifaDiaria);
            System.out.println("Estado: " + v.estado);
        }
    }

    static void realizarAlquiler() {
        System.out.println("\n===== REALIZAR ALQUILER =====");

        if (clientes.isEmpty()) {
            System.out.println("Primero debe registrar un cliente.");
            return;
        }

        if (vehiculos.isEmpty()) {
            System.out.println("Primero debe registrar un vehiculo.");
            return;
        }

        System.out.print("Documento del cliente: ");
        String documento = sc.nextLine();

        Cliente cliente = buscarCliente(documento);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Placa del vehiculo: ");
        String placa = sc.nextLine();

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            System.out.println("Vehiculo no encontrado.");
            return;
        }

        if (vehiculo.estado != EstadoVehiculo.DISPONIBLE) {
            System.out.println("El vehiculo no esta disponible para alquiler.");
            return;
        }

        System.out.print("Cantidad de dias de alquiler: ");
        int dias = leerEntero();

        if (dias <= 0) {
            System.out.println("La cantidad de dias debe ser mayor que cero.");
            return;
        }

        double total = vehiculo.tarifaDiaria * dias;

        Alquiler alquiler = new Alquiler(cliente, vehiculo, dias, total);

        alquileres.add(alquiler);
        vehiculo.estado = EstadoVehiculo.ALQUILADO;

        System.out.println("\nAlquiler realizado correctamente.");
        System.out.println("Cliente: " + cliente.nombre);
        System.out.println("Vehiculo: " + vehiculo.marca + " " + vehiculo.modelo);
        System.out.println("Dias: " + dias);
        System.out.printf("Total del alquiler: $%.2f%n", total);
    }

    static void mostrarAlquileres() {
        System.out.println("\n===== ALQUILERES REGISTRADOS =====");

        if (alquileres.isEmpty()) {
            System.out.println("No hay alquileres registrados.");
            return;
        }

        for (Alquiler a : alquileres) 
	    {
            System.out.println("------------------------------");
            System.out.println("Cliente: " + a.cliente.nombre);
            System.out.println("Documento: " + a.cliente.documento);
            System.out.println("Vehiculo: " + a.vehiculo.marca + " " + a.vehiculo.modelo);
            System.out.println("Placa: " + a.vehiculo.placa);
            System.out.println("Dias alquilados: " + a.dias);
            System.out.printf("Total: $%.2f%n", a.total);
            System.out.println(
                "Estado del alquiler: " +
                (a.finalizado ? "Finalizado" : "Activo")
            );
        }
    }

    static void finalizarAlquiler() {
        System.out.println("\n===== FINALIZAR ALQUILER =====");

        boolean hayActivos = false;

        for (Alquiler a : alquileres) {
            if (!a.finalizado) {
                hayActivos = true;
                break;
            }
        }

        if (!hayActivos) {
            System.out.println("No hay alquileres activos.");
            return;
        }

        System.out.print("Ingrese la placa del vehiculo que se devuelve: ");
        String placa = sc.nextLine();

        Alquiler alquilerEncontrado = null;

        for (Alquiler a : alquileres) {
            if (a.vehiculo.placa.equalsIgnoreCase(placa)
                    && !a.finalizado) {
                alquilerEncontrado = a;
                break;
            }
        }

        if (alquilerEncontrado == null) {
            System.out.println("No se encontro un alquiler activo para esa placa.");
            return;
        }

        alquilerEncontrado.finalizado = true;
        alquilerEncontrado.vehiculo.estado = EstadoVehiculo.DISPONIBLE;

        System.out.println("Alquiler finalizado correctamente.");
        System.out.println("El vehiculo esta disponible nuevamente.");
    }

    static Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.documento.equalsIgnoreCase(documento)) {
                return c;
            }
        }

        return null;
    }

    static Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.placa.equalsIgnoreCase(placa)) {
                return v;
            }
        }

        return null;
    }

    enum EstadoVehiculo {
        DISPONIBLE,
        ALQUILADO,
        MANTENIMIENTO
    }


    static class Cliente {
        String documento;
        String nombre;
        String telefono;

        Cliente(String documento, String nombre, String telefono) {
            this.documento = documento;
            this.nombre = nombre;
            this.telefono = telefono;
        }
    }

    static abstract class Vehiculo {
        String placa;
        String marca;
        String modelo;
        double tarifaDiaria;
        EstadoVehiculo estado;

        ArrayList<ItemInventario> inventario = new ArrayList<>();

        Vehiculo(String placa, String marca, String modelo,
                 double tarifaDiaria) {
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
            this.tarifaDiaria = tarifaDiaria;
            this.estado = EstadoVehiculo.DISPONIBLE;
        }
    }


    static class Automovil extends Vehiculo {
        Automovil(String placa, String marca, String modelo) {
            super(placa, marca, modelo, 150000);
        }
    }

    static class Motocicleta extends Vehiculo {
        Motocicleta(String placa, String marca, String modelo) {
            super(placa, marca, modelo, 50000);
        }
    }


    static class ItemInventario {
        String nombre;
        String estado;

        ItemInventario(String nombre, String estado) {
            this.nombre = nombre;
            this.estado = estado;
        }
    }

 
    static class Alquiler {
        Cliente cliente;
        Vehiculo vehiculo;
        int dias;
        double total;
        boolean finalizado;

        Alquiler(Cliente cliente, Vehiculo vehiculo,
                 int dias, double total) {
            this.cliente = cliente;
            this.vehiculo = vehiculo;
            this.dias = dias;
            this.total = total;
            this.finalizado = false;
        }
    }
}