import java.time.Duration;

public abstract class ReunionVirtual extends Reunion {
    private String enlace;
    public ReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String enlace) {
        super(duracionPrevista,dia, mes, año);
        this.enlace=enlace;
    }
}
