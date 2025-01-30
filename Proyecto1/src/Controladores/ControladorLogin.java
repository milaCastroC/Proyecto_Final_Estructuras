package Controladores;

import Excepciones.ContrasenaIncorrectaException;
import Excepciones.UsuarioInexistenteException;
import Modelo.AdministradorGeneral;
import Modelo.Persona;
import Modelo.Usuario;
import Util.IList;
import Util.Singleton;

public class ControladorLogin {

    IList<Persona> usuarios;

    public ControladorLogin() {
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
    }

    public Usuario inicioSesion(String nombreUsuario, String contrasena) throws ContrasenaIncorrectaException, UsuarioInexistenteException {
        Usuario administradorGeneral = AdministradorGeneral.getINSTANCE();
        if (administradorGeneral.getNombreUsuario().equals(nombreUsuario) && administradorGeneral.getContrasena().equals(contrasena)) {
            return administradorGeneral;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                if (usuario.getContrasena().equals(contrasena)) {
                    return usuario;
                } else {
                    throw new ContrasenaIncorrectaException();
                }
            }
        }
        throw new UsuarioInexistenteException();
    }

    public Usuario buscarUsuario(String nombreUsuario) throws UsuarioInexistenteException {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuario)) {
                return usuarios.get(i);
            }
        }
        throw new UsuarioInexistenteException();
    }

    public boolean isPrimerInicio(String nombreUsuario, String contrasena) throws ContrasenaIncorrectaException, UsuarioInexistenteException {
        Usuario usuario = inicioSesion(nombreUsuario, contrasena);
        if (usuario.isPrimerInicioSesion()) {
            return true;
        }
        return false;
    }

    public void cambiarContrasena(String nombreUsuario, String contrasena) throws UsuarioInexistenteException {
        Usuario usuario = buscarUsuario(nombreUsuario);
        if (usuario != null) {
            if (usuario.isPrimerInicioSesion()) {
                usuario.setPrimerInicioSesion(false);
                usuario.setContrasena(contrasena);
            }
        } else {
            throw new UsuarioInexistenteException();
        }

    }
    
    
}
