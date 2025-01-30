
package Excepciones;

public class FueraDeJornadaException extends Exception{

    public FueraDeJornadaException() {
        super("La hora o el día se encuntra fuera de la jornada");
    }
    
}
