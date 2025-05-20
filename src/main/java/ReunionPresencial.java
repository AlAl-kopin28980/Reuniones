import java.time.Duration;
public class ReunionPresencial extends Reunion {
    private String sala;
    public ReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String sala) {
        super(duracionPrevista,dia, mes, año);
        this.sala=sala;
    }
}
