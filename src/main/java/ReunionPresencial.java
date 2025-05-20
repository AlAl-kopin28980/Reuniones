import java.time.Duration;

public abstract class ReunionPresencial extends Reunion{
    private String sala;
    public ReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String sala) {
        super(duracionPrevista,dia, mes, año);
        this.sala=sala;
    }
}
