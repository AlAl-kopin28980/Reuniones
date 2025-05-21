import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Departamento ventas = new Departamento("ventas");
        ventas.añadirEmpleado("Juarez","Diorgenes","cinico@hola.com");
        ventas.añadirEmpleado("Mario","Mario","turtlefighter@hola.com");
        ventas.añadirEmpleado("Mario","Luigi","pepoterapato@hola.com");
        Empleado juarez = ventas.getEmpleado(0);
        Reunion r = juarez.organizarReunionPresencial(Duration.ofHours(2),20,05,2025,"Salon7",tipoReunion.MARKETING);
        r.Invitar(Instant.now(),ventas);
        r.iniciar();
        juarez.UnirseAReunion(Instant.now());
        r.finalizar();
        for (int i=0; i< ventas.obtenerCantidadEmpleados(); i++){
            ventas.getEmpleado(i).UnirseAReunion(Instant.now());
        }
        System.out.println(r.obtenerAsistencias());
        System.out.println(r.obtenerAunsencias());
        r.emitirInforme();
    }
}
