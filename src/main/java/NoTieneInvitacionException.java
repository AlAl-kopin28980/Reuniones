public class NoTieneInvitacionException extends Exception {
    public NoTieneInvitacionException(Persona persona){
        super(persona.toString()+" no tiene invitación para esta reunión. No puede unirse.");
    }
}
