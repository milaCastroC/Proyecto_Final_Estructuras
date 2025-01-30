
package Controladores;

import Excepciones.UsuarioNoCoincideException;
import Modelo.Curso;
import Modelo.Docente;
import Modelo.Usuario;
import Util.IList;
import java.time.DayOfWeek;

public class ControladorPrincipalDocente {
    
    Usuario usuario;

    public ControladorPrincipalDocente(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public IList<Curso>cursosActuales() throws UsuarioNoCoincideException{
        if (usuario.getRol().equals(Usuario.DOCENTE)) {
            Docente docente = (Docente) usuario;
            return docente.getCursosActuales();
        }else{
            throw new UsuarioNoCoincideException();
        }
        
    }
    
    public String []getHorario(DayOfWeek dia)throws UsuarioNoCoincideException{
        if (usuario.getRol().equals(Usuario.DOCENTE)) {
            Docente docente = (Docente) usuario;
            return docente.definirHorarioDia(dia);
        }else{
            throw new UsuarioNoCoincideException();
        }
    }
    
}
