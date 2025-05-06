import java.util.ArrayList;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> ListaEmpleados;

    public Departamento(String nombre){
        this.nombre=nombre;
    }

    public void añadirEmpleado(String apellidos, String nombre, String correo){
        ListaEmpleados.add(new Empleado(Integer.toString(ListaEmpleados.size()),apellidos,nombre,correo));
    }
    public int obtenerCantidadEmpleados(){
        return ListaEmpleados.size();
    }

    public void Invitar(Reunion reunion){
        for (Empleado listaEmpleado : ListaEmpleados) {
            listaEmpleado.Invitar(reunion);
        }
    }
}
