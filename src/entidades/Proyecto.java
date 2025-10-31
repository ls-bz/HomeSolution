package entidades;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Proyecto {
    private final int id;
    private static int contador = 1;
    private String domicilio;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaEstimadaFin;
    private LocalDate fechaFin;
    private EstadoProyecto estado;
    private Map<Integer, Tarea> tareas;
    private Set<Empleado> historialEmpleados;
    private double costoFinal;
    public Proyecto(Cliente cliente, String domicilio, LocalDate fechaInicio, LocalDate fechaEstimadaFin) {
        this.id = contador;
        contador++;
        this.cliente = cliente;
        this.domicilio = domicilio;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaFin = fechaEstimadaFin;
        this.estado = EstadoProyecto.EN_CURSO;
        this.tareas = new HashMap<>();
        this.historialEmpleados = new HashSet<>();
        this.costoFinal = 0.0;
    }
    public void agregarTarea(Tarea t){
        tareas.put(tareas.size()+1, t);
    }
    public Tarea buscarTareaPorTitulo(String titulo){
        for(Tarea t : tareas.values()){
            if(t.getTitulo().equalsIgnoreCase(titulo)){
                return t;
            }
        }
        return null;
    }
    public void asignarEmpleadoATarea(String titulo, Empleado e){
        Tarea t = buscarTareaPorTitulo(titulo);
        if(t!=null){
            t.asignarEmpleado(e);
            historialEmpleados.add(e);
        }
    }
    public double calcularCostoFinal(){
        double total = 0.0;
        for(Tarea t : tareas.values()){
            total+= t.getCosto();
        }
        this.costoFinal = total;
        return total;
    }
    public void registrarRetraso(Tarea t){
        t.registrarRetraso();
    }
    public void finalizarProyecto(){
        this.estado = EstadoProyecto.FINALIZADO;
        this.fechaFin = LocalDate.now();
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public String getDomicilio(){
        return this.domicilio;
    }
    public EstadoProyecto getEstado(){
        return this.estado;
    }
    public int getId(){
        return this.id;
    }
    public Collection<Tarea> getTareas(){
        return this.tareas.values();
    }
    public Set<Empleado> getHistorialEmpleados(){
        return this.historialEmpleados;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Proyecto #").append(id).append("\n");
        sb.append("Domicilio: ").append(domicilio).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Fecha inicio: ").append(fechaInicio).append("\n");
        if (fechaFin != null) {
            sb.append("Fecha fin: ").append(fechaFin).append("\n");
        }
        sb.append("Costo: $").append(costoFinal).append("\n");
        sb.append("Tareas:\n");
        for (Tarea t : tareas.values()) {
            sb.append("- ").append(t.toString()).append("\n");
        }
        return sb.toString();
}

}
