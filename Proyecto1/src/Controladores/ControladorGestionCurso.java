package Controladores;

import Excepciones.CursoFinalizadoException;
import Excepciones.CursoInexistenteException;
import Excepciones.CursoYaExistenteException;
import Excepciones.DiaInvalidoException;
import Excepciones.DocenteNoDisponibleException;
import Excepciones.EstudianteNoDisponibleException;
import Excepciones.FueraDeJornadaException;
import Excepciones.HoraInvalidaException;
import Excepciones.HorarioInvalidoException;
import Excepciones.PilaVaciaException;
import Excepciones.ProgramaNoEncontradoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Modelo.Accion;
import Modelo.Curso;
import Modelo.Docente;
import Modelo.Horario;
import Modelo.Persona;
import Modelo.Programa;
import Modelo.Usuario;
import Util.IList;
import Util.IStack;
import Util.Listas;
import Util.Singleton;
import Util.Stack;

public class ControladorGestionCurso {

    private IList<Curso> cursos;
    private IList<Programa> programas;
    private IList<Persona> usuarios;
    private IStack<Accion>pilaDeshacer;
    private IStack<Accion>pilaRehacer;

    public ControladorGestionCurso() {
        this.cursos = Singleton.getINSTANCIA().getCursos();
        this.programas = Singleton.getINSTANCIA().getProgramas();
        this.usuarios = Singleton.getINSTANCIA().getUsuarios();
        this.pilaDeshacer = new Stack<>();
        this.pilaRehacer = new Stack<>();
    }

    public IList<Curso> getCursos() {
        return cursos;
    }

    public IList<Docente> getListaDocentes() {
        IList<Persona> usuarios = Singleton.getINSTANCIA().getUsuarios();
        IList<Docente> docentes = new Listas<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getRol().equals(Usuario.DOCENTE)) {
                docentes.agregar((Docente) usuarios.get(i));
            }
        }
        return docentes;
    }

    public IList<Programa> getListaProgramas() {
        return Singleton.getINSTANCIA().getProgramas();
    }

    public IList<Horario> getListaHorario(Curso curso) {
        return curso.getHorarios();
    }

    public Programa buscarPrograma(String codigo) throws ProgramaNoEncontradoException {
        for (int i = 0; i < programas.size(); i++) {
            if (programas.get(i).getCodigo().equals(codigo)) {
                return programas.get(i);
            }
        }
        throw new ProgramaNoEncontradoException();
    }

    public Docente buscarDocente(String id) throws UsuarioInexistenteException, UsuarioNoCoincideException {
        for (int i = 0; i < usuarios.size(); i++) {
            Persona persona = usuarios.get(i);
            if (usuarios.get(i).getId().equals(id)) {
                if (persona instanceof Docente) {
                    return (Docente) usuarios.get(i);
                } else {
                    throw new UsuarioNoCoincideException();
                }
            }
        }
        throw new UsuarioInexistenteException();
    }

    public void validarCurso(Curso curso) throws CursoYaExistenteException {
        for (int i = 0; i < cursos.size(); i++) {
            Curso cursoAux = cursos.get(i);
            if (curso.getCodigoCurso().equals(cursoAux.getCodigoCurso())) {
                throw new CursoYaExistenteException();
            }
            if (curso.getNombre().equals(cursoAux.getNombre()) && curso.getPrograma().equals(cursoAux.getPrograma()) && curso.getJornada().equals(cursoAux.getJornada()) && curso.getPeriodo().equals(cursoAux.getPeriodo())) {
                throw new CursoYaExistenteException();
            }
        }
    }
    

    //Gestión cursos
    public Curso buscarCurso(String codigo) throws CursoInexistenteException {
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            if (cursos.get(i).getCodigoCurso().equals(codigo)) {
                return curso;
            }
        }
        throw new CursoInexistenteException();
    }

    public void agregarCurso(Curso curso) throws CursoYaExistenteException {
        cursos.agregar(curso);
        Accion accion = new Accion(Accion.AGREGAR, curso);
        pilaDeshacer.push(accion);
        Singleton.getINSTANCIA().escribirCursos();
    }

    public void eliminarCurso(String codigo) throws CursoInexistenteException, CursoFinalizadoException {
        Curso cursoEliminado = buscarCurso(codigo);
        if(cursoEliminado.getEstado().equals(Curso.FINALIZADO)){
            throw new CursoFinalizadoException();
        }
        Accion accion = new Accion(Accion.ELIMINAR, cursoEliminado);
        pilaDeshacer.push(accion);
        cursos.eliminar(cursoEliminado);
        Singleton.getINSTANCIA().escribirCursos();
    }

    //Gestion horario
    public void agregarHorario(Curso curso, Horario horario) throws HoraInvalidaException, DocenteNoDisponibleException, DiaInvalidoException, FueraDeJornadaException, HorarioInvalidoException, EstudianteNoDisponibleException, CursoFinalizadoException{
        curso.agregarHorario(horario, cursos);
        if (curso.getEstado().equals(Curso.FINALIZADO)) {
            throw new CursoFinalizadoException();
        }
    }

    public void eliminarHorario(Curso curso, Horario horario) throws CursoFinalizadoException {
        curso.eliminarHorario(horario, cursos);
        if (curso.getEstado().equals(Curso.FINALIZADO)) {
            throw new CursoFinalizadoException();
        }
    }
    
    //Deshacer y rehacer
    public IStack<Accion> getPilaDeshacer() {
        return pilaDeshacer;
    }

    public IStack<Accion> getPilaRehacer() {
        return pilaRehacer;
    }

    public void deshacerAccion() throws PilaVaciaException {
        if (!pilaDeshacer.isEmpty()) {
            Accion accion = pilaDeshacer.pop();
            Curso curso = (Curso) accion.getObjeto();
            if (accion.getAccion().equals(Accion.AGREGAR)) {
                cursos.eliminar(curso);
            } else if (accion.getAccion().equals(Accion.ELIMINAR)) {
                cursos.agregar(curso);
            }
            pilaRehacer.push(accion);
            Singleton.getINSTANCIA().escribirCursos();
        } else {
            throw new PilaVaciaException();
        }
    }

    public void rehacerAccion() throws PilaVaciaException {
        if (!pilaRehacer.isEmpty()) {
            Accion accion = pilaRehacer.pop();
            Curso curso = (Curso) accion.getObjeto();
            if (accion.getAccion().equals(Accion.AGREGAR)) {
                cursos.agregar(curso);
            } else if (accion.getAccion().equals(Accion.ELIMINAR)) {
                cursos.eliminar(curso);
            }
            pilaDeshacer.push(accion);
            Singleton.getINSTANCIA().escribirCursos();
        } else {
            throw new PilaVaciaException();
        }
    }

}
