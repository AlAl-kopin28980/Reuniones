import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class ReunionPresencial extends Reunion {
    private String sala;
    public ReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String sala,tipoReunion tipo) {
        super(duracionPrevista,dia, mes, año,tipo);
        this.sala=sala;
    }
    public void emitirInforme() {
        super.emitirInforme("Sala",sala);
    }
}
