
package Controladores;

import Excepciones.EdadInvalidaException;
import Excepciones.NombreUsuarioRepetidoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Excepciones.UsuarioYaRegistradoException;
import Modelo.AdministradorLaboratorio;
import Modelo.Persona;
import Util.IList;
import Util.Singleton;
import java.time.LocalDate;

public class ControladorGestionAdministradorLaboratorio {
    
    private IList<Persona>usuarios;

    public ControladorGestionAdministradorLaboratorio() {
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
    }

    public IList<Persona> getUsuarios() {
        return usuarios;
    }
    
    public void agregarAdminLab(AdministradorLaboratorio adminLab){
        usuarios.agregar(adminLab);
        Singleton.getINSTANCIA().escribirUsuarios();
    }
    
    public AdministradorLaboratorio buscarAdminLab(String id)throws UsuarioInexistenteException, UsuarioNoCoincideException{
        for(int i = 0; i < usuarios.size(); i++){
            Persona persona = usuarios.get(i);
            if(usuarios.get(i).getId().equals(id)){
               if(persona instanceof AdministradorLaboratorio){
                    return (AdministradorLaboratorio)usuarios.get(i);
                }else{
                    throw new UsuarioNoCoincideException();
                } 
            }
        }
        throw new UsuarioInexistenteException();
    }
    
    public void eliminarAdminLab(String id)throws UsuarioInexistenteException{
        boolean adminLabEliminado = false;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getId().equals(id)){
                usuarios.eliminar(usuarios.get(i));
                Singleton.getINSTANCIA().escribirUsuarios();
                adminLabEliminado = true;
                break;               
            }
        }
        if(!adminLabEliminado){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void editarAdminLab(String id, AdministradorLaboratorio adminLabActualizado)throws UsuarioInexistenteException{
        boolean adminLabEncontrado = false;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getId().equals(id)){
                AdministradorLaboratorio adminLab = (AdministradorLaboratorio) usuarios.get(i);
                adminLab.setNombre(adminLabActualizado.getNombre());
                adminLab.setTelefono(adminLabActualizado.getTelefono());
                adminLab.setFechaNacimiento(adminLabActualizado.getFechaNacimiento());
                adminLab.setContrasena(adminLabActualizado.getContrasena());
                Singleton.getINSTANCIA().escribirUsuarios();
                adminLabEncontrado = true;
                break;
            }
        }
        if(!adminLabEncontrado){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void validarAdminLab(String id, String nombreUsuario)throws UsuarioYaRegistradoException, NombreUsuarioRepetidoException{
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getId().equals(id)){
                throw new UsuarioYaRegistradoException();
            }
            if(usuarios.get(i).getNombreUsuario().equals(nombreUsuario)){
                throw new NombreUsuarioRepetidoException();
            }
        }
    }
    
    public void calcularEdad(AdministradorLaboratorio adminLab)throws EdadInvalidaException{
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = adminLab.getFechaNacimiento();
        adminLab.calcularEdad();
        if(fechaNacimiento.isAfter(fechaActual)){
            throw new EdadInvalidaException();
        }
    }

}
