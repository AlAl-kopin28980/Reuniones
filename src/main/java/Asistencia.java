public class Asistencia {
    private Persona persona;

    /**
     *
     * @param e Persona que asiste a la reunion
     */
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
