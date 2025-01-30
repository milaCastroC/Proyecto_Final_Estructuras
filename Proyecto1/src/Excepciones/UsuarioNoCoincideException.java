
package Excepciones;

public class UsuarioNoCoincideException extends Exception{

    public UsuarioNoCoincideException() {
        super("No coincide el rol de la persona buscada");
    }
    
}
