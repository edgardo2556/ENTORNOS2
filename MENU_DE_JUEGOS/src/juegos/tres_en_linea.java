package juegos;

import java.util.Scanner;
import java.util.Random;

public class  tres_en_linea{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] tablero = new char[3][3];
        inicializarTablero(tablero);

        System.out.println("Esto es Tres en Línea");
        System.out.println("1. Jugar con otro un amigo?");
        System.out.println("2. Jugar contra una Inteligencia Artficial");
        System.out.print("Elige una opción: ");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            jugarConDosJugadores(tablero);
        } else if (opcion == 2) {
            jugarConIA(tablero);
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void inicializarTablero(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    private static void imprimirTablero(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("--+---+--");
        }
        System.out.println();
    }

    private static boolean esMovimientoValido(char[][] tablero, int fila, int columna) {
        return fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ';
    }

    private static boolean hayGanador(char[][] tablero, char jugador) {
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) ||
                    (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador)) {
                return true;
            }
        }
        return (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) ||
                (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador);
    }

    private static void jugarConDosJugadores(char[][] tablero) {
        Scanner scanner = new Scanner(System.in);
        char jugadorActual = 'X';
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            imprimirTablero(tablero);
            System.out.println("Turno del jugador " + jugadorActual);
            System.out.print("Elige una casilla (1-9): ");
            int eleccion = scanner.nextInt() - 1;
            int fila = eleccion / 3;
            int columna = eleccion % 3;

            if (esMovimientoValido(tablero, fila, columna)) {
                tablero[fila][columna] = jugadorActual;
                if (hayGanador(tablero, jugadorActual)) {
                    imprimirTablero(tablero);
                    System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                    juegoTerminado = true;
                } else if (esEmpate(tablero)) {
                    imprimirTablero(tablero);
                    System.out.println("¡Empate! El tablero está lleno.");
                    juegoTerminado = true;
                } else {
                    jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Movimiento no válido. Intenta de nuevo.");
            }
        }
    }

    private static void jugarConIA(char[][] tablero) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        char jugadorHumano = 'X';
        char jugadorMaquina = 'O';
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            imprimirTablero(tablero);
            System.out.println("Turno del jugador " + jugadorHumano);
            System.out.print("Elige una casilla (1-9): ");
            int eleccion = scanner.nextInt() - 1;
            int fila = eleccion / 3;
            int columna = eleccion % 3;

            if (esMovimientoValido(tablero, fila, columna)) {
                tablero[fila][columna] = jugadorHumano;
                if (hayGanador(tablero, jugadorHumano)) {
                    imprimirTablero(tablero);
                    System.out.println("¡El jugador " + jugadorHumano + " ha ganado!");
                    juegoTerminado = true;
                } else if (esEmpate(tablero)) {
                    imprimirTablero(tablero);
                    System.out.println("¡Empate! El tablero está lleno.");
                    juegoTerminado = true;
                } else {
                    System.out.println("Turno de la máquina " + jugadorMaquina);
                    int filaMaquina, columnaMaquina;
                    do {
                        filaMaquina = rand.nextInt(3);
                        columnaMaquina = rand.nextInt(3);
                    } while (!esMovimientoValido(tablero, filaMaquina, columnaMaquina));
                    tablero[filaMaquina][columnaMaquina] = jugadorMaquina;
                    System.out.println("La máquina elige la casilla " + (filaMaquina * 3 + columnaMaquina + 1));

                    if (hayGanador(tablero, jugadorMaquina)) {
                        imprimirTablero(tablero);
                        System.out.println("¡La máquina ha ganado!");
                        juegoTerminado = true;
                    } else if (esEmpate(tablero)) {
                        imprimirTablero(tablero);
                        System.out.println("¡Empate! El tablero está lleno.");
                        juegoTerminado = true;
                    }
                }
            } else {
                System.out.println("Movimiento no válido. Intenta de nuevo.");
            }
        }
    }

    private static boolean esEmpate(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
