import java.time.Instant;

public class Retraso extends Asistencia {
    private Instant hora;

    public Retraso(Instant hora, Empleado e){
        super(e);
        this.hora = hora;
    }

    public Instant getHora() {
        return hora;
    }
}
