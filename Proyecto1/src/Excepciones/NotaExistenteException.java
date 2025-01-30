
package Excepciones;

public class NotaExistenteException extends Exception{

    public NotaExistenteException() {
        super("La nota ya ha sido creada");
    }
    
}
