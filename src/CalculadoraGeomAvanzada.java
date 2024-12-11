import java.util.ArrayList;
import java.util.Scanner;

public class CalculadoraGeomAvanzada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> resultados = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            int figura = 0;
            do {
                mostrarMenuFiguras();
                figura = leerEntero(scanner, "Seleccione una figura (1-6): ");
                if (figura < 1 || figura > 6) {
                    System.out.println("Error: Debe seleccionar una figura entre 1 y 6.");
                }
            } while (figura < 1 || figura > 6);

            int operacion = 0;
            do {
                mostrarMenuOperaciones();
                operacion = leerEntero(scanner, "Seleccione una operación (1-2): ");
                if (operacion < 1 || operacion > 2) {
                    System.out.println("Error: Debe seleccionar una operación válida (1 o 2).");
                }
            } while (operacion < 1 || operacion > 2);

            double resultado = realizarOperacion(figura, operacion, scanner);
            if (resultado != -1) {
                resultados.add(resultado);
                System.out.println("El resultado de la operación es: " + resultado);
            }

            continuar = validarContinuar(scanner);
        }

        System.out.println("\nResultados almacenados:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("Operación " + (i + 1) + ": " + resultados.get(i));
        }

        scanner.close();
    }

    public static void mostrarMenuFiguras() {
        System.out.println("\nSeleccione la figura geométrica:");
        System.out.println("1. Círculo");
        System.out.println("2. Cuadrado");
        System.out.println("3. Triángulo");
        System.out.println("4. Rectángulo");
        System.out.println("5. Pentágono");
        System.out.println("6. Potencia");
    }

    public static void mostrarMenuOperaciones() {
        System.out.println("\nSeleccione la operación:");
        System.out.println("1. Área");
        System.out.println("2. Perímetro");
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        int valor;
        while (true) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida, inténtelo de nuevo.");
                scanner.next(); // Limpiar entrada
            }
        }
        return valor;
    }

    public static double leerDouble(Scanner scanner, String mensaje) {
        double valor;
        while (true) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida, inténtelo de nuevo.");
                scanner.next(); // Limpiar entrada
            }
        }
        return valor;
    }

    public static double realizarOperacion(int figura, int operacion, Scanner scanner) {
        double resultado = -1;

        switch (figura) {
            case 1: // Círculo
                double radio = leerDouble(scanner, "Ingrese el radio del círculo: ");
                resultado = (operacion == 1) ? Math.PI * Math.pow(radio, 2) : 2 * Math.PI * radio;
                break;
            case 2: // Cuadrado
                double ladoCuadrado = leerDouble(scanner, "Ingrese el lado del cuadrado: ");
                resultado = (operacion == 1) ? Math.pow(ladoCuadrado, 2) : 4 * ladoCuadrado;
                break;
            case 3: // Triángulo
                double base = leerDouble(scanner, "Ingrese la base del triángulo: ");
                double altura = leerDouble(scanner, "Ingrese la altura del triángulo: ");
                if (operacion == 1) {
                    resultado = (base * altura) / 2;
                } else if (operacion == 2) {
                    double lado1 = leerDouble(scanner, "Ingrese el primer lado del triángulo: ");
                    double lado2 = leerDouble(scanner, "Ingrese el segundo lado del triángulo: ");
                    resultado = base + lado1 + lado2;
                }
                break;
            case 4: // Rectángulo
                double largo = leerDouble(scanner, "Ingrese el largo del rectángulo: ");
                double ancho = leerDouble(scanner, "Ingrese el ancho del rectángulo: ");
                resultado = (operacion == 1) ? largo * ancho : 2 * (largo + ancho);
                break;
            case 5: // Pentágono
                double ladoPentagono = leerDouble(scanner, "Ingrese el lado del pentágono: ");
                if (operacion == 1) {
                    resultado = (5 * Math.pow(ladoPentagono, 2)) / (4 * Math.tan(Math.PI / 5));
                } else if (operacion == 2) {
                    resultado = 5 * ladoPentagono;
                }
                break;
            case 6: // Potencia
                double basePotencia = leerDouble(scanner, "Ingrese la base: ");
                int exponente = leerEntero(scanner, "Ingrese el exponente: ");
                resultado = calcularPotencia(basePotencia, exponente);
                break;
            default:
                System.out.println("Figura no válida.");
                break;
        }

        return resultado;
    }

    public static double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        }
        return base * calcularPotencia(base, exponente - 1);
    }

    public static boolean validarContinuar(Scanner scanner) {
        while (true) {
            System.out.print("¿Desea realizar otra operación? (si/no): ");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("si")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Respuesta inválida, inténtelo de nuevo.");
            }
        }
    }
}
