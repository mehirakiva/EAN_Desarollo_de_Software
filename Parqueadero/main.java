import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Clase Vehiculo
    public static class Vehiculo {
        private String placa;
        private String marca;
        private String modelo;
        private LocalDateTime horaEntrada;

        public Vehiculo(String placa, String marca, String modelo, LocalDateTime horaEntrada) {
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
            this.horaEntrada = horaEntrada;
        }

        public String getPlaca() { return placa; }
        public void setPlaca(String placa) { this.placa = placa; }

        public String getMarca() { return marca; }
        public void setMarca(String marca) { this.marca = marca; }

        public String getModelo() { return modelo; }
        public void setModelo(String modelo) { this.modelo = modelo; }

        public LocalDateTime getHoraEntrada() { return horaEntrada; }
        public void setHoraEntrada(LocalDateTime horaEntrada) { this.horaEntrada = horaEntrada; }
    }

    // Clase Automovil
    public static class Automovil extends Vehiculo {
        private String tipoCombustible;

        public Automovil(String placa, String marca, String modelo, LocalDateTime horaEntrada, String tipoCombustible) {
            super(placa, marca, modelo, horaEntrada);
            this.tipoCombustible = tipoCombustible;
        }

        public String getTipoCombustible() { return tipoCombustible; }
        public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }
    }

    // Clase Motocicleta
    public static class Motocicleta extends Vehiculo {
        private int cilindraje;

        public Motocicleta(String placa, String marca, String modelo, LocalDateTime horaEntrada, int cilindraje) {
            super(placa, marca, modelo, horaEntrada);
            this.cilindraje = cilindraje;
        }

        public int getCilindraje() { return cilindraje; }
        public void setCilindraje(int cilindraje) { this.cilindraje = cilindraje; }
    }

    // Clase Camion
    public static class Camion extends Vehiculo {
        private double capacidadCarga;

        public Camion(String placa, String marca, String modelo, LocalDateTime horaEntrada, double capacidadCarga) {
            super(placa, marca, modelo, horaEntrada);
            this.capacidadCarga = capacidadCarga;
        }

        public double getCapacidadCarga() { return capacidadCarga; }
        public void setCapacidadCarga(double capacidadCarga) { this.capacidadCarga = capacidadCarga; }
    }

    // Clase Parqueadero
    public static class Parqueadero {
        private List<Vehiculo> vehiculos;
        private List<Vehiculo> vehiculosHistoricos;

        public Parqueadero() {
            vehiculos = new ArrayList<>();
            vehiculosHistoricos = new ArrayList<>();
        }

        public void registrarEntrada(Vehiculo vehiculo) {
            vehiculos.add(vehiculo);
            vehiculosHistoricos.add(vehiculo); // Guardamos los vehículos que entran para generar reporte
        }

        public double registrarSalida(String placa) {
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo.getPlaca().equals(placa)) {
                    LocalDateTime horaEntrada = vehiculo.getHoraEntrada();
                    LocalDateTime horaSalida = LocalDateTime.now();
                    long horasEstadia = Duration.between(horaEntrada, horaSalida).toHours();
                    if (horasEstadia == 0) horasEstadia = 1; // Si estuvo menos de una hora, se cobra como una hora

                    double costo = 0;

                    if (vehiculo instanceof Automovil) {
                        costo = horasEstadia * 5000;
                    } else if (vehiculo instanceof Motocicleta) {
                        costo = horasEstadia * 3000;
                    } else if (vehiculo instanceof Camion) {
                        costo = horasEstadia * 10000;
                    }

                    vehiculos.remove(vehiculo);
                    return costo;
                }
            }
            System.out.println("Error: El vehículo con placa " + placa + " no está en el parqueadero.");
            return 0; 
        }

        public List<Vehiculo> consultarEstado() {
            return vehiculos;
        }

        public void generarReporte() {
              System.out.println("===============================");
            System.out.println("Reporte de vehículos que han utilizado el parqueadero:");
              System.out.println("===============================");
            for (Vehiculo vehiculo : vehiculosHistoricos) {
                System.out.println("Placa: " + vehiculo.getPlaca() + ", Marca: " + vehiculo.getMarca() + ", Modelo: " + vehiculo.getModelo() + ", Hora de entrada: " + vehiculo.getHoraEntrada());
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero();

        while (true) {
            try {
                System.out.println("============= EAN UNIVERSIDAD PARQUEADERO ===============");
                System.out.println("");
                System.out.println("1. Ingresar vehículo");
                System.out.println("2. Registrar salida de vehículo");
                System.out.println("3. Consultar estado del parqueadero");
                System.out.println("4. Generar reporte de vehículos del día");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();  // Lee la opción seleccionada
                scanner.nextLine();  // Limpiar el buffer después de leer un número

                switch (opcion) {
                    case 1:
                        // Ingresar cada vehículo ojo
                        System.out.print("Placa: ");
                        String placa = scanner.nextLine();
                        System.out.print("Marca: ");
                        String marca = scanner.nextLine();
                        System.out.print("Modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.print("Tipo de vehículo (1. Automovil, 2. Motocicleta, 3. Camion): ");
                        int tipoVehiculo = scanner.nextInt();
                        scanner.nextLine(); 

                        LocalDateTime horaEntrada = LocalDateTime.now();

                        if (tipoVehiculo == 1) {
                            System.out.print("Tipo de combustible: ");
                            String tipoCombustible = scanner.nextLine();
                            Automovil automovil = new Automovil(placa, marca, modelo, horaEntrada, tipoCombustible);
                            parqueadero.registrarEntrada(automovil);
                        } else if (tipoVehiculo == 2) {
                            System.out.print("Cilindraje: ");
                            int cilindraje = scanner.nextInt();
                            scanner.nextLine(); 
                            Motocicleta motocicleta = new Motocicleta(placa, marca, modelo, horaEntrada, cilindraje);
                            parqueadero.registrarEntrada(motocicleta);
                        } else if (tipoVehiculo == 3) {
                            System.out.print("Capacidad de carga (toneladas): ");
                            double capacidadCarga = scanner.nextDouble();
                            scanner.nextLine(); 
                            Camion camion = new Camion(placa, marca, modelo, horaEntrada, capacidadCarga);
                            parqueadero.registrarEntrada(camion);
                        }
                        break;

                    case 2:
                        // Registrar salida de vehículo
                        System.out.print("Ingrese la placa del vehículo a registrar la salida: ");
                        String placaSalida = scanner.nextLine();
                        double costo = parqueadero.registrarSalida(placaSalida);
                        if (costo > 0) {
                             System.out.println("===============================");
                            System.out.println("Costo de salida: $" + costo);
                             System.out.println("===============================");
                        }
                        break;

                    case 3:
                        // Consultar estado del parqueadero
                        List<Vehiculo> estado = parqueadero.consultarEstado();
                        System.out.println("===============================");
                        System.out.println("Vehículos en el parqueadero:");
                        System.out.println("===============================");
                        for (Vehiculo vehiculo : estado) {
                            System.out.println(vehiculo.getPlaca() + " - " + vehiculo.getMarca() + " " + vehiculo.getModelo());
                        }
                        break;

                    case 4:
                        // Generar reporte de vehículos del día
                        parqueadero.generarReporte();
                        break;

                    case 5:
                        // Salir
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada. Intente de nuevo.");
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }
}
