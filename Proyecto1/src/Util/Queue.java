
package Util;

import java.io.Serializable;

public class Queue <T> implements IQueue<T>, Serializable{
    
     Nodo<T> primerNodo;

    public Queue() {
        this.primerNodo = null;
    }
    
    @Override
    public void enQueue(T dato) {
        Nodo<T> nodoNuevo = new Nodo<>(dato);
        if (isEmpty()) {
            this.primerNodo = nodoNuevo;
        } else {
            Nodo<T> observador = primerNodo;
            while (observador.nodoSig != null) {
                observador = observador.nodoSig;
            }
            observador.nodoSig = nodoNuevo;
        }
    }

    @Override
    public T deQueue(){
        if (!isEmpty()) {
            Nodo<T> nodoAuxiliar = primerNodo;
            primerNodo = primerNodo.nodoSig;
            return nodoAuxiliar.dato;
        }
        throw new RuntimeException("Cola vacía");
    }

    @Override
    public T peek() {
    if (!isEmpty()) {
            return primerNodo.dato;
        }
        throw new RuntimeException("Cola vacía");
    }

    @Override
    public boolean isEmpty() {
       return primerNodo == null;
    }
}
