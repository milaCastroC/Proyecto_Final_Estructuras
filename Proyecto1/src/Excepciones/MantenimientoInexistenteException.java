
package Excepciones;

public class MantenimientoInexistenteException extends Exception{

    public MantenimientoInexistenteException() {
        super("El mantenimiento no ha sido encontrado");
    }
    
}
