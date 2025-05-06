import java.util.ArrayList;

public class Departamento {
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
}
