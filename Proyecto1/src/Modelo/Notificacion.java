
package Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notificacion implements Serializable{
    
    private int identificador;
    private LocalDateTime fechaEnvio;
    private String mensaje;

    public Notificacion() {
        this.fechaEnvio = LocalDateTime.now();
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getFormatoFecha(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaEnvio.format(formato);
    }
    
    public void notificacionPorMantenimiento(Reserva reserva){
        this.mensaje = "Tu reserva ha sido CANCELADA por mantenimiento.";
    }
    
    public void notificacionPorModificacionPuestos(Reserva reserva){
        this.mensaje = "Tu reserva ha sido CANCELADA por cambio en la cantidad de puestos.";
    }
    
    public void notificacionPrestamo(Prestamo prestamo){
        this.mensaje = "¡Tu prestamo está disponible!";
    }
    
}
