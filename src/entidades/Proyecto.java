package entidades;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Proyecto {
    private final int id;
    private static int contador = 1;
    private String estado;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaEstimadaFin;
    private LocalDate fechaFin;
    private HashMap<String, Tarea> tareas = new HashMap<>();
    private HashSet<Empleado> historialEmpleados = new HashSet<>();
    private double costoFinal;

    public Proyecto(Cliente cliente, LocalDate fechaInicio, LocalDate fechaEstimadaFin) {
        this.id = contador;
        contador++;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaFin = fechaEstimadaFin;
        this.estado = Estado.pendiente;
        this.tareas = new HashMap<>();
        this.historialEmpleados = new HashSet<>();
        this.costoFinal = 0.0;
    }
    public void agregarTarea(Tarea t){

    }

    public double calcularCostoFinal(){
        return 0.0;
    }

    public void registrarRetraso(Tarea t){

    }

    public void finalizarProyecto(){

    }

    public int getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public String getEstado(){
        return this.estado;
    }

    public String getDomicilio() {
        return this.cliente.getDomicilio();
    }

    public Tarea getTarea(String titulo) {
        return this.tareas.get(titulo);
    }

    public LinkedList<Tarea> getTareas() {
        return new LinkedList<>(this.tareas.values());
    }

}
