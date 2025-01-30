
package Controladores;

import Modelo.Curso;
import Modelo.Estudiante;
import Util.IList;

public class ControladorCursosEstudiante {
    
    Estudiante estudiante;

    public ControladorCursosEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public IList<Curso> getCursosActuales() {
        return estudiante.getCursosMatriculados();

    }
    
    public IList<Curso>getHistorialCursos(){
        return estudiante.getHistorialCursos();
    }
}
