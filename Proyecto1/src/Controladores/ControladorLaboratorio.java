package Controladores;

import Modelo.Laboratorio;
import Util.Singleton;

public class ControladorLaboratorio {

    Laboratorio[][] laboratorios;

    public ControladorLaboratorio() {
        this.laboratorios = Singleton.getINSTANCIA().getLaboratorios();
        if (laboratorios[0][0] == null) {
            initLaboratorios();
        }
    }

    public void initLaboratorios() {
        for (int i = 0; i < laboratorios.length; i++) {
            for (int j = 0; j < laboratorios[i].length; j++) {
                laboratorios[i][j] = new Laboratorio();
            }
        }
    }

    public Laboratorio getLaboratorio(int fila, int columna) {
        return laboratorios[fila][columna];
    }

    public Laboratorio[][] getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(Laboratorio[][] laboratorios) {
        this.laboratorios = laboratorios;
    }

}
