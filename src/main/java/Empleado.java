import java.time.Duration;
import java.time.Instant;

public class Empleado extends Persona {
    public Empleado(String id, String apellidos, String nombre, String correo){
        super(id,apellidos,nombre,correo);
    }

    /** Organiza una Reunion Presencial
     *
     * @param horaPrevista
     * @param duracionPrevista
     * @param dia
     * @param mes
     * @param año
     * @param sala
     * @param tipo
     * @return Reunion creada
     */
    public ReunionPresencial organizarReunionPresencial(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String sala,tipoReunion tipo){
        return new ReunionPresencial(horaPrevista, duracionPrevista, dia, mes, año, sala,tipo);
    }

    /** Organiza una Reunion Virtual
     *
     * @param horaPrevista
     * @param duracionPrevista
     * @param dia
     * @param mes
     * @param año
     * @param enlace
     * @param tipo
     * @return Reunion creada
     */
    public ReunionVirtual organizarReunionVirtual(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String enlace,tipoReunion tipo){
        return new ReunionVirtual(horaPrevista, duracionPrevista, dia, mes, año, enlace,tipo);
    }

}
