package entidades;

public class Tarea {
    private String titulo;
    private double duracion;
    private String descripcion;
    private int diasRetraso = 0;
    private int vecesConRetraso = 0;
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
        e.asignar();
    }
    public void finalizarTarea() {
        this.estado = EstadoTarea.TERMINADO;
        if (empleadoResponsable != null) {
            empleadoResponsable.liberar();
            empleadoResponsable = null;
        }
    }
    public void registrarRetraso(double dias) {
        if (dias <= 0) return;
        this.diasRetraso += dias;
        this.vecesConRetraso += 1;
        if (empleadoResponsable != null) {
            this.empleadoResponsable.incrementarRetraso();
        }
    }
    public boolean tieneEmpleadoAsignado() {
        return empleadoResponsable != null;
    }
    public boolean tieneRetraso() {
        return this.diasRetraso > 0;
    }
    public boolean estaFinalizado() {
        return this.estado == EstadoTarea.TERMINADO;
    }
    public int getDiasRetraso() {
        return diasRetraso;
    }
    public int getVecesConRetraso() {
        return vecesConRetraso;
    }
    public double getDuracion() {
        return duracion;
    }
    public double getCosto() {
        if (empleadoResponsable == null) return 0.0;
        double horas = this.duracion * 8;
        return empleadoResponsable.calcularPago(horas);
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
    public String toString() {
        return this.titulo;
    }
}
