package Controladores;

import Excepciones.ContrasenaIncorrectaException;
import Excepciones.UsuarioInexistenteException;
import Modelo.Persona;
import Modelo.Usuario;
import Util.IList;
import Util.Singleton;

public class ControladorCambioContrasena {

    IList<Persona> usuarios;
    ControladorLogin controladorLogin;

    public ControladorCambioContrasena(){
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
        this.controladorLogin = new ControladorLogin();
    }

    public Usuario buscarUsuario(String nombreUsuario)throws UsuarioInexistenteException{
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuario)) {
                return usuarios.get(i);
            }
        }
        throw new UsuarioInexistenteException();
    }

    public boolean isPrimerInicio(String nombreUsuario, String contrasena) throws ContrasenaIncorrectaException, UsuarioInexistenteException {
        Usuario usuario = controladorLogin.inicioSesion(nombreUsuario, contrasena);
        if (usuario.isPrimerInicioSesion()) {
            return true;
        }
        return false;
    }

    public void cambiarContrasena(String nombreUsuario, String contrasena) throws UsuarioInexistenteException{
        Usuario usuario = buscarUsuario(nombreUsuario);
            if (usuario != null && usuario.isPrimerInicioSesion()) {
                usuario.setPrimerInicioSesion(false);
                usuario.setContrasena(contrasena);
                Singleton.getINSTANCIA().escribirUsuarios();
            }else{
                throw new UsuarioInexistenteException();
            }
    }
    

}
