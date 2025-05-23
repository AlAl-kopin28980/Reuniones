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
        ReunionPresencial r = juarez.organizarReunionPresencial(Instant.now(), Duration.ofHours(2),20,05,2025,"Salon7",tipoReunion.MARKETING);
        r.Invitar(Instant.now(),ventas);
        r.Invitar(Instant.now(),maria);
        r.iniciar();
        try{
        juarez.UnirseAReunion(Instant.now());}
        catch(NoTieneInvitacionException w){
            System.out.println(w.getMessage());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
        maria.UnirseAReunion(Instant.now());}
        catch(NoTieneInvitacionException w){
            System.out.println(w.getMessage());
        }
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
        try {
            r.finalizar();
        } catch (ReunionSinIniciarException e) {
            System.out.println(e.getMessage());;
        }
        System.out.println(r.obtenerAsistencias());
        System.out.println(r.obtenerAunsencias());
        try {
            r.emitirInforme();
        }
        catch(ReunionEnCursoException|ReunionSinIniciarException w){
            System.out.println(w.getMessage());
        }
    }
}
