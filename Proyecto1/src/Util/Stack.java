
package Util;

public class Stack <T> implements IStack <T>{
    
    Nodo<T> primerNodo;

    @Override
    public void push(T dato) {
        Nodo<T> nodoNuevo = new Nodo<>(dato);
        if(isEmpty()){
            this.primerNodo = nodoNuevo;
        }else{
            nodoNuevo.nodoSig = primerNodo;
            primerNodo = nodoNuevo;
        }
    }

    @Override
    public T pop() {
       if(!isEmpty()){
           Nodo<T> observador = primerNodo;
           primerNodo = primerNodo.nodoSig;
           return observador.dato;
       }
       throw new RuntimeException("La pila está vacía");
    }

    @Override
    public T peek() {
       if(!isEmpty()){
           return primerNodo.dato;
       } 
       throw new RuntimeException("La pila está vacía");
    }

    @Override
    public boolean isEmpty() {
        return primerNodo == null;
    }
    
}

