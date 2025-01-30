
package Modelo;

import Util.IList;
import Util.Listas;
import java.io.Serializable;
import Util.Singleton;

public class Programa implements Serializable{
    
    private String nombre;
    private String codigo;
    private String facultad;
    private int semestres;
    private IList<Curso>curso;

    public Programa(String nombre, String codigo, String facultad, int semestres) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.facultad = facultad;
        this.semestres = semestres;
        this.curso = Singleton.getINSTANCIA().getCursos() ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public int getSemestres() {
        return semestres;
    }

    public void setSemestres(int semestres) {
        this.semestres = semestres;
    }

    public IList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(IList<Curso> curso) {
        this.curso = curso;
    }
    
    
}
