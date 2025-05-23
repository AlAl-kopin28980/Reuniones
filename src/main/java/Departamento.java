import java.util.ArrayList;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> ListaEmpleados;

    public Departamento(String nombre){
        this.nombre=nombre;
        ListaEmpleados = new ArrayList<>();
    }

    /** Crea un empleado y lo añade a la lista
     *
     * @param apellidos Apellido del nuevo Empleado
     * @param nombre Nombre del Empleado
     * @param correo Correo del Empleado
     */
    public void añadirEmpleado(String apellidos, String nombre, String correo){
        ListaEmpleados.add(new Empleado(this.nombre + Integer.toString(ListaEmpleados.size()),apellidos,nombre,correo));
    }
    public int obtenerCantidadEmpleados(){
        return ListaEmpleados.size();
    }

    public String getNombre() {
        return nombre;
    }

    /** Devuelve un Empleado
     *
     * @param index indice del Empleado
     * @return Empleado
     */
    public Empleado getEmpleado(int index){
        return ListaEmpleados.get(index);
    }

    /** Remite la invitacion a cada empleado
     *
     * @param reunion Reunion a la que se es invitado
     */
    public void Invitar(Reunion reunion){
        for (Empleado listaEmpleado : ListaEmpleados) {
            listaEmpleado.Invitar(reunion);
        }
    }

    public String toString() {
        return nombre;
    }
}
