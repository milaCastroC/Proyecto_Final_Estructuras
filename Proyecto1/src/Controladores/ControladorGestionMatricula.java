
package Controladores;

import Excepciones.CursoInexistenteException;
import Excepciones.CursoYaMatriculadoException;
import Excepciones.DiaInvalidoException;
import Excepciones.EstudianteNoDisponibleException;
import Excepciones.HorarioNoDefinidoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Persona;
import Util.IList;
import Util.Listas;
import Util.Singleton;

public class ControladorGestionMatricula {
    
    private IList<Curso>cursos;

    public ControladorGestionMatricula() {
        this.cursos = Singleton.getINSTANCIA().getCursos();
    }

    public IList<Curso> getCursos() {
        return cursos;
    }

    public IList<Estudiante> getEstudiantes(String IdCurso) {
        try {
            return buscarCurso(IdCurso).getEstudiantes();
        } catch (CursoInexistenteException ex) {
        return new Listas<>();
        }
    }
    
    public void generarListaCursos(){
        for(int i = 0; i < cursos.size(); i++){
            if(cursos.get(i).getEstado().equals(Curso.ACTIVO)){
                cursos.agregar(cursos.get(i));
            }
        }
    }
    
    public Estudiante obtenerEstudiante(String id)throws UsuarioInexistenteException, UsuarioNoCoincideException{
        Listas<Persona> estudiantes = (Listas<Persona>) Singleton.getINSTANCIA().getUsuarios();
        for (int i = 0; i < estudiantes.size(); i++) {
            Persona usuario = estudiantes.get(i);
            if (usuario.getId().equals(id)) {
                if (usuario instanceof Estudiante) {
                    return (Estudiante) usuario;
                } else {
                    throw new UsuarioNoCoincideException();
                }
            }
        }
        throw new UsuarioInexistenteException();
    }
    
    public void matricularEstudiante(Curso curso, Estudiante estudiante) throws  CursoYaMatriculadoException, EstudianteNoDisponibleException, DiaInvalidoException, HorarioNoDefinidoException {
        curso.matricularEstudiante(estudiante,cursos);
    }
    
    public void cancelarMatriculaEstudiante(Curso curso, String idEstudiante)throws UsuarioInexistenteException, UsuarioNoCoincideException{
        curso.eliminarEstudianteCurso(idEstudiante);
    }
    
    public Curso buscarCurso(String codigo) throws CursoInexistenteException {
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            if (cursos.get(i).getCodigoCurso().equals(codigo)) {
                return curso;
            }
        }
        throw new CursoInexistenteException();
    }

}
