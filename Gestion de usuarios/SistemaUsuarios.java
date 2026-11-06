import java.util.ArrayList;
import java.util.Scanner;

public class SistemaUsuarios {
    private ArrayList<Usuario> usuarios;
    private int siguienteId;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
        siguienteId = 1;

        Usuario admin = new Usuario(
            siguienteId++,
            "Administrador principal",
            "admin",
            "admin123",
            Rol.ADMINISTRADOR
        );

        usuarios.add(admin);
        admin.registroAccion("Creación de cuenta inicial");
    }

    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarPorNombre(String nombreUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario()
                    .equalsIgnoreCase(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }

    public boolean esAdministrador(Usuario usuario) {
        return usuario.getRol() == Rol.ADMINISTRADOR;
    }

    public void crearUsuario(Usuario actor, Scanner sc) {
        if (!esAdministrador(actor)) {
            System.out.println("No tienes permiso.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Nombre de usuario: ");
        String username = sc.nextLine().trim();

        if (nombre.isEmpty() || username.isEmpty()
                || buscarPorNombre(username) != null) {
            System.out.println(
                "Datos vacíos o nombre de usuario repetido."
            );
            return;
        }

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        if (password.isEmpty()) {
            System.out.println("La contraseña no puede estar vacía.");
            return;
        }

        System.out.println("1. Administrador");
        System.out.println("2. Estándar");
        System.out.print("Selecciona el rol: ");
        String opcion = sc.nextLine();

        Rol rol;

        if (opcion.equals("1")) {
            rol = Rol.ADMINISTRADOR;
        } else if (opcion.equals("2")) {
            rol = Rol.ESTANDAR;
        } else {
            System.out.println("Rol inválido.");
            return;
        }

        Usuario nuevo = new Usuario(
            siguienteId++, nombre, username, password, rol
        );

        usuarios.add(nuevo);
        nuevo.registroAccion("Cuenta creada");
        actor.registroAccion("Creó el usuario " + nuevo.getNombreUsuario());

        System.out.println(
            "Usuario creado. ID: " + nuevo.getId()
        );
    }

    public void listarUsuarios(Usuario actor) {
        if (!esAdministrador(actor)) {
            System.out.println("No tienes permiso.");
            return;
        }

        for (Usuario u : usuarios) {
            System.out.println("--------------------");
            u.mostrarDatos();
        }

        actor.registroAccion("Consultó la lista de usuarios");
    }

    public void buscarUsuario(Usuario actor, Scanner sc) {
        if (!esAdministrador(actor)) {
            System.out.println("No tienes permiso.");
            return;
        }

        System.out.print("ID del usuario: ");
        int id = leerEntero(sc);

        Usuario encontrado = buscarPorId(id);

        if (encontrado == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            encontrado.mostrarDatos();
            actor.registroAccion("Consultó el usuario con ID " + id );
        }
    }

    public void actualizarUsuario(Usuario actor, Scanner sc) {
        System.out.print("ID del usuario a modificar: ");
        int id = leerEntero(sc);

        Usuario objetivo = buscarPorId(id);

        if (objetivo == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        boolean admin = esAdministrador(actor);

        if (!admin && actor.getId() != objetivo.getId()) {
            System.out.println(
                "Solo puedes modificar tu propio perfil."
            );
            return;
        }

        System.out.println(
            "Deja vacío un campo para conservarlo."
        );

        System.out.print("Nuevo nombre completo: ");
        String nombre = sc.nextLine().trim();

        if (!nombre.isEmpty()) {
            objetivo.setNombreCompleto(nombre);
        }

        System.out.print("Nuevo nombre de usuario: ");
        String username = sc.nextLine().trim();

        if (!username.isEmpty()) {
            Usuario existente = buscarPorNombre(username);

            if (existente != null
                    && existente.getId() != objetivo.getId()) {
                System.out.println("Ese nombre de usuario ya existe.");
                return;
            }

            objetivo.setNombreUsuario(username);
        }

        System.out.print("Nueva contraseña: ");
        String password = sc.nextLine();

        if (!password.isEmpty()) {
            objetivo.setContraseña(password);
        }

        if (admin) {
            System.out.println("1. Conservar rol");
            System.out.println("2. Administrador");
            System.out.println("3. Estándar");
            System.out.print("Opción: ");

            String opcion = sc.nextLine();

            if (opcion.equals("2")) {
                objetivo.setRol(Rol.ADMINISTRADOR);
            } else if (opcion.equals("3")) {
                objetivo.setRol(Rol.ESTANDAR);
            }
        }

        objetivo.registroAccion("Perfil actualizado");
        actor.registroAccion("Actualizó el usuario con ID " + id);

        System.out.println("Usuario actualizado correctamente.");
    }

    public void eliminarUsuario(Usuario actor, Scanner sc) {
        if (!esAdministrador(actor)) {
            System.out.println("No tienes permiso.");
            return;
        }

        System.out.print("ID del usuario a eliminar: ");
        int id = leerEntero(sc);

        Usuario objetivo = buscarPorId(id);

        if (objetivo == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        if (objetivo.getId() == actor.getId()) {
            System.out.println(
                "No puedes eliminar tu propia cuenta.");
            return;
        }

        actor.registroAccion("Eliminó el usuario " + objetivo.getNombreUsuario());

        usuarios.remove(objetivo);
        System.out.println("Usuario eliminado.");
    }

    public void consultarHistorial(Usuario actor, Scanner sc) {
        Usuario objetivo = actor;

        if (esAdministrador(actor)) {
            System.out.print(
                "ID del usuario (0 para tu historial): "
            );
            int id = leerEntero(sc);

            if (id != 0) {
                objetivo = buscarPorId(id);

                if (objetivo == null) {
                    System.out.println("Usuario no encontrado.");
                    return;
                }
            }
        }

        objetivo.mostrarHistorial();

        actor.registroAccion("Consultó el historial de "+ objetivo.getNombreUsuario());
    }

    public static int leerEntero(Scanner sc) {
        while (true) {
            String entrada = sc.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un número válido: ");
            }
        }
    }
}
