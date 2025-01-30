
package Excepciones;

public class CursoYaMatriculadoException extends Exception{

    public CursoYaMatriculadoException() {
        super("El estudiante ya se encuentra matriculado en el curso");
    }
    
    
}
