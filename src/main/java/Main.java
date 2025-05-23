import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Departamento ventas = new Departamento("ventas");
        ventas.añadirEmpleado("Juarez","Diorgenes","cinico@hola.com");
        ventas.añadirEmpleado("Mario","Mario","turtlefighter@hola.com");
        ventas.añadirEmpleado("Mario","Luigi","pepoterapato@hola.com");
        Persona maria= new Persona("1","Magdalena","Maria","marialaquellora@hola.com");
        Empleado juarez = ventas.getEmpleado(0);
        ReunionPresencial r = juarez.organizarReunionPresencial(Duration.ofHours(2),20,05,2025,"Salon7",tipoReunion.MARKETING);
        r.Invitar(Instant.now(),ventas);
        r.Invitar(Instant.now(),maria);
        r.iniciar();
        juarez.UnirseAReunion(Instant.now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        maria.UnirseAReunion(Instant.now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.crearNota("Está reunión es muy informativa");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.crearNota("Está reunión está finalizando");
        r.finalizar();
        System.out.println(r.obtenerAsistencias());
        System.out.println(r.obtenerAunsencias());
        r.emitirInforme();
    }
}
