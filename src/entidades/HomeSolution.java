package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HomeSolution implements IHomeSolution {
    private HashMap<Integer, Proyecto> proyectos = new HashMap<>();
    private HashMap<Integer, Empleado> empleados = new HashMap<>();

    @Override
    public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
        EmpleadoContratado empleado = new EmpleadoContratado(nombre, valor);
        this.empleados.put(empleado.getLegajo(), empleado);
    }

    @Override
    public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
        Categoria cat;

        try {
            cat = Categoria.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoría inválida: " + categoria);
        }

        EmpleadoDePlanta empleado = new EmpleadoDePlanta(nombre, valor, cat);
        this.empleados.put(empleado.getLegajo(), empleado);
    }

    @Override
    public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio, String[] cliente, String inicio, String fin) throws IllegalArgumentException {
        if (titulos.length != descripcion.length || titulos.length != dias.length) {
            throw new RuntimeException("...");
        }

        Cliente c = new Cliente(cliente[0], cliente[1], cliente[2], domicilio);

        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaEstimadaFin = LocalDate.parse(fin);
        Proyecto proyecto = new Proyecto(c, fechaInicio, fechaEstimadaFin);

        for (int i = 0; i < titulos.length; i++) {
            Tarea t = new Tarea(titulos[i], descripcion[i], dias[i]);
            proyecto.agregarTarea(t);
        }
        this.proyectos.put(proyecto.getId(), proyecto);
    }

    @Override
    public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {

    }

    @Override
    public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
        Proyecto p = proyectos.get(numero);
        if (p == null) return;
        Tarea t = p.getTarea(titulo);
        if (t == null || t.estaAsignada()) return;

        Empleado responsable = null;
        int menorRetraso = Integer.MAX_VALUE;

        for (Empleado e : this.empleados.values()) {
            if (!e.isAsignado()) {
                if (e.getCantidadRetrasos() < menorRetraso) {
                    responsable = e;
                }
            }
        }

        t.asignarEmpleado(responsable);
    }

    @Override
    public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias) throws IllegalArgumentException {

    }

    @Override
    public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias) throws IllegalArgumentException {

    }

    @Override
    public void finalizarTarea(Integer numero, String titulo) throws Exception {

    }

    @Override
    public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {

    }

    @Override
    public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {

    }

    @Override
    public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {

    }

    @Override
    public double costoProyecto(Integer numero) {
        return 0;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosFinalizados() {

        LinkedList<Tupla<Integer, String>> proyectosFinalizados = new LinkedList<>();

        for (Proyecto p : this.proyectos.values()) {

            if (p.getEstado().equals(Estado.finalizado)) {
                Tupla<Integer, String> datos = new Tupla<>(p.getId(), p.getCliente().getDomicilio());
                proyectosFinalizados.add(datos);
            }

        }

        return proyectosFinalizados;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosPendientes() {

        LinkedList<Tupla<Integer, String>> proyectosPendientes = new LinkedList<>();

        for (Proyecto p : this.proyectos.values()) {

            if (p.getEstado().equals(Estado.pendiente)) {
                Tupla<Integer, String> datos = new Tupla<>(p.getId(), p.getCliente().getDomicilio());
                proyectosPendientes.add(datos);
            }

        }

        return proyectosPendientes;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosActivos() {
        LinkedList<Tupla<Integer, String>> proyectosActivos = new LinkedList<>();

        for (Proyecto p : this.proyectos.values()) {

            if (p.getEstado().equals(Estado.activo)) {
                Tupla<Integer, String> datos = new Tupla<>(p.getId(), p.getCliente().getDomicilio());
                proyectosActivos.add(datos);
            }

        }

        return proyectosActivos;
    }

    @Override
    public Object[] empleadosNoAsignados() {
        return new Object[0];
    }

    @Override
    public boolean estaFinalizado(Integer numero) {
        Proyecto p = proyectos.get(numero);
        return (p != null) && Estado.finalizado.equals(p.getEstado());
    }

    @Override
    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        return 0;
    }

    @Override
    public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
        return List.of();
    }

    @Override
    public Object[] tareasProyectoNoAsignadas(Integer numero) {
        List<Tarea> tareasNoAsignadas = new ArrayList<>();
        Proyecto p = this.proyectos.get(numero);

        if (p == null) return new Object[0];

        for (Tarea t : p.getTareas()) {
            if (!t.estaAsignada()) {
                tareasNoAsignadas.add(t);
            }
        }

        return tareasNoAsignadas.toArray();
    }

    @Override
    public Object[] tareasDeUnProyecto(Integer numero) {

        List<Tarea> tareasAsignadas = new ArrayList<>();
        Proyecto p = this.proyectos.get(numero);

        if (p == null) return new Object[0];

        for (Tarea t : p.getTareas()) {
            if (t.estaAsignada()) {
                tareasAsignadas.add(t);
            }
        }

        return tareasAsignadas.toArray();
    }

    @Override
    public String consultarDomicilioProyecto(Integer numero) {
        Proyecto p = this.proyectos.get(numero);
        if (p != null) {
            return p.getDomicilio();
        }
        return "";
    }

    @Override
    public boolean tieneRestrasos(Integer legajo) {
        Empleado e = this.empleados.get(legajo);
        return (e != null) && e.tuvoRetrasos();
    }

    @Override
    public List<Tupla<Integer, String>> empleados() {

        LinkedList<Tupla<Integer, String>> empleadosTotales = new LinkedList<>();

        for (Empleado e : this.empleados.values()) {
            Tupla<Integer, String> empleado = new Tupla<>(e.getLegajo(), e.getNombre());
            empleadosTotales.add(empleado);
        }

        return empleadosTotales;
    }

    @Override
    public String consultarProyecto(Integer numero) {
        return "";
    }
}
