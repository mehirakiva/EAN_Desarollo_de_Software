public class Tienda {
    private static final int MAX_PRODUCTOS = 4;
    private String nombre;
    private String direccion;
    private String telefono;
    private Producto[] productos;
    private int totalProductos;
    private Scanner scanner;

    public Tienda(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.productos = new Producto[MAX_PRODUCTOS];
        this.totalProductos = 0;
        this.scanner = new Scanner(System.in);
    }

    public void registrarProducto() {
        if (totalProductos >= MAX_PRODUCTOS) {
            System.out.println("No puedes agregar más productos. Límite alcanzado.");
            return;
        }

        System.out.println("\nIngrese los datos del producto:");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Tipo (LACTEO, CÁRNICO, FRUTA, ENLATADO): ");
        String tipo = scanner.nextLine().toUpperCase();

        if (!tipo.equals("LACTEO") && !tipo.equals("CÁRNICO") && !tipo.equals("FRUTA") && !tipo.equals("ENLATADO")) {
            System.out.println("Tipo de producto inválido. Debe ser LACTEO, CÁRNICO, FRUTA o ENLATADO.");
            return;
        }

        System.out.print("Fecha de Expiración (dd/mm/aaaa): ");
        String fechaExpiracion = scanner.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        System.out.print("Cantidad en inventario: ");
        int cantidad = scanner.nextInt();

        System.out.print("Precio unitario: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); 

        productos[totalProductos] = new Producto(codigo, nombre, tipo, fechaExpiracion, fabricante, cantidad, precio);
        totalProductos++;
        System.out.println("Producto registrado con éxito.");
    }

    public void listarProductos() {
        if (totalProductos == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\nListado de productos:");
        for (int i = 0; i < totalProductos; i++) {
            productos[i].mostrarInfo();
        }
    }

    public void mostrarDatosTienda() {
        System.out.println("\n--- Información de la Tienda ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("------------------------------------------");
    }

    public void menu() {
        int opcion;
        do {
	    System.out.println("===============UNIVERSIDAD EAN============================");
            System.out.println("==================================================");
            System.out.println("\nMenú:");
            System.out.println("1. Ver información de la tienda");
            System.out.println("2. Registrar un producto");
            System.out.println("3. Listar productos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    mostrarDatosTienda();
                    break;
                case 2:
                    registrarProducto();
                    break;
                case 3:
                    listarProductos();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        Tienda tienda = new Tienda("Tienda EAN", "Calle 43 No. 57 - 14", "(601)5400330");
        tienda.menu();
    }
}
