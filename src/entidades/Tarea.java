package entidades;

public class Tarea {
    private String titulo;
    private String descripcion;
    private int duracion;
    private EstadoTarea estado;
    private Empleado empleadoResponsable;

    public Tarea(String titulo, String descripcion, int duracion, Empleado empleadoResponsable) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = EstadoTarea.PENDIENTE;
        this.empleadoResponsable = empleadoResponsable; 
    }

    public void asignarEmpleado(Empleado e) {
        this.empleadoResponsable = e;
    }

    public void finalizarTarea() {
        this.estado = EstadoTarea.TERMINADO;
    }

    public void registrarRetraso() {
        if (empleadoResponsable != null) {
            this.empleadoResponsable.incrementarRetraso();
        }
    }

    public boolean estaFinalizado() {
        return this.estado == EstadoTarea.TERMINADO;
    }

    public double getCosto() {
        if (empleadoResponsable == null) return 0.0;
        return empleadoResponsable.calcularPago(duracion);
    }

    public String getTitulo() {
        return this.titulo;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public Empleado getEmpleadoResponsable() {
        return empleadoResponsable;
    }
    public Empleado getEmpleado() {
        return empleadoResponsable;
    }

    public boolean tieneEmpleadoAsignado() {
        return empleadoResponsable != null;
    }

    public String toString() {
        return this.titulo;
    }
}
