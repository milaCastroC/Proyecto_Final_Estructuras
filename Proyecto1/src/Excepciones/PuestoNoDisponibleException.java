
package Excepciones;

public class PuestoNoDisponibleException extends Exception{

    public PuestoNoDisponibleException() {
        super("El puesto se encuentra ocupado");
    } 
}
