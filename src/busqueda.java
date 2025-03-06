/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminasdef;

/**
 *
 * @author Santiago, Fernando y Anthony
 */
public class Busqueda {
    public static void barrer(Tablero tablero, int x, int y, boolean usarBFS) {
        boolean[][] visitado = new boolean[tablero.getFilas()][tablero.getColumnas()];
        
        if (usarBFS) {
            barrerBFS(tablero, x, y, visitado);
        } else {
            barrerDFS(tablero, x, y, visitado);
        }
    }

    private static void barrerBFS(Tablero tablero, int x, int y, boolean[][] visitado) {
        Cola cola = new Cola();
        cola.encolar(new Nodo(x, y));
        visitado[x][y] = true;

        while (!cola.estaVacia()) {
            Nodo nodo = cola.desencolar();
            int i = nodo.x, j = nodo.y;

            if (tablero.contarMinasAdyacentes(i, j) == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = i + dx, ny = j + dy;
                        if (nx >= 0 && ny >= 0 && nx < tablero.getFilas() && ny < tablero.getColumnas()
                                && !visitado[nx][ny]) {
                            visitado[nx][ny] = true;
                            cola.encolar(new Nodo(nx, ny));
                        }
                    }
                }
            }
        }
    }

    private static void barrerDFS(Tablero tablero, int x, int y, boolean[][] visitado) {
        Pila pila = new Pila();
        pila.apilar(new Nodo(x, y));
        visitado[x][y] = true;

        while (!pila.estaVacia()) {
            Nodo nodo = pila.desapilar();
            int i = nodo.x, j = nodo.y;

            if (tablero.contarMinasAdyacentes(i, j) == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = i + dx, ny = j + dy;
                        if (nx >= 0 && ny >= 0 && nx < tablero.getFilas() && ny < tablero.getColumnas()
                                && !visitado[nx][ny]) {
                            visitado[nx][ny] = true;
                            pila.apilar(new Nodo(nx, ny));
                        }
                    }
                }
            }
        }
    }
}
