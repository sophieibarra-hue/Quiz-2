import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaUsuarios sistema = new SistemaUsuarios();

        System.out.println("=== SISTEMA DE GESTIÓN DE USUARIOS ===");
        System.out.println("Usuario inicial: admin");
        System.out.println("Contraseña inicial: admin123");

        Usuario sesion = null;

        while (sesion == null) {
            System.out.print("\nUsuario: ");
            String username = sc.nextLine();

            System.out.print("Contraseña: ");
            String password = sc.nextLine();

            Usuario encontrado = sistema.buscarPorNombre(username);

            if (encontrado != null
                    && encontrado.getContraseña().equals(password)) {
                sesion = encontrado;
                sesion.registroAccion("Inicio de sesión");

                System.out.println(
                    "Bienvenido, " + sesion.getNombreCompleto()
                );
            } else {
                System.out.println("Credenciales incorrectas.");
            }
        }

        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("Sesión: "
                    + sesion.getNombreUsuario()
                    + " | Rol: " + sesion.getRol());

            System.out.println("1. Ver mi perfil");
            System.out.println("2. Actualizar perfil");
            System.out.println("3. Ver mi historial");
            System.out.println("4. Crear usuario");
            System.out.println("5. Listar usuarios");
            System.out.println("6. Buscar usuario");
            System.out.println("7. Actualizar usuario");
            System.out.println("8. Eliminar usuario");
            System.out.println("9. Consultar historial de usuario");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = SistemaUsuarios.leerEntero(sc);

            switch (opcion) {
                case 1:
                    sesion.mostrarDatos();
                    sesion.registroAccion("Consultó su perfil");
                    break;

                case 2:
                    sistema.actualizarUsuario(sesion, sc);
                    break;

                case 3:
                    sesion.mostrarHistorial();
                    sesion.registroAccion(
                        "Consultó su propio historial"
                    );
                    break;

                case 4:
                    sistema.crearUsuario(sesion, sc);
                    break;

                case 5:
                    sistema.listarUsuarios(sesion);
                    break;

                case 6:
                    sistema.buscarUsuario(sesion, sc);
                    break;

                case 7:
                    sistema.actualizarUsuario(sesion, sc);
                    break;

                case 8:
                    sistema.eliminarUsuario(sesion, sc);
                    break;

                case 9:
                    sistema.consultarHistorial(sesion, sc);
                    break;

                case 0:
                    sesion.registroAccion("Cerró sesión");
                    System.out.println("Hasta pronto.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
