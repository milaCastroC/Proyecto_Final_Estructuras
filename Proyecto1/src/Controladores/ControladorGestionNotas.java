package Controladores;

import Excepciones.CantidadNotasInvalidaException;
import Excepciones.NotaExistenteException;
import Excepciones.NotaInvalidaException;
import Excepciones.PorcentajeInvalidoException;
import Modelo.Curso;
import Modelo.DetalleNota;
import Modelo.Docente;
import Modelo.Estudiante;
import Modelo.Nota;
import Modelo.Persona;
import Modelo.Usuario;
import Util.IList;
import Util.Singleton;
import excepciones.NotaNoEncontradaException;

public class ControladorGestionNotas {
    
    private Docente docente;
    
    public ControladorGestionNotas(Docente docente) {
        this.docente = docente;
    }
    
    public Docente getDocente() {
        return docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    public IList<Curso> getListaCursosActivos() {
        return docente.getCursosActuales();
    }
    
    public IList<Nota> getListaNotas(Curso curso) {
        return curso.getNotasEstudiantes();
    }
    
    public int getCantNotas(Curso curso) {
        return curso.getNotasEstudiantes().size();
    }
    
    public IList<Estudiante> getEstudiantesMatriculados(Curso curso) {
        return curso.getEstudiantes();
    }
    
//    public IList<Nota> getListaNotas(Curso curso) {
//        return curso.getNotasEstudiantes();
//    }
    
    public void validarNotas(IList<Nota>notasEstudiantes, Curso curso) throws NotaInvalidaException, PorcentajeInvalidoException, NotaExistenteException{
        curso.validarPorcentaje(notasEstudiantes);
        curso.validarNotaExistente(notasEstudiantes);
    }
    
    public Estudiante obtenerEstudiante(String id){
        IList<Persona>usuarios = Singleton.getINSTANCIA().getUsuarios();
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuario = usuarios.get(i);
            if(usuario.getRol().equals(Usuario.ESTUDIANTE)){
                Estudiante estudiante = (Estudiante) usuario;
                if(estudiante.getId().equals(id)){
                    return estudiante;
                }
            }
        }
        return null;
    }
    
    public void agregarNota(Curso curso, String descripcion) throws CantidadNotasInvalidaException, NotaExistenteException {
        curso.agregarNota(descripcion);
    }
    
    public void eliminarNota(Curso curso, String descripcion) throws NotaNoEncontradaException{
        curso.eliminarNota(descripcion);
    }
    
    public DetalleNota obtenerNota(Estudiante estudiante, Nota nota){
        return nota.obtenerNotaEstudiante(estudiante);
    }
    
    public double obtnerNotaFinal(Estudiante estudiante, Curso curso){
        return curso.obtenerNotaFinal(estudiante);
    }
    
    public void actualizarNotas(Curso curso, String razon, DetalleNota notaAuxiliar) throws NotaNoEncontradaException, NotaInvalidaException{
        curso.actualizarDetalleNota(razon, notaAuxiliar);
    }
    
    public void actualizarNota(IList<Nota>notasEstudiantes, Curso curso) throws NotaInvalidaException, PorcentajeInvalidoException, NotaExistenteException, NotaNoEncontradaException{
        validarNotas(notasEstudiantes, curso);
        for(int i = 0; i < notasEstudiantes.size(); i++){
            curso.actualizarNota(notasEstudiantes.get(i), i);
        }
    }
    
}
