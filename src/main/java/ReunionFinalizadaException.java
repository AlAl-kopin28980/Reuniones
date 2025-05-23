public class ReunionFinalizadaException extends Exception{
    public ReunionFinalizadaException(String mensaje){
        super("Reunión finalizada. No es posible "+mensaje);
    }
}
