
package Modelo;

import Excepciones.NotaInvalidaException;
import Util.IList;
import Util.Listas;
import Util.Singleton;
import excepciones.NotaNoEncontradaException;
import java.io.Serializable;

public class Nota implements Serializable{
    
    private String descripcion;
    private int porcentaje;
    private IList<DetalleNota> listaNotas;

    public Nota(String descripcion, int porcentaje) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.listaNotas = new Listas<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public IList<DetalleNota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(IList<DetalleNota> listaNotas) {
        this.listaNotas = listaNotas;
    }
    
    public void setEstudiantes(IList<Estudiante> estudiantesMatriculados){
        crearNotas(estudiantesMatriculados);
    }
    
    public void validarValorNota(double nota)throws NotaInvalidaException{
         if(nota < 0.0 || nota > 5.0){
            throw new NotaInvalidaException();
        }
    }
    public void crearNotas(IList<Estudiante> estudiantesMatriculados){
        for(int i = 0; i < estudiantesMatriculados.size(); i++){
            listaNotas.agregar(new DetalleNota(estudiantesMatriculados.get(i),0));
        }
        Singleton.getINSTANCIA().escribirCursos();
    }
    
    public DetalleNota obtenerNotaEstudiante(Estudiante estudiante){
        for(int i = 0; i < listaNotas.size(); i++){
            if(estudiante.getId().equals(listaNotas.get(i).getEstudiante().getId())){
                return  listaNotas.get(i);
            }
        }
        return null;
    }
    
    public void actualizarNota(DetalleNota notaAuxiliar) throws NotaInvalidaException, NotaNoEncontradaException{
        validarValorNota(notaAuxiliar.getValor());
        double valorAproximado = Math.round(notaAuxiliar.getValor() * 10.0) / 10.0;
        DetalleNota detalleNota = obtenerNotaEstudiante(notaAuxiliar.getEstudiante());
        detalleNota.setValor(valorAproximado);
        Singleton.getINSTANCIA().escribirCursos();
    }
    
     @Override
    public String toString() {
        return descripcion + " - (" + porcentaje+ "%)";
    }
}
