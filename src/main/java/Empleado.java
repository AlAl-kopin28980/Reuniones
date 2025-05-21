import java.time.Duration;
import java.time.Instant;

public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    private Reunion reunion;

    public Empleado(String id, String apellidos, String nombre, String correo){
        this.id=id;
        this.apellidos=apellidos;
        this.nombre=nombre;
        this.correo=correo;
    }

    public Reunion organizarReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String sala,tipoReunion tipo){
        return new ReunionPresencial(duracionPrevista, dia, mes, año, sala,tipo);
    }
    public Reunion organizarReunionVirtual(Duration duracionPrevista, int dia, int mes, int año, String enlace,tipoReunion tipo){
        return new ReunionVirtual(duracionPrevista, dia, mes, año, enlace,tipo);
    }

    public String getId() {
        return id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void Invitar(Reunion reunion){
        this.reunion=reunion;
    }
    public void UnirseAReunion(Instant hora){
        reunion.Unirse(hora, this);
    }
    public void RechazarReunion(Instant hora){
        reunion.Rechazar(this);
    }

    public String toString(){
        return nombre + " " + apellidos;
    }
}
