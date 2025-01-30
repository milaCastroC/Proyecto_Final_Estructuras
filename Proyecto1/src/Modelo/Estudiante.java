package Modelo;

import Util.IList;
import Util.Listas;
import Util.Singleton;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Estudiante extends Persona implements Serializable {

    public static final String RESERVA_ACTIVA = "Reserva Activa";
    public static final String SIN_RESERVA = "Sin Reserva";
    
    private Programa programa;
    private String semestre;
    private String tieneReserva;
    private IList<Curso> cursosMatriculados;
    private IList<Nota> notas;
    private IList<Notificacion>notificaciones;

    public Estudiante(Programa programa, String semestre, String nombre, String id, String telefono, LocalDate fechaNacimiento, String nombreUsuario, String contrasena, String rol) {
        super(nombre, id, telefono, fechaNacimiento, nombreUsuario, contrasena, rol);
        this.programa = programa;
        this.semestre = semestre;
        this.cursosMatriculados = Singleton.getINSTANCIA().getCursos();
        this.notas = new Listas<>();
        this.tieneReserva = SIN_RESERVA;
        this.notificaciones = new Listas<>();
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String isTieneReserva() {
        return tieneReserva;
    }

    public void setTieneReserva(String tieneReserva) {
        this.tieneReserva = tieneReserva;
    }
    
    public IList<Curso> getCursosMatriculados() {
        Listas<Curso> cursos = (Listas<Curso>) Singleton.getINSTANCIA().getCursos();
        Listas<Curso> cursosActuales = new Listas<>();
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            Listas<Estudiante> estudiantesMatriculados = (Listas<Estudiante>) curso.getEstudiantes();
            for (int j = 0; j < estudiantesMatriculados.size(); j++) {
                if (estudiantesMatriculados.get(j).getId().equals(getId()) && curso.getEstado().equals(Curso.ACTIVO)) {
                    cursosActuales.agregar(curso);
                }
            }
        }
        return cursosActuales;
    }
    
    public IList<Curso> getHistorialCursos() {
        Listas<Curso> cursos = (Listas<Curso>) Singleton.getINSTANCIA().getCursos();
        Listas<Curso> historial = new Listas<>();
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            Listas<Estudiante> estudiantesMatriculados = (Listas<Estudiante>) curso.getEstudiantes();
            for (int j = 0; j < estudiantesMatriculados.size(); j++) {
                if (estudiantesMatriculados.get(j).getId().equals(getId()) && curso.getEstado().equals(Curso.FINALIZADO)) {
                    historial.agregar(curso);
                }
            }
        }
        return historial;
    }

    public IList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(IList<Nota> notas) {
        this.notas = notas;
    }

    public IList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(IList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
    
    

    @Override
    public String toString() {
        return programa.getNombre();
    }

    public String[] definirHorarioDia(DayOfWeek dia) {
        Listas<Curso> cursosActuales = (Listas<Curso>) getCursosMatriculados();
        int clasesDiarias = 0;
        for (int i = 0; i < cursosActuales.size(); i++) {
            Listas<Horario> horarios = (Listas<Horario>) cursosActuales.get(i).getHorarios();
            for (int j = 0; j < horarios.size(); j++) {
                if (horarios.get(j).getDiaSemana().equals(dia)) {
                    clasesDiarias += 1;
                }
            }
        }
        String[] horarioDia = new String[clasesDiarias];
        int contador = 0;
        for (int i = 0; i < cursosActuales.size(); i++) {
            Listas<Horario> horarios = (Listas<Horario>) cursosActuales.get(i).getHorarios();
            for (int j = 0; j < horarios.size(); j++) {
                if (horarios.get(j).getDiaSemana().equals(dia)) {
                    horarioDia[contador] = cursosActuales.get(i).getCodigoCurso() + "-" + cursosActuales.get(i).getNombre() + "\n" + horarios.get(j).getHoraInicio() + "-" + horarios.get(j).getHoraFin();
                    contador++;
                }
            }
        }
        return horarioDia;
    }
    
    public void agregarNotificacion(Notificacion notificacion){
        if(notificaciones.isEmpty()){
            notificacion.setIdentificador(1);
        }else{
            notificacion.setIdentificador(notificaciones.size()+1);
        }
        notificaciones.agregar(notificacion);
        Singleton.getINSTANCIA().escribirUsuarios();
    }

}
