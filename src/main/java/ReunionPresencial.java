package src.main.java;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReunionPresencial extends Reunion {
    private String sala;
    public ReunionPresencial(Duration duracionPrevista, int dia, int mes, int año, String sala,tipoReunion tipo) {
        super(duracionPrevista,dia, mes, año,tipo);
        this.sala=sala;
    }
    public void emitirInforme() {
        try {
            FileWriter informe = new FileWriter("InformeReunion.txt");
            informe.write("Fecha de la reunión:"+this.obtenerFecha()+"\nHora de inicio prevista:"+this.obtenerHoraInicioPrevista()+"Hora de inicio real:"+this.obtenerHoraInicio());
            informe.write("Hora de fin:"+this.obtenerHoraFin()+"\nDuración de la reunión:"+this.calcularTiempoReal()+"\nSala de la reunión:"+sala+"\nTipo de reunión:"+this.obtenerTipoReunion());
            informe.write("Lista de participantes:");
            ArrayList<Asistencia> asistencias =this.obtenerAsistencias();
            ArrayList<Ausencia> retrasos=this.obtenerRetrasos();
            for (Asistencia persona: asistencias){
                Empleado empleado=persona.getPersona();
                informe.write("\n"+empleado.getNombre()+" "+empleado.getApellidos());
            }
            informe.write("Lista de retrasos:");
            for (Ausencia persona: retrasos){
                Empleado empleado=persona.getPersona();
                informe.write("\n"+empleado.getNombre()+" "+empleado.getApellidos());
            }
            informe.close();
            System.out.println("Se emitió informe correctamente.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error error.");
            e.printStackTrace();
        }}
}
