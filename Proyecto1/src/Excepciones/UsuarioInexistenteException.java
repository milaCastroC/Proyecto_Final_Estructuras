
package Excepciones;

public class UsuarioInexistenteException extends Exception{

    public UsuarioInexistenteException() {
        super("El usuario no ha sido encontrado");
    }

}
