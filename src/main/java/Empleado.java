import java.time.Duration;
import java.time.Instant;

public class Empleado extends Persona implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    private Reunion reunion;

    public Empleado(String id, String apellidos, String nombre, String correo){
        super(id,apellidos,nombre,correo);
    }

    public Reunion organizarReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String sala,tipoReunion tipo){
        return new ReunionPresencial(duracionPrevista, dia, mes, año, sala,tipo);
    }
    public Reunion organizarReunionVirtual(Duration duracionPrevista, int dia, int mes, int año, String enlace,tipoReunion tipo){
        return new ReunionVirtual(duracionPrevista, dia, mes, año, enlace,tipo);
    }

}
