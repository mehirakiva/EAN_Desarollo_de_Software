import java.util.*;

// Clase Empresa
class Empresa {
    private String nombre;
    private String sector;
    private String standAsignado;

    public Empresa(String nombre, String sector) {
        this.nombre = nombre;
        this.sector = sector;
        this.standAsignado = null;  
    }

    public String getNombre() {
        return nombre;
    }

    public String getSector() {
        return sector;
    }

    public String getStandAsignado() {
        return standAsignado;
    }

    public void setStandAsignado(String stand) {
        this.standAsignado = stand;
    }
}

// Clase Visitante
class Visitante {
    private String nombre;
    private String identificacion;
    private String correo;

    public Visitante(String nombre, String identificacion, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }
}

// Clase Stand
class Stand {
    private String numero;
    private String ubicacion;
    private String tamaño;
    private boolean ocupado;

    public Stand(String numero, String ubicacion, String tamaño) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.tamaño = tamaño;
        this.ocupado = false;
    }

    public String getNumero() {
        return numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTamaño() {
        return tamaño;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void ocuparStand() {
        this.ocupado = true;
    }

    public void liberarStand() {
        this.ocupado = false;
    }
}


class Comentario {
    private String nombreVisitante;
    private String fecha;
    private int calificacion;
    private String comentario;

    public Comentario(String nombreVisitante, String fecha, int calificacion, String comentario) {
        this.nombreVisitante = nombreVisitante;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }
}

// Clase FeriaEmpresarial (Clase principal)
public class FeriaEmpresarial {
    private List<Empresa> empresas = new ArrayList<>();
    private List<Visitante> visitantes = new ArrayList<>();
    private List<Stand> stands = new ArrayList<>();
    private Map<String, List<Comentario>> comentarios = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void registrarEmpresa() {
        System.out.print("Nombre de la empresa: ");
        String nombre = scanner.nextLine();
        System.out.print("Sector de la empresa: ");
        String sector = scanner.nextLine();
        empresas.add(new Empresa(nombre, sector));
        System.out.println("Empresa registrada correctamente.\n");
    }

    public void listarEmpresas() {
        if (empresas.isEmpty()) {
            System.out.println("No hay empresas registradas.\n");
            return;
        }
        System.out.println("Empresas registradas:");
        for (Empresa empresa : empresas) {
            System.out.println("- " + empresa.getNombre() + " (Sector: " + empresa.getSector() + ")");
        }
        System.out.println();
    }

    public void registrarVisitante() {
        System.out.print("Nombre del visitante: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación del visitante: ");
        String identificacion = scanner.nextLine();
        System.out.print("Correo electrónico del visitante: ");
        String correo = scanner.nextLine();
        visitantes.add(new Visitante(nombre, identificacion, correo));
        System.out.println("Visitante registrado correctamente.\n");
    }

    public void listarVisitantes() {
        if (visitantes.isEmpty()) {
            System.out.println("No hay visitantes registrados.\n");
            return;
        }
        System.out.println("Visitantes registrados:");
        for (Visitante visitante : visitantes) {
            System.out.println("- " + visitante.getNombre() + " (ID: " + visitante.getIdentificacion() + ")");
        }
        System.out.println();
    }

    public void registrarStand() {
        System.out.print("Número de stand: ");
        String numero = scanner.nextLine();
        System.out.print("Ubicación del stand: ");
        String ubicacion = scanner.nextLine();
        System.out.print("Tamaño del stand (pequeño, mediano, grande): ");
        String tamaño = scanner.nextLine();
        stands.add(new Stand(numero, ubicacion, tamaño));
        System.out.println("Stand registrado correctamente.\n");
    }

    public void listarStands() {
        if (stands.isEmpty()) {
            System.out.println("No hay stands registrados.\n");
            return;
        }
        System.out.println("Stands registrados:");
        for (Stand stand : stands) {
            System.out.println("- " + stand.getNumero() + " (Ubicación: " + stand.getUbicacion() + ", Tamaño: " + stand.getTamaño() + ")");
        }
        System.out.println();
    }

    public void asignarStand() {
        if (stands.isEmpty() || empresas.isEmpty()) {
            System.out.println("No hay stands disponibles o empresas registradas.\n");
            return;
        }
        System.out.print("Seleccione el número de la empresa (0 para cancelar): ");
        int opcionEmpresa = scanner.nextInt();
        scanner.nextLine();  
        if (opcionEmpresa == 0) return;

        Empresa empresaSeleccionada = empresas.get(opcionEmpresa - 1);

        System.out.print("Seleccione el número del stand (0 para cancelar): ");
        int opcionStand = scanner.nextInt();
        scanner.nextLine();  
        if (opcionStand == 0) return;

        Stand standSeleccionado = stands.get(opcionStand - 1);
        if (standSeleccionado.estaOcupado()) {
            System.out.println("Este stand ya está ocupado.\n");
        } else {
            standSeleccionado.ocuparStand();
            empresaSeleccionada.setStandAsignado(standSeleccionado.getNumero());
            System.out.println("Stand asignado correctamente a la empresa.\n");
        }
    }

    public void generarReporteEmpresas() {
        if (empresas.isEmpty()) {
            System.out.println("No hay empresas registradas para generar el reporte.\n");
            return;
        }
        System.out.println("Reporte de empresas y stands asignados:");
        for (Empresa empresa : empresas) {
            System.out.println("- " + empresa.getNombre() + " (Stand: " + empresa.getStandAsignado() + ")");
        }
    }

    public void generarReporteVisitantes() {
        if (visitantes.isEmpty()) {
            System.out.println("No hay visitantes registrados para generar el reporte.\n");
            return;
        }
        System.out.println("Reporte de visitantes:");
        for (Visitante visitante : visitantes) {
            System.out.println("- " + visitante.getNombre() + " (ID: " + visitante.getIdentificacion() + ")");
        }
    }

    public void menu() {
        while (true) {

            System.out.println("===============UNIVERSIDAD EAN============================");
            System.out.println("==========================================================");
            System.out.println("1. Registrar empresa");
            System.out.println("2. Listar empresas");
            System.out.println("3. Registrar visitante");
            System.out.println("4. Listar visitantes");
            System.out.println("5. Registrar stand");
            System.out.println("6. Listar stands");
            System.out.println("7. Asignar stand a empresa");
            System.out.println("8. Generar reporte de empresas");
            System.out.println("9. Generar reporte de visitantes");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 10) {
                System.out.println("Saliendo del sistema...");
                break;
            }

            switch (opcion) {
                case 1:
                    registrarEmpresa();
                    break;
                case 2:
                    listarEmpresas();
                    break;
                case 3:
                    registrarVisitante();
                    break;
                case 4:
                    listarVisitantes();
                    break;
                case 5:
                    registrarStand();
                    break;
                case 6:
                    listarStands();
                    break;
                case 7:
                    asignarStand();
                    break;
                case 8:
                    generarReporteEmpresas();
                    break;
                case 9:
                    generarReporteVisitantes();
                    break;
                default:
                    System.out.println("Opción inválida.\n");
            }
        }
    }

    public static void main(String[] args) {
        FeriaEmpresarial feria = new FeriaEmpresarial();
        feria.menu();
    }
}
