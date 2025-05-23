import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public abstract class Reunion {
    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio = null;
    private Instant horaFin = null;
    private float duracion;
    private tipoReunion tipo;

    private ArrayList<Invitacion> Invitaciones;
    private ArrayList<Asistencia> Asistencias;
    private ArrayList<Ausencia> Ausencias;
    private ArrayList<Retraso> Retrasos;
    private ArrayList<Nota> Notas;

    public Reunion(Instant horaPrevista, Duration duracionPrevista, int dia, int mes, int año, tipoReunion tipo) {
        this.horaPrevista=horaPrevista;
        this.duracionPrevista=duracionPrevista;
        fecha= LocalDate.of(año,mes,dia);
        this.tipo=tipo;
        Invitaciones = new ArrayList<Invitacion>();
        Asistencias = new ArrayList<Asistencia>();
        Ausencias = new ArrayList<Ausencia>();
        Retrasos = new ArrayList<Retraso>();
        Notas = new ArrayList<Nota>();
    }

    public void Invitar(Instant hora, Invitable invitado){
        Invitaciones.add(new Invitacion(hora,invitado,this));
    }
    public void Unirse(Instant hora, Persona yo) {
        if (horaFin == null) {
            Asistencias.add(new Asistencia(yo));
            Iterator<Ausencia> itr =Ausencias.iterator();
            while (itr.hasNext()){
                Ausencia ci_itr=itr.next();
                if (ci_itr.getPersona() == yo){
                    itr.remove();
                }
            }
            if (horaInicio != null)
                if (hora.compareTo(horaInicio) > 0)
                    Retrasos.add(new Retraso(hora, yo));
        }
    }
    public void Rechazar(Persona yo){
        Ausencias.add(new Ausencia(yo));
    }

    public void crearNota(String contenido){
        Notas.add(new Nota(contenido));
    }

    public ArrayList<Nota> obtenerNotas(){
        return Notas;
    }
    public ArrayList<Asistencia> obtenerAsistencias(){
        return Asistencias;
    }
    public ArrayList<Retraso> obtenerRetrasos(){
        return Retrasos;
    }
    public ArrayList<Ausencia> obtenerAunsencias(){
        return Ausencias;
    }
    public int obtenerTotalAsistencia(){
        return Asistencias.size();
    }
    public float obtenerProcentajeAsistencia(){
        return (float) Asistencias.size()/(Asistencias.size()+Ausencias.size())*100f;
    }
    public float calcularTiempoReal(){
        duracion = horaInicio.until(horaFin, ChronoUnit.SECONDS);
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
    public LocalDate obtenerFecha(){return fecha;}
    public Instant obtenerHoraInicioPrevista(){return horaPrevista;}
    public Instant obtenerHoraInicio(){return horaInicio;}
    public Instant obtenerHoraFin(){return horaFin;}
    public tipoReunion obtenerTipoReunion(){return tipo;}

    public String toString() {
        if (horaInicio==null)
            return "Reunion prevista para la hora: "+ horaPrevista.toString();
        else if (horaFin==null)
            return "Reunion en progreso desde la hora: "+ horaInicio.toString();
        else
            return "Reunion terminada a la hora: "+ horaFin.toString();
    }

    public String InstantToString(Instant hora) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneOffset.UTC);
            String formattedInstant = formatter.format(hora);
            return formattedInstant;
    }
    protected void emitirInforme(String EspacioDeReunion, String EspacioEspecifico) {
        try {
            FileWriter informe = new FileWriter("InformeReunion.txt");
            informe.write("Fecha de la reunión:"+fecha+"\nHora de inicio prevista:"+this.InstantToString(horaPrevista)+"\nHora de inicio real:"+this.InstantToString(horaInicio));
            informe.write("\nHora de fin:"+this.InstantToString(horaFin)+"\nDuración de la reunión:"+this.calcularTiempoReal()+"\n"+EspacioDeReunion+" de la reunión:"+EspacioEspecifico+"\nTipo de reunión:"+tipo);
            informe.write("\nLista de participantes:");
            for (Asistencia persona: Asistencias){
                informe.write("\n"+persona.toString());
            }
            informe.write("\nLista de retrasos:");
            for (Retraso persona: Retrasos){
                informe.write("\n"+persona.toString()+". Tiempo de llegada: "+this.InstantToString(persona.getHora()));
            }
            informe.write("\nLista de ausencias:");
            for (Ausencia persona: Ausencias){
                informe.write("\n"+persona.toString());
            }
            informe.write("\nTotal de asistentes: "+this.obtenerTotalAsistencia()+"\nPorcentaje de asistencia: "+this.obtenerProcentajeAsistencia());
            informe.write("\nNotas:");
            for (Nota nota: Notas){
                informe.write("\n"+nota.getContenido());
            }
            informe.close();
            System.out.println("Se emitió informe correctamente.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
        }
    }


}
