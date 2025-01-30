
package Modelo;

public final class AdministradorGeneral {
    
    private static final String nombreUsuario = "admin";
    private static final String contrasena = "admin";
    private static final Usuario INSTANCE;
    
    static{
        INSTANCE = new Usuario(nombreUsuario, contrasena,Usuario.ADMINGENERAL);
        INSTANCE.setPrimerInicioSesion(false);
    }

    private AdministradorGeneral() {
    }

    public static Usuario getINSTANCE() {
        return INSTANCE;
    } 
    
}
