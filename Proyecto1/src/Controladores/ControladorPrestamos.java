
package Controladores;

import Excepciones.PrestamoExistenteException;
import Excepciones.PrestamoInexistenteException;
import Excepciones.PuestoNoDisponibleException;
import Excepciones.ReservaProximaException;
import Excepciones.SinPuestoDisponibleException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Modelo.Estudiante;
import Modelo.Laboratorio;
import Modelo.Persona;
import Modelo.Puesto;
import Util.IList;
import Util.Singleton;

public class ControladorPrestamos {
    
    private Laboratorio laboratorio;
    
    public ControladorPrestamos(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
    
     public Persona buscarEstudiante(String id) throws UsuarioInexistenteException, UsuarioNoCoincideException {
        IList<Persona> usuarios = Singleton.getINSTANCIA().getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            Persona persona = usuarios.get(i);
            if (usuarios.get(i).getId().equals(id)) {
                if (persona instanceof Estudiante) {
                    return (Estudiante) usuarios.get(i);
                } else {
                    throw new UsuarioNoCoincideException();
                }
            }
        }
        throw new UsuarioInexistenteException();
    }

    public boolean verificarPrestamoActivo(String id) {
        Laboratorio[][] laboratorios = Singleton.getINSTANCIA().getLaboratorios();
        for (int i = 0; i < laboratorios.length; i++) {
            for (int j = 0; j < laboratorios[i].length; j++) {
                Laboratorio laboratorio = laboratorios[i][j];
                if (laboratorio != null) { 
                    if (laboratorio.verificarPrestamoActivoEnLaboratorio(id)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void agregarPrestamo(Estudiante estudiante, Puesto puesto)throws PrestamoExistenteException, ReservaProximaException, PuestoNoDisponibleException, SinPuestoDisponibleException{
        if(verificarPrestamoActivo(estudiante.getId())){
           throw new PrestamoExistenteException();
       } 
       puesto.actualizarEstado();
       try{
           laboratorio.buscarPuestoDisponible();
           if(puesto.getEstado().equals(Puesto.DISPONIBLE)){
               puesto.agregarPrestamo(estudiante);
           }else{
               throw new PuestoNoDisponibleException();
           }
       }catch(SinPuestoDisponibleException ex){
           laboratorio.anadirEstudianteACola(estudiante);
       }
       Singleton.getINSTANCIA().escribirLaboratorios();
    }
    
     public void finalizarPrestamo(Estudiante estudiante, Puesto puesto) throws PrestamoInexistenteException {
         if(verificarPrestamoActivo(estudiante.getId())){
             puesto.finalizarPrestamo();
         }else{
             throw new PrestamoInexistenteException();
        }
        Singleton.getINSTANCIA().escribirLaboratorios();
    }
     
     public boolean buscarPuestosDisponibles(Laboratorio laboratorio){
         try{
             laboratorio.buscarPuestoDisponible();
             return true;
         }catch(SinPuestoDisponibleException ex){
             return false;
         }
     }
     
}
