
package Excepciones;

public class LaboratorioEnMantenimientoException extends Exception{

    public LaboratorioEnMantenimientoException() {
        super("El laboratorio se encuntra en mantenimiento para la fecha seleccionada");
    }
    
}
