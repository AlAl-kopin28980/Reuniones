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
     *
     * @throws ReunionFinalizadaException si la Reunion termino no se pueden enviar Invitaciones
     */
    public void Invitar(Instant hora, Invitable invitado) throws ReunionFinalizadaException{
        if (horaFin==null) {
            Invitaciones.add(new Invitacion(hora, invitado, this));
        }
        else{
            throw new ReunionFinalizadaException("invitar a nuevas personas.");
        }
    }

    /** Une a persona a la reunion y marca su Asistencia
     * si llega luego de la hora de inicio se registra Retraso
     *
     * @param hora Instant en el que se une a la reunion
     * @param yo Persona que se une
     *
     * @throws ReunionFinalizadaException no se puede unir a una Reunion finalizida
     */
    public void Unirse(Instant hora, Persona yo) throws ReunionFinalizadaException{
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
        else if (horaFin!=null){
            throw new ReunionFinalizadaException("unirse a la reunión");
        }
    }

    /**
     *
     * @param yo Persona que rechaza unirse a la reunion
     */
    public void Rechazar(Persona yo){
        Ausencias.add(new Ausencia(yo));
    }

    public void crearNota(String contenido) throws ReunionFinalizadaException{
        if (horaFin==null) {
            Notas.add(new Nota(contenido));
        }
        else{
            throw new ReunionFinalizadaException("crear más notas");
        }
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

    public ArrayList<Invitacion> obtenerInvitaciones(){
        return Invitaciones;
    }

    public int obtenerTotalAsistencia(){
        return Asistencias.size();
    }

    /**
     *
     * @return Porcentaje de Asistencia sobre Personas invitadas
     *
     * @throws ReunionSinIniciarException el metodo debe ser llamado despues de que termine la Reunion
     * @throws ReunionEnCursoException
     */
    public float obtenerProcentajeAsistencia() throws ReunionEnCursoException,ReunionSinIniciarException{
        if(horaInicio!=null & horaFin!=null) {
            return (float) Asistencias.size()/(Asistencias.size()+Ausencias.size())*100f;
        }
        else if(horaInicio==null){
            throw new ReunionSinIniciarException();
        }
        else {
            throw new ReunionEnCursoException();
        }
    }

    /** Calcula la duracion real de la Reunion
     *
     * @return duracion de la Reunion en segundos
     *
     * @throws ReunionSinIniciarException no se puede calcular la duracion de una Reunion sin iniciar
     * @throws ReunionEnCursoException no se puede calcular la duracion de una Reunion sin finalizar
     */
    public float calcularTiempoReal() throws ReunionEnCursoException,ReunionSinIniciarException{
        if(horaInicio!=null & horaFin!=null) {
            duracion = horaInicio.until(horaFin, ChronoUnit.SECONDS);
            return duracion;
        }
        else if(horaInicio==null){
            throw new ReunionSinIniciarException();
        }
        else {
            throw new ReunionEnCursoException();
        }
    }
    public void iniciar(){
        horaInicio= Instant.now();
    }

    /**
     *
     * @throws ReunionSinIniciarException solo se puede finalizar una Reunion en curso
     */
    public void finalizar() throws ReunionSinIniciarException{
        if (horaInicio!=null) {
            horaFin = Instant.now();
        }
        else{throw new ReunionSinIniciarException();}
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneOffset.ofHours(-4));
            String formattedInstant = formatter.format(hora);
            return formattedInstant;
    }
    /**
     *
     * @param EspacioDeReunion Nombre generico de donde se realiza la reunion ej: "Sala"
     * @param EspacioEspecifico Nombre de donde se realiza la reunion ej: "1-3"
     *
     * @throws ReunionSinIniciarException cuando la Reunion no ha iniciado
     * @throws ReunionEnCursoException cuando la Reunion no ha finalizado
     */
    protected void emitirInforme(String EspacioDeReunion, String EspacioEspecifico) throws ReunionSinIniciarException,ReunionEnCursoException {
        try{
            float tiempoReal=this.calcularTiempoReal();
            try {
                FileWriter informe = new FileWriter("InformeReunion.txt");
                informe.write("Fecha de la reunión:"+fecha+"\nHora de inicio prevista:"+this.InstantToString(horaPrevista)+"\nHora de inicio real:"+this.InstantToString(horaInicio));
                informe.write("\nHora de fin:"+this.InstantToString(horaFin)+"\nDuración de la reunión:"+tiempoReal+"\n"+EspacioDeReunion+" de la reunión:"+EspacioEspecifico+"\nTipo de reunión:"+tipo);
                if (Asistencias.size()!=0){
                    informe.write("\nLista de participantes:");
                    for (Asistencia persona: Asistencias){
                        informe.write("\n"+persona.toString());
                    }
                }
                if (Retrasos.size()!=0) {
                    informe.write("\nLista de retrasos:");
                    for (Retraso persona : Retrasos) {
                        informe.write("\n" + persona.toString() + ". Tiempo de llegada: " + this.InstantToString(persona.getHora()));
                    }
                }
                if (Ausencias.size()!=0) {
                    informe.write("\nLista de ausencias:");
                    for (Ausencia persona : Ausencias) {
                        informe.write("\n" + persona.toString());
                    }
                }
                informe.write("\nTotal de asistentes: "+this.obtenerTotalAsistencia()+"\nPorcentaje de asistencia: "+this.obtenerProcentajeAsistencia()+"%");
                if (Notas.size()!=0) {
                    informe.write("\nNotas:");
                    for (Nota nota : Notas) {
                        informe.write("\n" + nota.getContenido());
                    }
                }
                informe.close();
                System.out.println("Se emitió informe correctamente.");
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error.");
                e.printStackTrace();
            }
        }
        catch (ReunionEnCursoException | ReunionSinIniciarException w){
            throw w;
        }

    }

}