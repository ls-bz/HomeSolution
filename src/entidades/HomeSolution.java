package entidades;

import java.time.LocalDate;
import java.util.HashMap;
//import java.util.LinkedList;
import java.util.List;

public class HomeSolution implements IHomeSolution {

    private HashMap<Integer, Proyecto> proyectos = new HashMap<>();

    @Override
    public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {

    }

    @Override
    public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {

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
        return List.of();
    }

    @Override
    public List<Tupla<Integer, String>> proyectosPendientes() {
        return List.of();
    }

    @Override
    public List<Tupla<Integer, String>> proyectosActivos() {
        return List.of();
    }

    @Override
    public Object[] empleadosNoAsignados() {
        return new Object[0];
    }

    @Override
    public boolean estaFinalizado(Integer numero) {
        return false;
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
        return new Object[0];
    }

    @Override
    public Object[] tareasDeUnProyecto(Integer numero) {
        return new Object[0];
    }

    @Override
    public String consultarDomicilioProyecto(Integer numero) {
        return "";
    }

    @Override
    public boolean tieneRestrasos(Integer legajo) {
        return false;
    }

    @Override
    public List<Tupla<Integer, String>> empleados() {
        return List.of();
    }

    @Override
    public String consultarProyecto(Integer numero) {
        return "";
    }
}
