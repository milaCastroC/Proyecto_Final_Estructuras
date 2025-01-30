
package Util;

public interface IList<T> {
    
    public void agregar(T dato);
        
    public void agregar(T dato,int index);
    
    public T get(int index);
    
    public void eliminar(int index);
    
    public void eliminar(T dato);
    
    public int size();
    
    public boolean isEmpty();
}
