import java.time.Instant;

public class Retraso extends Asistencia {
    private Instant hora;

    /**
     *
     * @param hora Instant a la que se une a la Reunion
     * @param e Persona que entro a la reunion con retraso
     */
    public Retraso(Instant hora, Persona e){
        super(e);
        this.hora = hora;
    }

    public Instant getHora() {
        return hora;
    }
}
