/*
 * Modelo Partida
 */
package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Partida {

    //Atributos
    private String[][] tablero;
    private int[] santa = new int[2];
    private int[] grinch = new int[2];
    private int turno;
    private ArrayList<String> piezas = new ArrayList(Arrays.asList("id", "ia", "ib", "ad", "bd"));

    //Constructores
    public Partida() {
        this(new String[5][5]);
    }

    public Partida(String[][] tablero) {
        this.tablero = tablero;
        this.rellenarTablero(tablero);
        turno = 0;

        //Posición Santa
        int posicionYSanta = new Random().nextInt(tablero.length);
        santa[0] = posicionYSanta;
        santa[1] = -1;

        //Posición Grinch
        int posicionYGrinch = new Random().nextInt(tablero.length);
        int difPosiciones = posicionYGrinch - posicionYSanta;//Variable que permite saber la diferencia entre las dos posiciones
        while (difPosiciones < 2 && difPosiciones > -2) {//Si la diferencia es menos de 2, calcula una nueva posición
            posicionYGrinch = new Random().nextInt(tablero.length);
            difPosiciones = posicionYGrinch - posicionYSanta;
        }
        grinch[0] = posicionYGrinch;
        grinch[1] = -1;
    }

    //Métodos
    /*Método que rellena el tablero(array bidimensional) con las piezas del ArrayList piezas
    habrá en cada columna cada una de las piezas desordenadas con respecto a la siguiente*/
    private void rellenarTablero(String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {//Recorre filas
            ArrayList<String> piezasAleatorias = new ArrayList(piezas);//Crea una lista con la lista de las piezas
            Collections.shuffle(piezasAleatorias);//Baraja la lista para poner las posiciones aleatorias
            for (int j = 0; j < tablero[i].length; j++) {//Recorre columnas
                tablero[j][i] = piezasAleatorias.get(j);//Añade los elementos de la lista barajados a cada posición del tablero
            }
        }
    }

    /*Método que cambia la pieza enviada a través de su posición 
      por la pieza que se encuentra debajo. Si la pieza enviada está
      en el límite de abajo, la cambia por la que se encuentra más arriba.*/
    public void cambiarPieza(int fila, int columna) {
        String posAuxiliar;//Variable auxiliar para el intercambio
        if (fila + 1 < tablero.length) {//Si la pieza a cambiar tiene una pieza abajo
            //Guardo la posición de la pieza que será suplantada por la pieza seleccionada
            posAuxiliar = tablero[fila + 1][columna];
            tablero[fila + 1][columna] = tablero[fila][columna];
        } else {//Si la pieza está en el límite del tablero
            //Guardo la posición de la pieza que será suplantada por la pieza seleccionada
            posAuxiliar = tablero[0][columna];
            tablero[0][columna] = tablero[fila][columna];
        }
        tablero[fila][columna] = posAuxiliar;
    }

    /*Método que mueve a santa según a donde apunta su dirección actual. Si la ficha siguiente
      a la suya (a la que apunta) apunta hacia santa, este seguirá avanzando hasta que 
      no pueda seguir más.*/
    public void moverSanta() {
        String piezaSiguiente;
        if (santa[1] == -1) {//Si santa no ha salido a la primera casilla aún
            piezaSiguiente = tablero[santa[0]][0];
            if (piezaSiguiente.equals("ia") || piezaSiguiente.equals("id") || piezaSiguiente.equals("ib")) {
                santa[1] = 0;
            }
        } else {//Si santa se encuentra en cualquier otra posición de la fila
            String piezaSanta = tablero[santa[0]][santa[1]];
            switch (piezaSanta) {
                case "id", "ad", "bd"://Si la pieza en la que se encuentra Santa es izquierda-derecha, arriba-derecha o abajo-derecha
                    if (santa[1] == 4) {//Si santa está en la última casilla
                        santa[1]++;//Avanza y finaliza la partida
                    } else {
                        piezaSiguiente = tablero[santa[0]][santa[1] + 1];
                        if (piezaSiguiente.equals("ia") || piezaSiguiente.equals("id") || piezaSiguiente.equals("ib")) {
                            santa[1]++;
                        }
                    }
                    break;
                case "ia"://Si la pieza en la que se encuentra Santa es izquierda-arriba
                    if (santa[0] == 0) {//Si santa está en la primera fila
                        piezaSiguiente = tablero[tablero.length - 1][santa[1]];//La ficha siguiente es de la misma columna pero de la última fila
                        if (piezaSiguiente.equals("bd")) {//Si la ficha siguiente de abajo-derecha
                            santa[0] = tablero.length - 1;
                        }
                    } else {//Si santa está en cualquier otra fila
                        piezaSiguiente = tablero[santa[0] - 1][santa[1]];//La ficha siguiente es la de arriba de santa
                        if (piezaSiguiente.equals("bd")) {//Si la ficha siguiente de abajo-derecha
                            santa[0]--;
                        }
                    }
                    break;
                case "ib"://Si la pieza en la que se encuentra Santa es izquierda-abajo
                    if (santa[0] == tablero.length - 1) {//Si santa está en la última fila
                        piezaSiguiente = tablero[0][santa[1]];//La ficha siguiente es de la misma columna pero de la primera fila
                        if (piezaSiguiente.equals("ad")) {//Si la ficha siguiente de arriba-derecha
                            santa[0] = 0;
                        }
                    } else {//Si santa está en cualquier otra fila
                        piezaSiguiente = tablero[santa[0] + 1][santa[1]];//La ficha siguiente es la de abajo de santa
                        if (piezaSiguiente.equals("ad")) {//Si la ficha siguiente de arriba-derecha
                            santa[0]++;
                        }
                    }
                    break;
                default://Checkea que no haya error con el valor de la pieza
                    System.err.println("La pieza actual no tiene ningun valor válido.");
                    break;
            }
        }
        turno++;//Avanza en uno el turno
    }

    /* Método que hará mover al Grinch cada dos turnos */
    public void moverGrinch() {
        //TODO dificultad de Grinch
        if (turno > 2 ) {//Cada dos turnos y sin contar cuando carga la página
            if (grinch[1] == -1) {//Si es el primer movimiento del Grinch
                grinch[1]++;//Se desplaza hacia la derecha
                return;//Termina el movimiento del grinch
            }

            int difPosFila = santa[0] - grinch[0];//Diferencia de filas entre Santa y Grinch
            int difPosColumna = santa[1] - grinch[1];//Diferencia de columna entre Santa y Grinch

            if (difPosColumna == 0) {//Si el grinch se encuentra en la misma columna
                grinch[0] = difPosFila > 0 ? grinch[0] + 1 : grinch[0] - 1;//Se mueve en fila
            } else if (difPosFila == 0) {//Si el grinch se encuentra en la misma fila
                grinch[1] = difPosColumna > 0 ? grinch[1] + 1 : grinch[1] - 1;//Se mueve en columna
            } else {//Si no se encuentra ni en la misma fila ni en la misma columna
                Random random = new Random();
                int movimientoAleatorio = random.nextInt(2);//Variable para moverse en un eje o en otro de forma aleatoria
                if (movimientoAleatorio == 0) {
                    grinch[0] = difPosFila > 0 ? grinch[0] + 1 : grinch[0] - 1;//Se mueve en fila
                } else {
                    grinch[1] = difPosColumna > 0 ? grinch[1] + 1 : grinch[1] - 1;//Se mueve en columna
                }
            }

        }
    }

    /* Método que comprueba el estado de las posiciones de Santa y el Grinch */
    public int comprobarEstado() {
        if (santa[1] == tablero.length) {//Si santa gana
            return 1;
        } else if (santa[0] == grinch[0] && santa[1] == grinch[1]) {
            return -1;
        } else {
            return 0;
        }
    }

    //Getter y setter
    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public int[] getSanta() {
        return santa;
    }

    public void setSanta(int[] santa) {
        this.santa = santa;
    }

    public int[] getGrinch() {
        return grinch;
    }

    public void setGrinch(int[] grinch) {
        this.grinch = grinch;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

}
