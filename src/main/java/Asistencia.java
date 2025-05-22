public class Asistencia {
    private Persona persona;

    public Asistencia(Persona e){
        persona=e;
    }

    public Persona getPersona() {
        return persona;
    }

    public String toString() {
        return persona.toString();
    }
}
