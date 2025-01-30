
package Modelo;

public class Accion <T>{
    
    private String accion;
    private T objeto;
    
    public static final String AGREGAR = "Agregar";
    public static final String ELIMINAR = "Eliminar";

    public Accion(String accion, T objeto) {
        this.accion = accion;
        this.objeto = objeto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
    
}
