package Controladores;

import Excepciones.UsuarioNoCoincideException;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Laboratorio;
import Modelo.Usuario;
import Util.IList;
import Util.Singleton;
import java.time.DayOfWeek;

public class ControladorEstudiante {

    Usuario usuario;

    public ControladorEstudiante(Usuario usuario) {
        this.usuario = usuario;
        validarEstadoEstudiante((Estudiante) usuario);
        verificarEstadoReservaEstudiante((Estudiante) usuario);
    }

    public IList<Curso> cursosActuales() throws UsuarioNoCoincideException {
        if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
            Estudiante estudiante = (Estudiante) usuario;
            return estudiante.getCursosMatriculados();
        } else {
            throw new UsuarioNoCoincideException();
        }
    }

    public String[] getHorario(DayOfWeek dia) throws UsuarioNoCoincideException {
        if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
            Estudiante estudiante = (Estudiante) usuario;
            return estudiante.definirHorarioDia(dia);
        } else {
            throw new UsuarioNoCoincideException();
        }
    }

    public static void validarEstadoEstudiante(Estudiante estudiante) {
        Laboratorio[][] laboratorio = Singleton.getINSTANCIA().getLaboratorios();
        if (laboratorio[0][0] != null) {
            for (int i = 0; i < laboratorio.length; i++) {
                for (int j = 0; j < laboratorio[i].length; j++) {
                    laboratorio[i][j].actualizarReservas(estudiante);
                }
            }
        }
    }

    public void verificarEstadoReservaEstudiante(Estudiante estudiante) {
        Laboratorio[][] laboratorio = Singleton.getINSTANCIA().getLaboratorios();
        boolean tieneReserva = false;
        if (laboratorio[0][0] != null) {
            for (int i = 0; i < laboratorio.length; i++) {
                for (int j = 0; j < laboratorio[i].length; j++) {
                    if (laboratorio[i][j].tieneReservaEstudiante(estudiante)) {
                        tieneReserva = true;
                        break;
                    }
                }
                if (tieneReserva) {
                    break;
                }
            }

            if (!tieneReserva) {
                estudiante.setTieneReserva(Estudiante.SIN_RESERVA);
                Singleton.getINSTANCIA().escribirUsuarios();
            }
        }

    }
}
