package Controladores;

import Modelo.Curso;
import Modelo.DetalleNota;
import Modelo.Estudiante;
import Modelo.Nota;
import Util.IList;

public class ControladorNotas {

    private Estudiante estudiante;

    public ControladorNotas(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public IList<Curso> getCursosActuales() {
        return estudiante.getCursosMatriculados();
    }

    public IList<Nota> obtenerNotas(Curso curso) {
        return curso.getNotasEstudiantes();
    }
    
    public DetalleNota obtenerValorNota(Nota nota){
        return nota.obtenerNotaEstudiante(estudiante);
    }

}
