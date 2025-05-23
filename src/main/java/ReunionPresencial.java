import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class ReunionPresencial extends Reunion {
    private String sala;

    /**
     *
     * @param horaPrevista
     * @param duracionPrevista
     * @param dia
     * @param mes
     * @param año
     * @param sala Sala donde se realizara la Reunion
     * @param tipo
     */
    public ReunionPresencial(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String sala, tipoReunion tipo) {
        super(horaPrevista,duracionPrevista,dia, mes, año,tipo);
        this.sala=sala;
    }

    /**
     * Emite un informe sobre la Reunion
     *
     * @throws ReunionSinIniciarException cuando la Reunion no ha iniciado
     * @throws ReunionEnCursoException cuando la Reunion no ha finalizado
     */
    public void emitirInforme() throws ReunionEnCursoException,ReunionSinIniciarException{
        super.emitirInforme("Sala",sala);
    }
}
