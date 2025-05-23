import java.time.Duration;
import java.time.Instant;

public class Empleado extends Persona implements Invitable {
    public Empleado(String id, String apellidos, String nombre, String correo){
        super(id,apellidos,nombre,correo);
    }

    public ReunionPresencial organizarReunionPresencial(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String sala,tipoReunion tipo){
        return new ReunionPresencial(horaPrevista, duracionPrevista, dia, mes, año, sala,tipo);
    }
    public ReunionVirtual organizarReunionVirtual(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String enlace,tipoReunion tipo){
        return new ReunionVirtual(horaPrevista, duracionPrevista, dia, mes, año, enlace,tipo);
    }

}
