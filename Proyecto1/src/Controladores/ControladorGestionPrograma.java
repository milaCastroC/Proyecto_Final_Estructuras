package Controladores;

import Excepciones.ProgramaNoEncontradoException;
import Excepciones.ProgramaYaRegistradoException;
import Modelo.Programa;
import Util.IList;
import Util.Singleton;

public class ControladorGestionPrograma {

    private IList<Programa> programas;

    public ControladorGestionPrograma() {
        this.programas = Singleton.getINSTANCIA().getProgramas();
    }

    public IList<Programa> getPrograma() {
        return programas;
    }

    public void agregarPrograma(Programa programa) {
        programas.agregar(programa);
        Singleton.getINSTANCIA().escribirProgramas(programas);
    }

    public Programa buscarPrograma(String codigo) throws ProgramaNoEncontradoException {
        for (int i = 0; i < programas.size(); i++) {
            if (programas.get(i).getCodigo().equals(codigo)) {
                return programas.get(i);
            }
        }
        throw new ProgramaNoEncontradoException();
    }

    public void eliminarPrograma(String codigo) throws ProgramaNoEncontradoException {
        boolean programaEliminado = false;
        for (int i = 0; i < programas.size(); i++) {
            if (programas.get(i).getCodigo().equals(codigo)) {
                programas.eliminar(programas.get(i));
                Singleton.getINSTANCIA().escribirProgramas(programas);
                programaEliminado = true;
                break;
            }
        }
        if (!programaEliminado) {
            throw new ProgramaNoEncontradoException();
        }
    }

    public void editarPrograma(String codigo, Programa programaActualizado) throws ProgramaNoEncontradoException {
        boolean programaEncontrado = false;
        for (int i = 0; i < programas.size(); i++) {
            if (programas.get(i).getCodigo().equals(codigo)) {
                programas.get(i).setNombre(programaActualizado.getNombre());
                programas.get(i).setFacultad(programaActualizado.getFacultad());
                programas.get(i).setSemestres(programaActualizado.getSemestres());
                Singleton.getINSTANCIA().escribirProgramas(programas);
                programaEncontrado = true;
                break;
            }
        }
        if (!programaEncontrado) {
            throw new ProgramaNoEncontradoException();
        }
    }

    public void validarPrograma(String codigo) throws ProgramaYaRegistradoException {
        for (int i = 0; i < programas.size(); i++) {
            if (programas.get(i).getCodigo().equals(codigo)) {
                throw new ProgramaYaRegistradoException();
            }
        }
    }

}
