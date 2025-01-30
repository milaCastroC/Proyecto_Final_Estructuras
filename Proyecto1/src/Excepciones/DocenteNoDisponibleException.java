
package Excepciones;

public class DocenteNoDisponibleException extends Exception{

    public DocenteNoDisponibleException() {
        super("El docente no se encuentra disponible");
    }
    
    
}
