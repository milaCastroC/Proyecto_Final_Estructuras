
package Excepciones;

public class HorarioInvalidoException extends Exception{

    public HorarioInvalidoException() {
        super("La duración de la clase debe ser mínimo de 1 hora");
    }
    
    
}
