/*
 * Clase pruebas
 */
package pruebas;

import java.util.Arrays;
import java.util.Scanner;
import modelo.Partida;

/**
 *
 * @author Carlos
 */
public class pruebas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Partida partida = new Partida();
        mostrarTabla(partida);

        System.out.println("Posición santa" + Arrays.toString(partida.getSanta()));
        System.out.println("Posición Grinch" + Arrays.toString(partida.getGrinch()));
        while (partida.getSanta()[1] < partida.getTablero().length) {
            System.out.println("Turno " + partida.getTurno());
            System.out.println("Posicion x a mover");
            int x = sc.nextInt();
            System.out.println("Posición y a mover:");
            int y = sc.nextInt();
            partida.cambiarPieza(y, x);
            partida.moverSanta();
            partida.moverGrinch();
            System.out.println("Posición santa" + Arrays.toString(partida.getSanta()));
            System.out.println("Posición Grinch" + Arrays.toString(partida.getGrinch()));
            mostrarTabla(partida);
            partida.comprobarEstado();
        }
    }

    public static void mostrarTabla(Partida partida) {
        for (int fila = 0; fila < partida.getTablero().length; fila++) {
            System.out.println(Arrays.toString(partida.getTablero()[fila]));
        }
    }
}
