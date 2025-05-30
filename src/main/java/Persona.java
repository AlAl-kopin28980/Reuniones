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

    /** Recive una invitacion
     * la invitacion es guardada para luego Unirse o Rechazar
     *
     * @param reunion Reunion a la que se es invitado
     */
    public void Invitar(Reunion reunion){
        this.reunion=reunion;
        this.RechazarReunion();
    }

    /** Se une a la reunion a la que ha sido invitada
     *
     * @param hora Instant en el que se une
     *
     * @throws NoTieneInvitacionException la Persona no ha sido invitada a una Reunion
     * @throws ReunionFinalizadaException no se puede unir a una Reunion finalizada
     */
    public void UnirseAReunion(Instant hora) throws NoTieneInvitacionException, ReunionFinalizadaException{
        if (reunion!=null) {
            reunion.Unirse(hora, this);
        }
        else if (reunion==null){
            throw new NoTieneInvitacionException(this);
        }
    }
    /** Rechaza la reunion a la que ha sido invitada
     */
    public void RechazarReunion(){
        reunion.Rechazar(this);
    }

    public String toString(){
        return nombre + " " + apellidos;
    }
}

