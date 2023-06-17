import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class SistemaCitas {
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Administrador> administradores;
    private Scanner scanner;

    public SistemaCitas() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        administradores = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void iniciarSistema() {
        cargarAdministradores();

        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Acceder como administrador");
            System.out.println("2. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el número

            switch (opcion) {
                case 1:
                    accederComoAdministrador();
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }

    private void accederComoAdministrador() {
        System.out.print("Ingrese su identificador: ");
        String identificador = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Administrador administrador = buscarAdministrador(identificador, contrasena);
        if (administrador == null) {
            System.out.println("Identificador o contraseña incorrectos. No se puede acceder.");
            return;
        }

        mostrarMenuAdministrador();
    }

    private Administrador buscarAdministrador(String identificador, String contrasena) {
        for (Administrador administrador : administradores) {
            if (administrador.getIdentificador().equals(identificador) && administrador.getContrasena().equals(contrasena)) {
                return administrador;
            }
        }
        return null; // Si no se encuentra un administrador con los datos ingresados
    }

    private void mostrarMenuAdministrador() {
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Administrador ===");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el número

            switch (opcion) {
                case 1:
                    darDeAltaDoctor();
                    break;
                case 2:
                    darDeAltaPaciente();
                    break;
                case 3:
                    crearCita();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }

    private void darDeAltaDoctor() {
        System.out.print("Ingrese el identificador del doctor: ");
        String identificador = scanner.nextLine();
        System.out.print("Ingrese la especialidad del doctor: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(identificador, especialidad);
        doctores.add(doctor);

        System.out.println("Doctor agregado exitosamente.");
    }

    private void darDeAltaPaciente() {
        System.out.print("Ingrese el identificador del paciente: ");
        String identificador = scanner.nextLine();

        Paciente paciente = new Paciente(identificador);
        pacientes.add(paciente);

        System.out.println("Paciente agregado exitosamente.");
    }

    private void crearCita() {
        System.out.print("Ingrese el identificador del doctor: ");
        String identificadorDoctor = scanner.nextLine();
        Doctor doctor = buscarDoctorPorIdentificador(identificadorDoctor);
        if (doctor == null) {
            System.out.println("No se encontró un doctor con ese identificador.");
            return;
        }

        System.out.print("Ingrese el identificador del paciente: ");
        String identificadorPaciente = scanner.nextLine();
        Paciente paciente = buscarPacientePorIdentificador(identificadorPaciente);
        if (paciente == null) {
            System.out.println("No se encontró un paciente con ese identificador.");
            return;
        }

        System.out.print("Ingrese la fecha y hora de la cita (formato: dd/MM/yyyy HH:mm): ");
        String fechaHoraStr = scanner.nextLine();
        LocalDateTime fechaHora;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Fecha y hora inválidas. No se pudo crear la cita.");
            return;
        }

        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        Cita cita = new Cita(fechaHora, motivo, doctor, paciente);
        citas.add(cita);

        System.out.println("Cita creada exitosamente.");
    }

    private Administrador buscarAdministradorPorIdentificador(String identificador) {
        for (Administrador administrador : administradores) {
            if (administrador.getIdentificador().equals(identificador)) {
                return administrador;
            }
        }
        return null; // Agrega esta instrucción de retorno para cubrir el caso en que no se encuentre ningún administrador
    }

    private void cargarAdministradores() {
        try (BufferedReader reader = new BufferedReader(new FileReader("administradores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String identificador = data[0];
                String contrasena = data[1];
                Administrador administrador = new Administrador(identificador, contrasena);
                administradores.add(administrador);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los administradores. Se creará un archivo vacío.");
            crearArchivoAdministradores();
        }
    }

    private void crearArchivoAdministradores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("administradores.txt"))) {
            // Crear un archivo vacío
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de administradores.");
        }
    }

    private Doctor buscarDoctorPorIdentificador(String identificador) {
        for (Doctor doctor : doctores) {
            if (doctor.getIdentificador().equals(identificador)) {
                return doctor;
            }
        }
        return null;
    }

    private Paciente buscarPacientePorIdentificador(String identificador) {
        for (Paciente paciente : pacientes) {
            if (paciente.getIdentificador().equals(identificador)) {
                return paciente;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SistemaCitas sistemaCitas = new SistemaCitas();
        sistemaCitas.iniciarSistema();
    }
}
