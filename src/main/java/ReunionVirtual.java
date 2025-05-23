import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReunionVirtual extends Reunion {
    private String enlace;
    public ReunionVirtual(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, String enlace, tipoReunion tipo) {
        super(horaPrevista,duracionPrevista,dia, mes, año,tipo);
        this.enlace=enlace;
    }
    public void emitirInforme() {
        super.emitirInforme("Enlace",enlace);
    }
}
