import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Invitable invitado;

    /**
     *
     * @param hora Instant en el que es enviada la invitacion
     * @param invitado Invitable al que se envia la invitacion
     * @param reunion Reunion a la que se esta invitando
     */
    public Invitacion(Instant hora, Invitable invitado, Reunion reunion){
        this.hora = hora;
        this.invitado = invitado;
        invitado.Invitar(reunion);
    }

    public Instant getHora() {
        return hora;
    }
    public Invitable getInvitado() {
        return invitado;
    }
    public String toString(){return invitado.toString();}
}
