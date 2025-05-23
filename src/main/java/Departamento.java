import java.util.ArrayList;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> ListaEmpleados;

    public Departamento(String nombre){
        this.nombre=nombre;
        ListaEmpleados = new ArrayList<>();
    }

    public void añadirEmpleado(String apellidos, String nombre, String correo){
        ListaEmpleados.add(new Empleado(this.nombre + Integer.toString(ListaEmpleados.size()),apellidos,nombre,correo));
    }
    public int obtenerCantidadEmpleados(){
        return ListaEmpleados.size();
    }

    public String getNombre() {
        return nombre;
    }
    public Empleado getEmpleado(int index){
        return ListaEmpleados.get(index);
    }

    public void Invitar(Reunion reunion){
        for (Empleado listaEmpleado : ListaEmpleados) {
            listaEmpleado.Invitar(reunion);
        }
    }

    public String toString() {
        return nombre;
    }
}
