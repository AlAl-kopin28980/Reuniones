import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Test1 {
    private Departamento ventas;
    private Persona maria;
    private Empleado juarez;
    private ReunionPresencial r;

    @BeforeEach
    void setUp() {
        ventas = new Departamento("ventas");
        ventas.añadirEmpleado("Juarez", "Diorgenes", "cinico@hola.com");
        ventas.añadirEmpleado("Mario", "Mario", "turtlefighter@hola.com");
        ventas.añadirEmpleado("Mario", "Luigi", "pepoterapato@hola.com");
        maria = new Persona("1", "Magdalena", "Maria", "marialaquellora@hola.com");
        juarez = ventas.getEmpleado(0);
        r = juarez.organizarReunionPresencial(Instant.now(), Duration.ofHours(2), 20, 05, 2025, "Salon7", tipoReunion.MARKETING);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test: Invitar a un empleado")
    public void testInvitarEmpleado() throws Exception {
        r.Invitar(Instant.now(), juarez);
        r.iniciar();
        juarez.UnirseAReunion(Instant.now());
        r.obtenerAsistencias();
        r.obtenerAunsencias();
        r.finalizar();

    }

    @Test
    @DisplayName("Test: Invitar a un departamento")
    public void testInvitarDepartamento() throws Exception {
        r.Invitar(Instant.now(), ventas);
        System.out.println(r.obtenerInvitaciones());
    }

    @Test
    @DisplayName("Test: Invitar persona externa")
    public void testInvitarPersona() {
        r.Invitar(Instant.now(), maria);
        r.obtenerInvitaciones();
    }

    @Test
    @DisplayName("Test: Persona se une sin invitacion")
    public void testSinInvitación() {
        juarez.UnirseAReunion(Instant.now());
    }

    @Test
    @DisplayName("Test: crear notas")
    public void crearNotas() {
        r.iniciar();
        r.crearNota("La reunión empieza");
        r.obtenerNotas();
    }

    @Test
    @DisplayName("Test: Asistencias, ausencias y retrasos")
    public void testLlegada() {
        r.Invitar(Instant.now(), ventas);
        r.Invitar(Instant.now(), maria);
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.finalizar();
        r.obtenerAunsencias();
        r.obtenerAsistencias();
        r.obtenerRetrasos();
    }

    @Test
    @DisplayName("Test: emitir informe")
    public void crearInforme() {
        r.Invitar(Instant.now(), ventas);
        r.Invitar(Instant.now(), maria);
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
        r.emitirInforme();
    }
}