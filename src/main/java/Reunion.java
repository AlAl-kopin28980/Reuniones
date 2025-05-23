import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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

    /**
     *
     * @param horaPrevista
     * @param duracionPrevista
     * @param dia
     * @param mes
     * @param año
     * @param tipo Motivo de la Reunion
     */
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

    /** Invita un Invitable
     *
     * @param hora Hora en que se envia la invitacion
     * @param invitado Invitable que se le envia la invitacion
     */
    public void Invitar(Instant hora, Invitable invitado){
        Invitaciones.add(new Invitacion(hora,invitado,this));
    }

    /** Une a persona a la reunion y marca su Asistencia
     * si llega luego de la hora de inicio se registra Retraso
     *
     * @param hora Instant en el que se une a la reunion
     * @param yo Persona que se une
     */
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

    /**
     *
     * @param yo Persona que rechaza unirse a la reunion
     */
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

    /**
     *
     * @return Porcentaje de Asistencia sobre Personas invitadas
     */
    public float obtenerProcentajeAsistencia(){
        return (float) Asistencias.size()/(Asistencias.size()+Ausencias.size())*100f;
    }

    /** Calcula la duracion real de la Reunion
     *
     * @return duracion de la Reunion en segundos
     */
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

    /**
     *
     * @param EspacioDeReunion Nombre generico de donde se realiza la reunion ej: "Sala"
     * @param EspacioEspecifico Nombre de donde se realiza la reunion ej: "1-3"
     */
    protected void emitirInforme(String EspacioDeReunion, String EspacioEspecifico) {
        try {
            ArrayList<Asistencia> asistencias =this.obtenerAsistencias();
            ArrayList<Retraso> retrasos=this.obtenerRetrasos();
            ArrayList<Ausencia> ausencias =this.obtenerAunsencias();
            ArrayList<Nota> notas =this.obtenerNotas();
            FileWriter informe = new FileWriter("InformeReunion.txt");
            informe.write("Fecha de la reunión:"+this.obtenerFecha()+"\nHora de inicio prevista:"+this.obtenerHoraInicioPrevista()+"\nHora de inicio real:"+this.obtenerHoraInicio());
            informe.write("\nHora de fin:"+this.obtenerHoraFin()+"\nDuración de la reunión:"+this.calcularTiempoReal()+"\n"+EspacioDeReunion+" de la reunión:"+EspacioEspecifico+"\nTipo de reunión:"+this.obtenerTipoReunion());
            informe.write("\nLista de participantes:");
            for (Asistencia persona: asistencias){
                informe.write("\n"+persona.toString());
            }
            informe.write("\nLista de retrasos:");
            for (Retraso persona: retrasos){
                informe.write("\n"+persona.toString()+". Tiempo de llegada: "+persona.getHora());
            }
            informe.write("\nLista de ausencias:");
            for (Ausencia persona: ausencias){
                informe.write("\n"+persona.toString());
            }
            informe.write("\nNotas:");
            for (Nota nota: notas){
                informe.write("\n"+nota.getContenido());
            }
            informe.close();
            System.out.println("Se emitió informe correctamente.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error error.");
            e.printStackTrace();
        }
    }

}
