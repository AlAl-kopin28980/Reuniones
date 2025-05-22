import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReunionVirtual extends Reunion {
    private String enlace;
    public ReunionVirtual(Duration duracionPrevista, int dia, int mes, int año, String enlace, tipoReunion tipo) {
        super(duracionPrevista,dia, mes, año,tipo);
        this.enlace=enlace;
    }
    public void emitirInforme() {
        try {
            ArrayList<Asistencia> asistencias =this.obtenerAsistencias();
            ArrayList<Retraso> retrasos=this.obtenerRetrasos();
            ArrayList<Ausencia> ausencias =this.obtenerAunsencias();
            FileWriter informe = new FileWriter("InformeReunion.txt");
            informe.write("Fecha de la reunión:"+this.obtenerFecha()+"\nHora de inicio prevista:"+this.obtenerHoraInicioPrevista()+"\nHora de inicio real:"+this.obtenerHoraInicio());
            informe.write("\nHora de fin:"+this.obtenerHoraFin()+"\nDuración de la reunión:"+this.calcularTiempoReal()+"\nEnlace de la reunión:"+enlace+"\nTipo de reunión:"+this.obtenerTipoReunion());
            informe.write("\nLista de participantes:");
            for (Asistencia persona: asistencias){
                Persona empleado=persona.getPersona();
                informe.write("\n"+empleado.toString());
            }
            informe.write("\nLista de retrasos:");
            for (Retraso persona: retrasos){
                Persona empleado=persona.getPersona();
                informe.write("\n"+empleado.toString()+". Tiempo de llegada: "+persona.getHora());
            }
            informe.write("\nLista de ausencias:");
            for (Ausencia persona: ausencias){
                Persona empleado=persona.getPersona();
                informe.write("\n"+empleado.toString());
            }
            informe.close();
            System.out.println("Se emitió informe correctamente.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error error.");
            e.printStackTrace();
        }}
}
