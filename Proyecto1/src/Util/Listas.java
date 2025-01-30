package Util;

import java.io.Serializable;

public class Listas<T> implements IList<T>, Serializable {

    private Nodo<T> primerNodo;
    private int size;

    public Listas() {
        this.primerNodo = null;
        this.size = 0;
    }

    @Override
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        if (primerNodo == null) {
            primerNodo = nuevoNodo;
        } else {
            Nodo<T> observador = primerNodo;
            while (observador.nodoSig != null) {
                observador = observador.nodoSig;
            }
            observador.nodoSig = nuevoNodo;
        }
        size++;
    }

    @Override
    public void agregar(T dato, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index fuera de rango " + index);
        }
        Nodo<T> nodoNuevo = new Nodo<>(dato);
        if (index == 0) {
            nodoNuevo.nodoSig = primerNodo;
            primerNodo = nodoNuevo;
        } else {
            Nodo<T> observador = primerNodo;
            for (int i = 0; i < index - 1; i++) {
                observador = observador.nodoSig;
            }
            nodoNuevo.nodoSig = observador.nodoSig;
            observador.nodoSig = nodoNuevo;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index fuera de rango " + index);
        } else {
            if (index == 0) {
                return primerNodo.dato;
            } else {
                Nodo<T> observador = primerNodo;
                int contador = 0;
                while (contador < index) {
                    contador++;
                    observador = observador.nodoSig;
                }
                return observador.dato;
            }
        }
    }

    @Override
    public void eliminar(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index fuera de rango " + index);
        } else {
            if (index == 0) {
                primerNodo = primerNodo.nodoSig;
                size--;
            } else {
                Nodo<T> observador = primerNodo;
                for (int i = 0; i < index - 1; i++) {
                    observador = observador.nodoSig;
                }
                observador.nodoSig = observador.nodoSig.nodoSig;
            }
        }
        size--;
    }

    @Override
    public void eliminar(T dato) {
        if (primerNodo != null) {
            if (primerNodo.dato.equals(dato)) {
                primerNodo = primerNodo.nodoSig;
                size--;
            }else {
                Nodo<T> observador = primerNodo;
                while (observador.nodoSig != null && observador.nodoSig.dato != dato) {
                    observador = observador.nodoSig;
                }
                if (observador.nodoSig != null) {
                    observador.nodoSig = observador.nodoSig.nodoSig;
                    size--;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return primerNodo == null;
    }

}
