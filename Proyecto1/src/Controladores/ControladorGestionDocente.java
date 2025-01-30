
package Controladores;

import Excepciones.EdadInvalidaException;
import Excepciones.NombreUsuarioRepetidoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Excepciones.UsuarioYaRegistradoException;
import Modelo.Docente;
import Modelo.Persona;
import Util.IList;
import Util.Singleton;
import java.time.LocalDate;

public class ControladorGestionDocente {
    
    private IList<Persona>usuarios;

    public ControladorGestionDocente() {
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
    }

    public IList<Persona> getUsuarios() {
        return usuarios;
    }
    
    public void agregarDocente(Docente docente){
        usuarios.agregar(docente);
        Singleton.getINSTANCIA().escribirUsuarios();
    }
    
    public Docente buscarDocente(String id)throws UsuarioInexistenteException, UsuarioNoCoincideException{
        for(int i = 0; i < usuarios.size(); i++){
            Persona persona = usuarios.get(i);
           if(usuarios.get(i).getId().equals(id)){
               if(persona instanceof Docente){
                   return (Docente) usuarios.get(i);
               }else{
                    throw new UsuarioNoCoincideException();
                } 
           }
        }
        throw new UsuarioInexistenteException();
    }
    
    public void eliminarDocente(String id)throws UsuarioInexistenteException{
        boolean docenteEliminado = false;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getId().equals(id)){
                usuarios.eliminar(usuarios.get(i));
                Singleton.getINSTANCIA().escribirUsuarios();
                docenteEliminado = true;
                break;
            }
        }
        if(!docenteEliminado){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void editarDocente(String id, Docente docenteActualizado)throws UsuarioInexistenteException{
        boolean docenteEncontrado = false;
        for(int i = 0; i < usuarios.size(); i ++){
            if(usuarios.get(i).getId().equals(id)){
                Docente docente = (Docente) usuarios.get(i);
                docente.setNombre(docenteActualizado.getNombre());
                docente.setTelefono(docenteActualizado.getTelefono());
                docente.setProfesion(docenteActualizado.getProfesion());
                docente.setFechaNacimiento(docenteActualizado.getFechaNacimiento());
                docente.setContrasena(docenteActualizado.getContrasena());
                Singleton.getINSTANCIA().escribirUsuarios();
                docenteEncontrado = true;
                break;
            }
        }
        if(!docenteEncontrado){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void validarDocente(String id, String nombreUsuario)throws UsuarioYaRegistradoException, NombreUsuarioRepetidoException {
        for(int i = 0; i < usuarios.size(); i ++){
            if(usuarios.get(i).getId().equals(id)){
                throw new UsuarioYaRegistradoException();
            }
            if(usuarios.get(i).getNombreUsuario().equals(nombreUsuario)){
                throw new NombreUsuarioRepetidoException();
            }
        }
    }
    
    public void CalcularEdad(Docente docente)throws EdadInvalidaException{
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = docente.getFechaNacimiento();
        docente.calcularEdad();
        if(fechaNacimiento.isAfter(fechaActual)){
            throw new EdadInvalidaException();
        }
    }

}
