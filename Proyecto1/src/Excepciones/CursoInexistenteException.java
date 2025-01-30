
package Excepciones;

public class CursoInexistenteException extends Exception {

    public CursoInexistenteException() {
        super("El curso no ha sido encontrado");
    }
    
    
}
