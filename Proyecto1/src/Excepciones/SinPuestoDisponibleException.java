
package Excepciones;

public class SinPuestoDisponibleException extends Exception{

    public SinPuestoDisponibleException() {
        super("El laboratorio no tiene puestos disponibles en este momento");
    }
    
    
}
