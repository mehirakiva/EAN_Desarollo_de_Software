import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UniversidadEan {
    private static final int MAX_FACULTADES = 5;
    private String nombre;
    private String rector;
    private String ciudad;
    private Facultad[] facultades;
    private int totalFacultades;
    private Scanner scanner;

    public UniversidadEan(String nombre, String rector, String ciudad) {
        this.nombre = nombre;
        this.rector = rector;
        this.ciudad = ciudad;
        this.facultades = new Facultad[MAX_FACULTADES];
        this.totalFacultades = 0;
        this.scanner = new Scanner(System.in);
    }

    public void registrarFacultad() {
        if (totalFacultades >= MAX_FACULTADES) {
            System.out.println("No puedes agregar más facultades. Límite alcanzado.");
            return;
        }

        System.out.println("\nIngrese los datos de la facultad:");
        System.out.print("Nombre de la facultad: ");
        String nombreFacultad = scanner.nextLine();

        System.out.print("Código de la facultad: ");
        int codigoFacultad = scanner.nextInt();
        scanner.nextLine(); 

        facultades[totalFacultades] = new Facultad(nombreFacultad, codigoFacultad);
        totalFacultades++;
        System.out.println("Facultad registrada con éxito.");
    }

    public void registrarProfesor() {
        System.out.println("\nIngrese los datos del profesor:");
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombreProfesor = scanner.nextLine();

        System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Lugar de nacimiento: ");
        String lugarNacimiento = scanner.nextLine();

        System.out.print("Profesión: ");
        String profesion = scanner.nextLine();

        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        System.out.print("Sueldo: ");
        double sueldo = scanner.nextDouble();
        scanner.nextLine(); 

        // Asumimos que el usuario ya sabe a qué facultad pertenece el profesor.
        System.out.print("Ingrese el código de la facultad a la que pertenece el profesor: ");
        int codigoFacultad = scanner.nextInt();
        scanner.nextLine(); 

        Facultad facultadProfesor = null;
        for (Facultad facultad : facultades) {
            if (facultad != null && facultad.getCodigo() == codigoFacultad) {
                facultadProfesor = facultad;
                break;
            }
        }

        if (facultadProfesor == null) {
            System.out.println("Facultad no encontrada.");
            return;
        }

        Profesor profesor = new Profesor(cedula, nombreProfesor, fechaNacimiento, lugarNacimiento, profesion, nacionalidad, sueldo, facultadProfesor);
        facultadProfesor.agregarProfesor(profesor);
        System.out.println("Profesor registrado con éxito.");
    }

    public void registrarCarrera() {
        System.out.println("\nIngrese los datos de la carrera:");
        System.out.print("Nombre de la carrera: ");
        String nombreCarrera = scanner.nextLine();

        System.out.print("Número de créditos: ");
        int creditos = scanner.nextInt();

        System.out.print("Número de semestres: ");
        int semestres = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nivel (PREGRADO o POSTGRADO): ");
        String nivel = scanner.nextLine().toUpperCase();

        if (!nivel.equals("PREGRADO") && !nivel.equals("POSTGRADO")) {
            System.out.println("Nivel inválido. Debe ser PREGRADO o POSTGRADO.");
            return;
        }

        System.out.print("Ingrese el código de la facultad a la que pertenece la carrera: ");
        int codigoFacultad = scanner.nextInt();
        scanner.nextLine(); 

        Facultad facultadCarrera = null;
        for (Facultad facultad : facultades) {
            if (facultad != null && facultad.getCodigo() == codigoFacultad) {
                facultadCarrera = facultad;
                break;
            }
        }

        if (facultadCarrera == null) {
            System.out.println("Facultad no encontrada.");
            return;
        }

        Carrera carrera = new Carrera(nombreCarrera, creditos, semestres, nivel, facultadCarrera);
        facultadCarrera.agregarCarrera(carrera);
        System.out.println("Carrera registrada con éxito.");
    }

    public void registrarEstudiante() {
        System.out.println("\nIngrese los datos del estudiante:");
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombreEstudiante = scanner.nextLine();

        System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Lugar de nacimiento: ");
        String lugarNacimiento = scanner.nextLine();

        System.out.print("Colegio de bachillerato: ");
        String colegio = scanner.nextLine();

        System.out.print("Fecha de ingreso a la universidad (dd/mm/aaaa): ");
        String fechaIngreso = scanner.nextLine();

        Estudiante estudiante = new Estudiante(cedula, nombreEstudiante, fechaNacimiento, lugarNacimiento, colegio, fechaIngreso);
        System.out.println("Estudiante registrado con éxito.");
    }

    public void listarFacultades() {
        if (totalFacultades == 0) {
            System.out.println("No hay facultades registradas.");
            return;
        }
        System.out.println("\nListado de facultades:");
        for (Facultad f : facultades) {
            if (f != null) {
                f.mostrarInfo();
            }
        }
    }

    public void listarProfesores() {
        System.out.print("Ingrese el código de la facultad para listar los profesores: ");
        int codigoFacultad = scanner.nextInt();
        scanner.nextLine(); 

        Facultad facultadProfesor = null;
        for (Facultad facultad : facultades) {
            if (facultad != null && facultad.getCodigo() == codigoFacultad) {
                facultadProfesor = facultad;
                break;
            }
        }

        if (facultadProfesor == null) {
            System.out.println("Facultad no encontrada.");
            return;
        }

        facultadProfesor.listarProfesores();
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Registrar facultad");
            System.out.println("2. Registrar profesor");
            System.out.println("3. Registrar carrera");
            System.out.println("4. Registrar estudiante");
            System.out.println("5. Listar facultades");
            System.out.println("6. Listar profesores");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    registrarFacultad();
                    break;
                case 2:
                    registrarProfesor();
                    break;
                case 3:
                    registrarCarrera();
                    break;
                case 4:
                    registrarEstudiante();
                    break;
                case 5:
                    listarFacultades();
                    break;
                case 6:
                    listarProfesores();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 7);
    }

    public static void main(String[] args) {
        UniversidadEan universidad = new UniversidadEan("Universidad UAN", "Dr. Juan Pérez", "Ciudad UAN");
        universidad.menu();
    }
}

// Clase Facultad
class Facultad {
    private String nombre;
    private int codigo;
    private List<Carrera> carreras;
    private List<Profesor> profesores;

    public Facultad(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.carreras = new ArrayList<>();
        this.profesores = new ArrayList<>();
    }

    public void agregarCarrera(Carrera carrera) {
        carreras.add(carrera);
    }

    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public int getCodigo() {
        return codigo;
    }

    public void mostrarInfo() {
        System.out.println("Facultad: " + nombre + " (Código: " + codigo + ")");
    }

    public void listarProfesores() {
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados en esta facultad.");
            return;
        }

        System.out.println("\nListado de profesores:");
        for (Profesor p : profesores) {
            System.out.println("Profesor: " + p.getNombre() + " - Profesión: " + p.getProfesion());
        }
    }
}

// Clase Carrera
class Carrera {
    private String nombre;
    private int creditos;
    private int semestres;
    private String nivel;
    private Facultad facultad;

    public Carrera(String nombre, int creditos, int semestres, String nivel, Facultad facultad) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestres = semestres;
        this.nivel = nivel;
        this.facultad = facultad;
    }
}

// Clase Profesor
class Profesor {
    private String cedula;
    private String nombre;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String profesion;
    private String nacionalidad;
    private double sueldo;
    private Facultad facultad;

    public Profesor(String cedula, String nombre, String fechaNacimiento, String lugarNacimiento, String profesion, String nacionalidad, double sueldo, Facultad facultad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.profesion = profesion;
        this.nacionalidad = nacionalidad;
        this.sueldo = sueldo;
        this.facultad = facultad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesion() {
        return profesion;
    }
}

// Clase Estudiante
class Estudiante {
    private String cedula;
    private String nombre;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String colegio;
    private String fechaIngreso;

    public Estudiante(String cedula, String nombre, String fechaNacimiento, String lugarNacimiento, String colegio, String fechaIngreso) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.colegio = colegio;
        this.fechaIngreso = fechaIngreso;
    }
}
