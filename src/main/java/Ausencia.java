public class Ausencia {
    private Persona persona;

    /**
     *
     * @param e Persona falta a la reunion
     */
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
