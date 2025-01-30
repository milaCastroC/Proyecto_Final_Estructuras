package Controladores;

import Excepciones.EdadInvalidaException;
import Excepciones.NombreUsuarioRepetidoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Excepciones.UsuarioYaRegistradoException;
import Modelo.Administrativo;
import Modelo.Persona;
import Modelo.Usuario;
import Util.IList;
import Util.Singleton;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControladorGestionAdministrativo {

    private IList<Persona> usuarios;

    public ControladorGestionAdministrativo() {
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
    }

    public IList<Persona> getUsuarios() {
        return usuarios;
    }

    public void agregarAdministrativo(Administrativo administrativo) {
        usuarios.agregar(administrativo);
        Singleton.getINSTANCIA().escribirUsuarios();
    }

    public Administrativo buscarAdministrativo(String id) throws UsuarioInexistenteException, UsuarioNoCoincideException {
        for (int i = 0; i < usuarios.size(); i++) {
            Persona persona = usuarios.get(i);
            if (usuarios.get(i).getId().equals(id)) {
                if(persona instanceof Administrativo){
                    return (Administrativo) usuarios.get(i);
                }else{
                    throw new UsuarioNoCoincideException();
                } 
            }
        }
        throw new UsuarioInexistenteException();
    }

    public void eliminarAdministrativo(String id) throws UsuarioInexistenteException {
        boolean administrativoEliminado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.eliminar(usuarios.get(i));
                Singleton.getINSTANCIA().escribirUsuarios();
                administrativoEliminado = true;
                break;
            }
        }
        if (!administrativoEliminado) {
            throw new UsuarioInexistenteException();
        }
    }

    public void editarAdministrativo(String id, Administrativo administrativoActualizado) throws UsuarioInexistenteException {
        boolean administrativoEncontrado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                Administrativo administrativo = (Administrativo) usuarios.get(i);
                administrativo.setNombre(administrativoActualizado.getNombre());
                administrativo.setTelefono(administrativoActualizado.getTelefono());
                administrativo.setFechaNacimiento(administrativoActualizado.getFechaNacimiento());
                administrativo.setContrasena(administrativoActualizado.getContrasena());
                Singleton.getINSTANCIA().escribirUsuarios();
                administrativoEncontrado = true;
                break;
            }
        }
        if (!administrativoEncontrado) {
            throw new UsuarioInexistenteException();
        }
    }

    public void validarAdministrativo(String id, String nombreUsuario) throws UsuarioYaRegistradoException, NombreUsuarioRepetidoException {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                throw new UsuarioYaRegistradoException();
            }
            if(usuarios.get(i).getNombreUsuario().equals(nombreUsuario)){
                throw new NombreUsuarioRepetidoException();
            }
        }
    }
    
    public void calcularEdad(Administrativo administrativo)throws EdadInvalidaException {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = administrativo.getFechaNacimiento();
        administrativo.calcularEdad();
        if(fechaNacimiento.isAfter(fechaActual)){
            throw new EdadInvalidaException();
        }
    }
    
}
