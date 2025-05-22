public class Ausencia {
    private Persona persona;

    public Ausencia(Persona e){
        persona=e;
    }

    public Persona getPersona() {
        return persona;
    }

    public String toString() {
        return persona.toString();
    }
}
