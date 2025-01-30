
package Util;

import java.io.Serializable;

public class Nodo <T> implements Serializable{
    T dato;
    Nodo<T> nodoSig;

    public Nodo(T dato) {
        this.dato = dato;
        this.nodoSig = null;
    }
    
    
    
}
