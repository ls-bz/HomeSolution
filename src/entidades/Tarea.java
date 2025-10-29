package entidades;

public class Tarea {
    private String titulo;
    private String descripcion;
    private double duracion;
    private String estado;
    private Empleado empleadoResponsable;

    public Tarea(String titulo, String descripcion, double duracion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = Estado.pendiente;
    }

    public void asignarEmpleado(Empleado e){
    }

    public void finalizarTarea(){
        this.estado = Estado.finalizado;
    }

    public void registrarRetraso(){

    }

    public double getCosto(){
        return 0.0;
    }
}
