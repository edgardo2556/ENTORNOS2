package juegos;

import java.util.Scanner;
import java.util.Random ;

public class Cuatro_en_linea {
    public static void main(String[] args) {
        char simbolo = 'X';
        System.out.println(" ");
        System.out.println(" CCCC   U   U   AAAAA  TTTTT  RRRR   OOOOO       EEEEE  N   N        L      IIIII  N   N  EEEEE   AAAAA ");
        System.out.println("C       U   U   A   A    T    R   R  O   O       E      NN  N        L        I    NN  N  E       A   A ");
        System.out.println("C       U   U   AAAAA    T    RRRR   O   O       EEEE   N N N        L        I    N N N  EEEE    AAAAA ");
        System.out.println("C       U   U   A   A    T    R  R   O   O       E      N  NN        L        I    N  NN  E       A   A ");
        System.out.println(" CCCC    UUU    A   A    T    R   R  OOOOO       EEEEE  N   N        LLLLL  IIIII  N   N  EEEEE   A   A ");
        System.out.println(" ");
        // Crear el tablero 6x7 (6 filas y 7 columnas)
        char[][] tablero = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tablero[i][j] = '.';
            }
        }
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String turno = "1";
        boolean juegoTerminado = false;

        // Comenzar el juego
        while (!juegoTerminado) {
            int columna;

            if (simbolo == 'X') {
                turno = "1";
            } else {
                turno = "2";
            }

            // Mostrar el tablero
            System.out.println();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
            // creo un if para tener los turnos del humano y de la maquina .
            if (turno.equals("1")) {
                System.out.println("Turno del jugador " + turno + " (" + simbolo + ")");
                System.out.print("Elige una columna (0-6): ");
                columna = scanner.nextInt();

                if (columna < 0 || columna > 6) {
                    System.out.println("Columna inválida. Elige un número entre 0 y 6.");
                    continue;
                }
            } else {
                System.out.println("Turno de la máquina (" + simbolo + ")");
                do { // BUCLE DO - WHILE PARA ASEGURARME QUE SE EJECUTE AL MENOS UNA VEZ
                    columna = random.nextInt(7); // Seleccionar una columna aleatoria entre 0 y 6
                } while (columna < 0 || columna > 6 || tablero[0][columna] != '.');
                System.out.println("La máquina elige la columna: " + columna);
            }

            boolean colocado = false;
            int filaColocada = -1;
            for (int fila = 5; fila >= 0 && !colocado; fila--) {
                if (tablero[fila][columna] == '.') {
                    tablero[fila][columna] = simbolo;
                    filaColocada = fila;
                    colocado = true;
                }
            }

            if (!colocado) {
                System.out.println("La columna está llena. Elige otra columna.");
                continue;
            }

            boolean ganador = false;

            // Verificar filas
            int contadorHorizontal = 0;
            for (int j = 0; j < 7; j++) {  // recorre toda la fila en la que se coloca una ficha desde la primera hasta la ultima
                if (tablero[filaColocada][j] == simbolo) {
                    contadorHorizontal++;
                } else {
                    contadorHorizontal = 0;
                }

                if (contadorHorizontal == 4) {
                    ganador = true;
                }
            }

            // Verificar columnas
            int contadorVertical = 0;
            for (int i = 0; i < 6; i++) {
                if (tablero[i][columna] == simbolo) { // recorre todas las columnas en la que se coloco una ficha  desde 0 a 5
                    contadorVertical++;
                } else {
                    contadorVertical = 0;
                }

                if (contadorVertical == 4) {
                    ganador = true;
                }
            }

            // Verificar diagonales ascendentes
            // Este bucle recorre una diagonal ascendente que empieza desde la posición donde se puso la ficha y avanza hacia la parte superior derecha del tablero
            int contadorDiagonalAscendente = 0;
            for (int i = filaColocada, j = columna; i >= 0 && j < 7; i--, j++) { // i decrece y j aumenta con cada iteracion
                if (tablero[i][j] == simbolo) {
                    contadorDiagonalAscendente++;
                } else {
                    contadorDiagonalAscendente = 0;
                }
                if (contadorDiagonalAscendente == 4) {
                    ganador = true;
                }
            }

            // Verificar diagonales descendentes
            int contadorDiagonalDescendente = 0;
            //Este bucle recorre una diagonal descendente iniciando desde la posición de la ficha colocada, avanzando hacia la parte inferior derecha del tablero

            for (int i = filaColocada, j = columna; i < 6 && j < 7; i++, j++) { // i y j aumentan con cada iteracion
                // el bulce para cuando i alcanza 6 y j al 7
                if (tablero[i][j] == simbolo) {
                    contadorDiagonalDescendente++;
                } else {
                    contadorDiagonalDescendente = 0;
                }
                if (contadorDiagonalDescendente == 4) {
                    ganador = true;
                }
            }

            int contadorDiagonalAscendenteDescendente = 0;
            // 1. La diagonal descendente hacia abajo a la izquierda, donde 'i' aumenta y 'j' disminuye.
            // 2. La diagonal ascendente hacia arriba a la derecha, donde 'i' disminuye y 'j' aumenta.
            // Ambas diagonales comparten el punto de inicio y el bucle las recorre en direcciones opuestas.
            for (int i = filaColocada, j = columna; i < 6 && j >= 0; i++, j--) {  // el bucle parq cuando i alcanza 6 y j es mayor o igual que 0 esto asegura que j no sea menor que 0.
                if (tablero[i][j] == simbolo) {
                    contadorDiagonalAscendenteDescendente++;
                } else {
                    contadorDiagonalAscendenteDescendente = 0;
                }
                if (contadorDiagonalAscendenteDescendente == 4) {
                    ganador = true;
                }
            }

            // Si hay ganador, mostrar el tablero final y terminar el juego
            if (ganador) {
                System.out.println("¡El jugador " + turno + " (" + simbolo + ") ha ganado!");
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        System.out.print(tablero[i][j] + " ");
                    }
                    System.out.println();
                }
                juegoTerminado = true;
            } else {
                if (simbolo == 'X') {
                    simbolo = 'O';
                } else {
                    simbolo = 'X';
                }
            }
        }
    }
}
