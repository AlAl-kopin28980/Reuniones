import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReunionVirtual extends Reunion {
    private String enlace;

    /**
     *
     * @param horaPrevista
     * @param duracionPrevista
     * @param dia
     * @param mes
     * @param año
     * @param enlace Enlace donde se realizara la Reunion
     * @param tipo
     */
    public ReunionVirtual(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String enlace, tipoReunion tipo) {
        super(horaPrevista,duracionPrevista,dia, mes, año,tipo);
        this.enlace=enlace;
    }

    /**
     * Emite un informe sobre la Reunion
     */
    public void emitirInforme() throws ReunionSinIniciarException,ReunionEnCursoException {
        super.emitirInforme("Enlace",enlace);
    }
}
