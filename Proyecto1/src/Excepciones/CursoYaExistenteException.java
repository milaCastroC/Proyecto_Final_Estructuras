
package Excepciones;

public class CursoYaExistenteException extends Exception {

    public CursoYaExistenteException() {
        super("El curso ya se encuentra registrado");
    }
    
    
}
