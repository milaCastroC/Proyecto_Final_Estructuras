package Controladores;

import Excepciones.CantidadPuestosInvalidaException;
import Excepciones.MantenimientoExistenteException;
import Excepciones.MantenimientoInexistenteException;
import Excepciones.MantenimientoInvalidoException;
import Modelo.Laboratorio;
import Modelo.Mantenimiento;
import Util.IList;
import java.time.LocalDate;

public class ControladorGestionLaboratorio {

    Laboratorio laboratorio;

    public ControladorGestionLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
        this.laboratorio.actualizarEstadoMantenimientos();
    }

    public void actualizarCantidadPuestos(int cantidadPuestos)throws CantidadPuestosInvalidaException{
        if(cantidadPuestos < 1 || cantidadPuestos > 24){
            throw new CantidadPuestosInvalidaException();
        }
        laboratorio.generarMatrizPuestos(cantidadPuestos);
    }

    public int getCantidadPuestos() {
        return laboratorio.getCantidadPuestos();
    }
    
    public IList<Mantenimiento> getMantenimientos(){
        return laboratorio.getMantenimientos();
    }
    
    public Mantenimiento buscarMantenimiento(LocalDate fechaActual) throws MantenimientoInexistenteException{
        for(int i = 0; i < laboratorio.getMantenimientos().size(); i++){
            if(fechaActual.equals(laboratorio.getMantenimientos().get(i).getDia())){
                return laboratorio.getMantenimientos().get(i);
            }
        }
        throw new  MantenimientoInexistenteException();
    }
    
    public void agregarMantenimiento(Mantenimiento mantenimiento) throws MantenimientoInvalidoException, MantenimientoExistenteException{
        laboratorio.agregarMantenimiento(mantenimiento);
    }
          
}
