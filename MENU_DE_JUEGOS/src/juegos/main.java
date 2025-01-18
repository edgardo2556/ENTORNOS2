package juegos;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(" MENÚ DE JUEGOS ");
            System.out.println("1.Cuatro en línea ");
            System.out.println("2. Tres en linea");
            System.out.println("3. Piedra Papel o tijera");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Vamos a jugar , Cuatro en linea.");
                    Cuatro_en_linea.main(null);

                    break;
                case 2:
                    System.out.println("Vamos a jugar , Tres en linea.");
                    tres_en_linea.main(null);
                    break;
                case 3:
                    System.out.println("Piedra Papel o tijera");
                    tres_en_linea.main(null);
                    break;
                case 4:
                    System.out.println("Saliendo del menú. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 4.");
                    break;
            }
        } while (opcion != 4);


    }
}
