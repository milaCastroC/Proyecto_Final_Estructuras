package Modelo;

import Excepciones.CantidadNotasInvalidaException;
import Excepciones.CursoYaMatriculadoException;
import Excepciones.DiaInvalidoException;
import Excepciones.DocenteNoDisponibleException;
import Excepciones.EstudianteNoDisponibleException;
import Excepciones.FueraDeJornadaException;
import Excepciones.HoraInvalidaException;
import Excepciones.HorarioInvalidoException;
import Excepciones.HorarioNoDefinidoException;
import Excepciones.NotaExistenteException;
import Excepciones.NotaInvalidaException;
import Excepciones.PorcentajeInvalidoException;
import Excepciones.ProgramaIncompatibleException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Util.IList;
import Util.Listas;
import Util.Singleton;
import excepciones.NotaNoEncontradaException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Curso implements Serializable {

    private String nombre;
    private Docente docente;
    private String codigoCurso;
    private Programa programa;
    private String jornada;
    private String periodo;
    private String estado;
    private LocalDate fechaInicio;
    private IList<Estudiante> estudiantes;
    private IList<Horario> horarios;
    private IList<Nota> notasEstudiantes;

    public static final String ACTIVO = "Activo";
    public static final String FINALIZADO = "Finalizado";

    public static final String NOCTURNA = "Nocturna";
    public static final String DIURNA = "Diurna";

    public Curso(String nombre, Docente docente, String codigoCurso, Programa programa, String jornada) {
        this.nombre = nombre;
        this.docente = docente;
        this.codigoCurso = codigoCurso;
        this.programa = programa;
        this.jornada = jornada;
        this.periodo = generarPeriodo();
        this.estado = ACTIVO;
        this.fechaInicio = LocalDate.now();
        this.estudiantes = new Listas<>();
        this.horarios = new Listas<>();
        this.notasEstudiantes = new Listas<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        actualizarEstado();
        Singleton.getINSTANCIA().escribirCursos();
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public IList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(IList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public IList<Horario> getHorarios() {
        return horarios;
    }

    public IList<Nota> getNotasEstudiantes() {
        return notasEstudiantes;
    }

    public void setNotasEstudiantes(IList<Nota> notasEstudiantes) {
        this.notasEstudiantes = notasEstudiantes;
    }

    //Validaciones
    public void actualizarEstado() {
        LocalDate fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        LocalDate fechaInicio;
        LocalDate fechaFin;

        if (mes <= 6) {
            fechaInicio = LocalDate.of(fechaActual.getYear(), Month.JANUARY, 1);
            fechaFin = LocalDate.of(fechaActual.getYear(), Month.JUNE, 30);
        } else {
            fechaInicio = LocalDate.of(fechaActual.getYear(), Month.JULY, 1);
            fechaFin = LocalDate.of(fechaActual.getYear(), Month.DECEMBER, 31);
        }
        if (fechaInicio != null && fechaFin != null) {
            if (this.fechaInicio.isAfter(fechaInicio) && this.fechaInicio.isBefore(fechaFin)) {
                estado = ACTIVO;
            } else {
                estado = FINALIZADO;
            }
        }
    }
    
    public String generarPeriodo() {
        LocalDate hoy = LocalDate.now();
        int mes = hoy.getMonthValue();
        if (mes >= 1 && mes <= 6) {
            return hoy.getYear() + "-" + "1";
        } else {
            return hoy.getYear() + "-" + "2";
        }
    }

    public void validarDocenteDisponible(Horario horario) throws DocenteNoDisponibleException, DiaInvalidoException {
        IList<Curso> cursosDocente = docente.getCursosActuales();
        for (int i = 0; i < cursosDocente.size(); i++) {
            IList<Horario> horarioCursos = cursosDocente.get(i).getHorarios();
            for (int j = 0; j < horarioCursos.size(); j++) {
                Horario nuevoHorario = horarioCursos.get(j);
                if (nuevoHorario.convertirDia().equals(horario.convertirDia())) {
                    LocalTime inicioPrimerClase = nuevoHorario.getHoraInicio();
                    LocalTime finPrimerClase = nuevoHorario.getHoraFin();
                    LocalTime inicioSegundaClase = horario.getHoraInicio();
                    LocalTime finSegundaClase = horario.getHoraFin();
                    if (inicioPrimerClase.isBefore(finSegundaClase) && (finPrimerClase.isAfter(inicioSegundaClase))) {
                        throw new DocenteNoDisponibleException();
                    }
                }

            }
        }
    }

    public void validarJornada(Horario horario) throws FueraDeJornadaException {
        LocalTime horaInicio = horario.getHoraInicio();
        LocalTime horaFin = horario.getHoraFin();
        if (jornada.equals(DIURNA)) {
            if (horaInicio.isBefore(LocalTime.of(7, 0)) || horaInicio.isAfter(LocalTime.of(18, 0)) || horaFin.isAfter(LocalTime.of(18, 0))) {
                throw new FueraDeJornadaException();
            }
        } else {
            if (horaInicio.isBefore(LocalTime.of(18, 0)) || horaInicio.isAfter(LocalTime.of(22, 0)) || horaFin.isAfter(LocalTime.of(22, 0))) {
                throw new FueraDeJornadaException();
            }
        }
    }

    public void validarDuracion(Horario horario) throws HorarioInvalidoException {
        long duracion = ChronoUnit.HOURS.between(horario.getHoraInicio(), horario.getHoraFin());
        if (duracion < 1) {
            throw new HorarioInvalidoException();
        }
    }

    public void validarDisponibilidadEstudiante(Estudiante estudiante) throws EstudianteNoDisponibleException, DiaInvalidoException {
        IList<Curso> cursosEstudiante = estudiante.getCursosMatriculados();
        for (int i = 0; i < cursosEstudiante.size(); i++) {
            IList<Horario> horarioCursos = cursosEstudiante.get(i).getHorarios();
            for (int j = 0; j < horarioCursos.size(); j++) {
                Horario nuevoHorario = horarioCursos.get(j);
                for (int k = 0; k < horarios.size(); k++) {
                    Horario horarioCursoActual = horarios.get(k);
                    if (nuevoHorario.convertirDia().equals(horarioCursoActual.convertirDia())) {
                        LocalTime inicioPrimerClase = nuevoHorario.getHoraInicio();
                        LocalTime finPrimerClase = nuevoHorario.getHoraFin();
                        LocalTime inicioSegundaClase = horarioCursoActual.getHoraInicio();
                        LocalTime finSegundaClase = horarioCursoActual.getHoraFin();
                        if (inicioPrimerClase.isBefore(finSegundaClase) && (finPrimerClase.isAfter(inicioSegundaClase))) {
                            throw new EstudianteNoDisponibleException();
                        }
                    }
                }
            }
        }
    }

    public void validarDisponibilidadEstudiante(Horario nuevoHorario) throws EstudianteNoDisponibleException, DiaInvalidoException {
        for (int i = 0; i < estudiantes.size(); i++) {
            IList<Curso> cursoEstudiantes = estudiantes.get(i).getCursosMatriculados();
            for (int j = 0; j < cursoEstudiantes.size(); j++) {
                IList<Horario> horariosExistentes = cursoEstudiantes.get(j).getHorarios();
                for (int k = 0; k < horariosExistentes.size(); k++) {
                    Horario horariosE = horariosExistentes.get(k);
                    if (horariosE.convertirDia().equals(nuevoHorario.convertirDia())) {
                        LocalTime inicioNuevoHorario = nuevoHorario.getHoraInicio();
                        LocalTime finNuevoHorario = nuevoHorario.getHoraFin();
                        LocalTime inicioHorarioEstudiante = horariosE.getHoraInicio();
                        LocalTime finHorarioEstudiante = horariosE.getHoraFin();
                        if (inicioNuevoHorario.isBefore(finHorarioEstudiante) && finNuevoHorario.isAfter(inicioHorarioEstudiante)) {
                            throw new EstudianteNoDisponibleException();
                        }
                    }

                }

            }
        }
    }

    public Estudiante validarEstudiante(String id) throws UsuarioInexistenteException, UsuarioNoCoincideException {
        for (int i = 0; i < estudiantes.size(); i++) {
            Persona persona = estudiantes.get(i);
            if (estudiantes.get(i).getId().equals(id)) {
                if (persona instanceof Estudiante) {
                    return (Estudiante) estudiantes.get(i);
                } else {
                    throw new UsuarioNoCoincideException();
                }
            }
        }
        throw new UsuarioInexistenteException();
    }

    public void validarHorariosCurso() throws HorarioNoDefinidoException {
        if (horarios.isEmpty()) {
            throw new HorarioNoDefinidoException();
        }
    }
    
    //Gestion Horario
    public void agregarHorario(Horario horario, IList<Curso> listaCurso) throws HoraInvalidaException, DocenteNoDisponibleException, DiaInvalidoException, FueraDeJornadaException, HorarioInvalidoException, EstudianteNoDisponibleException {
        if (horario.getHoraFin().isBefore(horario.getHoraInicio())) {
            throw new HoraInvalidaException();
        }
        validarDuracion(horario);
        validarDocenteDisponible(horario);
        validarJornada(horario);
        validarDisponibilidadEstudiante(horario);
        horarios.agregar(horario);
        Singleton.getINSTANCIA().escribirCursos();
    }

    public void eliminarHorario(Horario horario, IList<Curso> listaCurso) {
        horarios.eliminar(horario);
        Singleton.getINSTANCIA().escribirCursos();
    }

    //Gestion estudiante
    
    public void generarNotas(Estudiante estudiante){
        for(int i = 0; i < notasEstudiantes.size(); i++){
            Nota nota = notasEstudiantes.get(i);
            IList<DetalleNota>notas = nota.getListaNotas();
            notas.agregar(new DetalleNota(estudiante, 0.0));
        }
    }
    public void matricularEstudiante(Estudiante estudiante, IList<Curso> listaCurso) throws CursoYaMatriculadoException, EstudianteNoDisponibleException, DiaInvalidoException, HorarioNoDefinidoException {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).equals(estudiante)) {
                throw new CursoYaMatriculadoException();
            }
        }
        validarHorariosCurso();
        validarDisponibilidadEstudiante(estudiante);
        estudiantes.agregar(estudiante);
        generarNotas(estudiante);
        Singleton.getINSTANCIA().escribirCursos();
    }

    public Estudiante buscarEstudianteEnCurso(String idEstudiante) throws UsuarioInexistenteException {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (idEstudiante.equals(estudiantes.get(i).getId())) {
                return estudiantes.get(i);
            }
        }
        throw new UsuarioInexistenteException();
    }

    public void eliminarEstudianteCurso(String idEstudiante) throws UsuarioInexistenteException, UsuarioNoCoincideException {
        Estudiante estudiante = buscarEstudianteEnCurso(idEstudiante);
        validarEstudiante(idEstudiante);
        estudiantes.eliminar(estudiante);
        estudiante.getCursosMatriculados().eliminar(this);
        Singleton.getINSTANCIA().escribirCursos();
    }

    //Gestion notas
    public void validarNota(String descripcion) throws NotaExistenteException {
        for (int i = 0; i < notasEstudiantes.size(); i++) {
            if (descripcion.equals(notasEstudiantes.get(i).getDescripcion())) {
                throw new NotaExistenteException();
            }
        }
    }

    public void validarPorcentaje(IList<Nota> notas) throws NotaInvalidaException, PorcentajeInvalidoException {
        int porcentaje = 0;
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i).getPorcentaje() == 0) {
                throw new PorcentajeInvalidoException();
            }
            porcentaje += notas.get(i).getPorcentaje();
        }
        if (porcentaje != 100) {
            throw new NotaInvalidaException();
        }
    }
    
    public void validarNotaExistente(IList<Nota>notas)throws NotaExistenteException{
        for(int i = 0; i < notas.size(); i++){
            Nota nota = notas.get(i);
            for(int j = 0; j < notas.size(); j++){
                if( i != j && nota.getDescripcion().equals(notas.get(j).getDescripcion())){
                    throw new NotaExistenteException();
                }
            }
        }
    }

    public void calcularPorcentajeNota() {
        if (notasEstudiantes.isEmpty()) {
            return;
        }
        int porcentaje = 100 / notasEstudiantes.size();
        for (int i = 0; i < notasEstudiantes.size(); i++) {
            notasEstudiantes.get(i).setPorcentaje(porcentaje);
        }
        if (100 % notasEstudiantes.size() != 0) {
            int ajuste = 100 - (porcentaje * notasEstudiantes.size());
            notasEstudiantes.get(notasEstudiantes.size() - 1).setPorcentaje(porcentaje + ajuste);
        }
        Singleton.getINSTANCIA().escribirCursos();
    }
    
    public Nota buscarNota(String descripcion) throws NotaNoEncontradaException{
        for (int i = 0; i < notasEstudiantes.size(); i++) {
            if(notasEstudiantes.get(i).getDescripcion().equals(descripcion)){
                return notasEstudiantes.get(i);
            }
        }
        throw new NotaNoEncontradaException();
    }
    
    public Nota buscarNota(int indice)throws NotaNoEncontradaException{
        for (int i = 0; i < notasEstudiantes.size(); i++) {
            if(i == indice){
                return notasEstudiantes.get(i);
            }
        }
        throw new NotaNoEncontradaException();
    }
    
    public void agregarNota(String descripcion) throws CantidadNotasInvalidaException, NotaExistenteException {
        if (notasEstudiantes.size() < 3) {
            validarNota(descripcion);
            Nota nota = new Nota(descripcion, 0);
            nota.setEstudiantes(estudiantes);
            notasEstudiantes.agregar(nota);

            calcularPorcentajeNota();

            Singleton.getINSTANCIA().escribirCursos();
        } else {
            throw new CantidadNotasInvalidaException();
        }
    }
    
    public void eliminarNota(String descripcion)throws NotaNoEncontradaException{
        Nota nota = buscarNota(descripcion);
        if(nota == null){
            throw new NotaNoEncontradaException();
        }
        notasEstudiantes.eliminar(nota);
        calcularPorcentajeNota();
        Singleton.getINSTANCIA().escribirCursos();
    }
    
    public double obtenerNotaFinal(Estudiante estudiante){
        double notaFinal = 0.0;
        for(int i = 0; i < notasEstudiantes.size(); i++){
            double notaDefinitiva = notasEstudiantes.get(i).obtenerNotaEstudiante(estudiante).getValor();
            double porcentaje = notasEstudiantes.get(i).getPorcentaje() / 100.0;
            notaFinal += notaDefinitiva * porcentaje;
        }
        notaFinal = Math.round(notaFinal * 10.0)/ 10.0;
        return notaFinal;
    }
    
    public void actualizarNota(Nota notaActualizada, int indice)throws NotaNoEncontradaException{
        Nota nota = buscarNota(indice);
        nota.setDescripcion(notaActualizada.getDescripcion());
        nota.setPorcentaje(notaActualizada.getPorcentaje());
        Singleton.getINSTANCIA().escribirCursos();
    }
    
    public void actualizarDetalleNota(String descripcion, DetalleNota notaAuxiliar) throws NotaNoEncontradaException, NotaInvalidaException{
        Nota nota = buscarNota(descripcion);
        nota.actualizarNota(notaAuxiliar);
    }
    
    @Override
    public String toString() {
        return codigoCurso + " - " + nombre;
    }
}
