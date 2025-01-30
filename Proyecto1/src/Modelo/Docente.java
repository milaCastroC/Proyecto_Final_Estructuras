
package Modelo;

import Util.IList;
import Util.Listas;
import Util.Singleton;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Docente extends Persona implements Serializable{
    
    private String profesion;
    private IList<Curso>cursosActuales;
    private IList<Curso>historialCursos;

    public Docente(String profesion, String nombre, String id, String telefono, LocalDate fechaNacimiento, String nombreUsuario, String contrasena, String rol) {
        super(nombre, id, telefono, fechaNacimiento, nombreUsuario, contrasena, rol);
        this.profesion = profesion;
        this.cursosActuales = Singleton.getINSTANCIA().getCursos();
        this.historialCursos = Singleton.getINSTANCIA().getCursos();
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public IList<Curso> getCursosActuales() {
        Listas<Curso>cursos = (Listas<Curso>) Singleton.getINSTANCIA().getCursos();
        Listas<Curso>cursosActuales = new Listas<>();
        for(int i = 0; i < cursos.size(); i++){
            Curso curso = cursos.get(i);
            if(curso.getDocente().getId().equals(getId()) && curso.getEstado().equals(Curso.ACTIVO)){
                cursosActuales.agregar(curso);
            }
        }
        return cursosActuales;
    }

    public IList<Curso> getHistorialCursos() {
        Listas<Curso>cursos = (Listas<Curso>) Singleton.getINSTANCIA().getCursos();
        Listas<Curso>historial = new Listas<>();
        for(int i = 0; i < cursos.size(); i++){
            Curso curso = cursos.get(i);
            if(curso.getDocente().getId().equals(getId()) && curso.getEstado().equals(Curso.FINALIZADO)){
                historial.agregar(curso);
            }
        }
        return historial;
    }
 
    public String[] definirHorarioDia(DayOfWeek dia){
        Listas<Curso>cursosActuales = (Listas<Curso>) getCursosActuales();
        int clasesDiarias = 0;
        for(int i = 0; i < cursosActuales.size(); i++){
            Listas<Horario>horarios = (Listas<Horario>) cursosActuales.get(i).getHorarios();
            for(int j = 0; j < horarios.size(); j++){
                if(horarios.get(j).getDiaSemana().equals(dia)){
                    clasesDiarias += 1;
                }
            }
        }
        String[]horarioDia = new String[clasesDiarias];
        int contador = 0; 
        for(int i = 0; i < cursosActuales.size(); i++){
            Listas<Horario>horarios = (Listas<Horario>) cursosActuales.get(i).getHorarios();
            for(int j = 0; j < horarios.size(); j++){
                if(horarios.get(j).getDiaSemana().equals(dia)){
                    horarioDia[contador] = cursosActuales.get(i).getCodigoCurso()+"-"+cursosActuales.get(i).getNombre()+ "\n" +horarios.get(j).getHoraInicio()+"-"+horarios.get(j).getHoraFin();
                contador++;
                }
            }
        }
        return horarioDia;
    }
}
