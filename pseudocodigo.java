import java.util.ArrayList;

class Administrador {
  private String identificador;
  private String contraseña;
  
  public boolean verificarCredenciales(String identificador, String contraseña) {
    // Lógica para verificar las credenciales
  }
}

class Doctor {
  private int id;
  private String nombre;
  private String especialidad;
  
  public void consultarAgenda() {
    // Lógica para consultar la agenda del doctor
  }
}

class Paciente {
  private int id;
  private String nombre;
  
  public void consultarHistorial() {
    // Lógica para consultar el historial del paciente
  }
}

class Cita {
  private int id;
  private String fechaHora;
  private String motivo;
  private Doctor doctor;
  private Paciente paciente;
}

class SistemaCitas {
  private ArrayList<Administrador> administradores;
  private ArrayList<Doctor> doctores;
  private ArrayList<Paciente> pacientes;
  private ArrayList<Cita> citas;
  
  public void altaDoctor(int id, String nombre, String especialidad) {
    // Lógica para dar de alta un doctor
  }
  
  public void altaPaciente(int id, String nombre) {
    // Lógica para dar de alta un paciente
  }
  
  public void crearCita(int id, String fechaHora, String motivo) {
    // Lógica para crear una cita
  }
  
  public void relacionarCita(int idCita, int idDoctor, int idPaciente) {
    // Lógica para relacionar una cita con un doctor y un paciente
  }
  
  public boolean controlAcceso(String identificador, String contraseña) {
    // Lógica para controlar el acceso al sistema
  }
}

public class Main {
  public static void main(String[] args) {
    // Crear una instancia del sistema de citas
    SistemaCitas sistemaCitas = new SistemaCitas();

    // Dar de alta un administrador
    sistemaCitas.altaDoctor("admin", "contraseña");

    // Dar de alta un doctor
    sistemaCitas.altaDoctor(1, "Dr. Juan Pérez", "Pediatría");

    // Dar de alta un paciente
    sistemaCitas.altaPaciente(1001, "Luisa García");

    // Crear una cita
    sistemaCitas.crearCita(1, "2023-06-04 10:00", "Consulta general");

    // Relacionar la cita con un doctor y un paciente
    sistemaCitas.relacionarCita(1, 1, 1001);

    // Verificar credenciales de un administrador
    boolean credencialesCorrectas = sistemaCitas.controlAcceso("admin", "contraseña");

    if (credencialesCorrectas) {
      System.out.println("Acceso permitido al sistema");
    } else {
      System.out.println("Acceso denegado al sistema");
    }
  }
}
