package Controladores;

import Modelo.Curso;
import Modelo.Docente;
import Util.IList;
import Util.Singleton;

public class ControladorCursosDocente {

    Docente docente;

    public ControladorCursosDocente(Docente docente) {
        this.docente = docente;
    }

    public IList<Curso> getCursosActuales() {
        return docente.getCursosActuales();

    }
    
    public IList<Curso>getHistorialCursos(){
        return docente.getHistorialCursos();
    }
    
    public Curso obtenerCurso(String codigo){
        IList<Curso>cursos = Singleton.getINSTANCIA().getCursos();
         for (int i = 0; i < cursos.size(); i++) {
            if(cursos.get(i).getCodigoCurso().equals(codigo)){
                return cursos.get(i);
            }
        }
        return null;
    }

}
