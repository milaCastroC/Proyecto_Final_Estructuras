
package Controladores;

import Excepciones.EdadInvalidaException;
import Excepciones.NombreUsuarioRepetidoException;
import Excepciones.ProgramaNoEncontradoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Excepciones.UsuarioYaRegistradoException;
import Modelo.Estudiante;
import Modelo.Persona;
import Modelo.Programa;
import Util.IList;
import Util.Singleton;
import java.time.LocalDate;

public class ControladorGestionEstudiante {
    
    private IList<Persona> usuarios;
    private IList<Programa>programas;
    
    public ControladorGestionEstudiante() {
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
        this.programas = Singleton.getINSTANCIA().getProgramas();
    }

    public IList<Persona> getUsuarios() {
        return usuarios;
    }

    public IList<Programa> getProgramas() {
        return programas;
    }
    
    public void agregarEstudiante(Estudiante estudiante){
        usuarios.agregar(estudiante);
        Singleton.getINSTANCIA().escribirUsuarios();
    }

    public Persona buscarEstudiante(String id) throws UsuarioInexistenteException, UsuarioNoCoincideException {
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
    
    public void eliminarEstudiante(String id)throws UsuarioInexistenteException{
        boolean estudianteEliminado = false;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getId().equals(id)){
                usuarios.eliminar(usuarios.get(i));
                Singleton.getINSTANCIA().escribirUsuarios();
                estudianteEliminado = true;
                break;
            }
        }
        if(!estudianteEliminado){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void editarEstudiante(String id, Estudiante estudianteActualizado)throws UsuarioInexistenteException{
        boolean estudianteEncontrado = false;
        for(int i = 0; i < usuarios.size(); i ++){
            if(usuarios.get(i).getId().equals(id)){
                Estudiante estudiante = (Estudiante) usuarios.get(i);
                estudiante.setNombre(estudianteActualizado.getNombre());
                estudiante.setTelefono(estudianteActualizado.getTelefono());
                estudiante.setPrograma(estudianteActualizado.getPrograma());
                estudiante.setSemestre(estudianteActualizado.getSemestre());
                estudiante.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
                estudiante.setContrasena(estudianteActualizado.getContrasena());
                Singleton.getINSTANCIA().escribirUsuarios();
                estudianteEncontrado = true;
                break;
            }
        }
        if(!estudianteEncontrado){
            throw new UsuarioInexistenteException();
        }
    }
    
    public void validarEstudiante(String id, String nombreUsuario)throws UsuarioYaRegistradoException, NombreUsuarioRepetidoException{
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getId().equals(id)){
                throw new UsuarioYaRegistradoException();
            }
            if(usuarios.get(i).getNombreUsuario().equals(nombreUsuario)){
                throw new NombreUsuarioRepetidoException();
            }
        }
    }
    
    public void calcularEdad(Estudiante estudiante)throws EdadInvalidaException{
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = estudiante.getFechaNacimiento();
        estudiante.calcularEdad();
        if(fechaNacimiento.isAfter(fechaActual)){
            throw new EdadInvalidaException();
        }
    }
    
    public Programa buscarPrograma(String codigo)throws ProgramaNoEncontradoException{
        for(int i = 0; i < programas.size(); i++){
            if(programas.get(i).getCodigo().equals(codigo)){
                return programas.get(i);
            }
        }
        throw new ProgramaNoEncontradoException();
    }
    
}
