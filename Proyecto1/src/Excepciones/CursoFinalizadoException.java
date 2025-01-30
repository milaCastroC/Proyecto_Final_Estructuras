package Excepciones;

public class CursoFinalizadoException extends Exception {

    public CursoFinalizadoException() {
        super("El curso ha finalizado por lo tanto no se puede modificar");
    }

}
