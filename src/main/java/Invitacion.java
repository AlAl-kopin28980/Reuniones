import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Invitable invitado;

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
