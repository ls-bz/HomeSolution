package entidades;

public class Cliente {
    private String nombre;
    private String telefono;
    private String email;
    public Cliente(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
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
}
