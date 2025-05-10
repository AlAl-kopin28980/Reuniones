package src.main.java;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public abstract class Reunion {
    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    Reunion(Duration duracionPrevista, int dia, int mes, int año) {
        this.duracionPrevista=duracionPrevista;
        fecha= LocalDate.of(año,mes,dia);
    }
    public abstract ArrayList obtenerAsistencias();
    public abstract ArrayList obtenerRetrasos();
    public abstract ArrayList obtenerAunsencias();
    public abstract int obtenerTotalAsistencia();
    public abstract float obtenerProcentajeAsistencia();
    public float calcularTiempoReal(){
        float duracion = horaInicio.until(horaFin, ChronoUnit.SECONDS);
        return duracion;
    }
    public void iniciar(){
        horaInicio= Instant.now();
    }
    public void finalizar(){
        horaFin= Instant.now();
    }
    public Duration obtenerTiempoPrevisto(){
        return duracionPrevista;
    }


}
