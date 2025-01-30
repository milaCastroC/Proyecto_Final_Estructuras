
package Excepciones;

public class ContrasenaRepetidaException extends Exception {

    public ContrasenaRepetidaException() {
        super("La contraseña es repetida, por favor ingrese una diferente");
    }
    
}
