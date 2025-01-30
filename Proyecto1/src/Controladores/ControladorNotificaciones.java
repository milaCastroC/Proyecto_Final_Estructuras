
package Controladores;

import Excepciones.NotificacionNoEncontradaException;
import Modelo.Estudiante;
import Modelo.Notificacion;
import Util.IList;

public class ControladorNotificaciones {
    
    Estudiante estudiante;

    public ControladorNotificaciones(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public IList<Notificacion>getListaNotificaciones(){
        return estudiante.getNotificaciones();
    }
    
    public Notificacion getNotificacion(int identificador) throws NotificacionNoEncontradaException{
        for(int i = 0; i < estudiante.getNotificaciones().size(); i++){
            Notificacion notificacion = estudiante.getNotificaciones().get(i);
            if(notificacion.getIdentificador() == identificador){
                return notificacion;
            }
        }
        throw new NotificacionNoEncontradaException();
    }
}
