import java.time.Duration;
import java.time.Instant;

public class Persona implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    private Reunion reunion;

    public Persona(String id, String apellidos, String nombre, String correo){
        this.id=id;
        this.apellidos=apellidos;
        this.nombre=nombre;
        this.correo=correo;
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
        reunion.Rechazar(this);
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

