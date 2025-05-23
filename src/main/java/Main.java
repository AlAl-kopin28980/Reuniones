import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Departamento ventas = new Departamento("ventas");
        ventas.añadirEmpleado("Juarez","Diorgenes","cinico@hola.com");
        ventas.añadirEmpleado("Mario","Mario","turtlefighter@hola.com");
        ventas.añadirEmpleado("Mario","Luigi","pepoterapato@hola.com");
        Persona maria= new Persona("1","Magdalena","Maria","marialaquellora@hola.com");
        Persona pedro= new Persona("2","Venegas","Pedro","pedrodelavega@hola.com");
        Empleado juarez = ventas.getEmpleado(0);
        ReunionPresencial r = juarez.organizarReunionPresencial(Instant.now(), Duration.ofHours(2),20,05,2025,"Salon7",tipoReunion.MARKETING);
        try {
            r.Invitar(Instant.now(), ventas);
            r.Invitar(Instant.now(), maria);
            r.iniciar();
            try {
                juarez.UnirseAReunion(Instant.now());
            } catch (NoTieneInvitacionException w) {
                System.out.println(w.getMessage());
            }
        }
        catch (ReunionFinalizadaException w){
            System.out.println(w.getMessage());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            r.emitirInforme();
        }
        catch(ReunionEnCursoException|ReunionSinIniciarException w){
            System.out.println(w.getMessage());
        }
        try{
        maria.UnirseAReunion(Instant.now());}
        catch(NoTieneInvitacionException | ReunionFinalizadaException w){
            System.out.println(w.getMessage());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            r.crearNota("Esta reunión es muy informativa");}
        catch(ReunionFinalizadaException w){
            System.out.println(w.getMessage());
        }
        try{
            pedro.UnirseAReunion(Instant.now());}
        catch(NoTieneInvitacionException | ReunionFinalizadaException w){
            System.out.println(w.getMessage());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            r.crearNota("Está reunión está finalizando");}
        catch(ReunionFinalizadaException w){
            System.out.println(w.getMessage());
        }
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
