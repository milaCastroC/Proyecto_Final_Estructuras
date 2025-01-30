
package Excepciones;

public class ProgramaYaRegistradoException extends Exception {

    public ProgramaYaRegistradoException() {
        super("El programa ya se encuentra registrado");
    }
    
}
