package Controladores;

import Modelo.Laboratorio;
import Modelo.Puesto;
import Modelo.Reserva;
import Util.IList;

public class ControladorPuestosLaboratorio {

    Laboratorio laboratorio;
    Puesto[][] puestos;

    public ControladorPuestosLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
        this.puestos = laboratorio.getPuestos();
        actualizarEstadoPuestos();
        laboratorio.sacarEstudianteDeCola();
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }
    
    public int getCantidadPuestos(){
        return laboratorio.getCantidadPuestos();
    }

    public Puesto getPuesto(int fila, int columna) {
        return puestos[fila][columna];
    }
    
    public void actualizarEstadoPuestos(){
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                puestos[i][j].actualizarEstado();
            }
        }
    }

}
