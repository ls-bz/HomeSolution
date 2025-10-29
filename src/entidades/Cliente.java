package entidades;

public class Cliente {
    private String nombre;
    private String telefono;
    private String email;
    private String domicilio;

    public Cliente(String nombre, String email, String telefono, String domicilio) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDomicilio(){
        return this.domicilio;
    }
}
