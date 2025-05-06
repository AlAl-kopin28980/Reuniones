public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;
    private Reunion reunion;

    public Empleado(String id, String apellidos, String nombre, String correo){
        this.id=id;
        this.apellidos=apellidos;
        this.nombre=nombre;
        this.correo=correo;
    }

    public void Invitar(Reunion reunion){
        this.reunion=reunion;
    }
}
