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

    private ArrayList<Invitacion> Invitaciones;
    private ArrayList<Asistencia> Asistencias;
    private ArrayList<Ausencia> Ausencias;
    private ArrayList<Retraso> Retrasos;

    Reunion(Duration duracionPrevista, int dia, int mes, int año) {
        this.duracionPrevista=duracionPrevista;
        fecha= LocalDate.of(año,mes,dia);
    }

    public void Invitar(Instant hora, Invitable invitado){
        Invitaciones.add(new Invitacion(hora,invitado,this));
    }
    public void Unirse(Instant hora, Empleado yo){
        Asistencias.add(new Asistencia(yo));
        if (hora.compareTo(horaInicio)>0){
            Retrasos.add(new Retraso(hora,yo));
        }
    }
    public void Rechazar(Empleado yo){
        Ausencias.add(new Ausencia(yo));
    }

    public ArrayList obtenerAsistencias(){
        return Asistencias;
    }
    public ArrayList obtenerRetrasos(){
        return Retrasos;
    }
    public ArrayList obtenerAunsencias(){
        return Ausencias;
    }
    public int obtenerTotalAsistencia(){
        return Asistencias.size();
    }
    public float obtenerProcentajeAsistencia(){
        return (float) Asistencias.size()/(Asistencias.size()+Ausencias.size())*100f;
    }
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
