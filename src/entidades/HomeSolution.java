package entidades;

import java.time.LocalDate;
import java.util.*;

public class HomeSolution implements IHomeSolution {

    private Map<Integer, Proyecto> proyectos;
    private Map<Integer, Empleado> empleados;
    private int contadorProyectos;
    private int contadorEmpleados;

    public HomeSolution() {
        this.proyectos = new HashMap<>();
        this.empleados = new HashMap<>();
        this.contadorProyectos = 1;
        this.contadorEmpleados = 1;
    }

    // ============================================================
    // REGISTRO DE EMPLEADOS
    // ============================================================

    @Override
    public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
        if (nombre == null || nombre.isEmpty() || valor < 0)
            throw new IllegalArgumentException("Datos inválidos al registrar empleado.");

        EmpleadoContratado e = new EmpleadoContratado(nombre, contadorEmpleados++, valor);
        empleados.put(e.getLegajo(), e);
    }

    @Override
    public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
        if (nombre == null || nombre.isEmpty() || valor < 0 || categoria == null)
            throw new IllegalArgumentException("Datos inválidos al registrar empleado.");

        Categoria cat;
        try {
            cat = Categoria.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoría inválida: " + categoria);
        }

        EmpleadoPlanta e = new EmpleadoPlanta(nombre, contadorEmpleados++, valor, cat);
        empleados.put(e.getLegajo(), e);
    }

    // ============================================================
    // REGISTRO Y GESTIÓN DE PROYECTOS
    // ============================================================

    @Override
    public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias,
                                  String domicilio, String[] cliente, String inicio, String fin)
            throws IllegalArgumentException {

        if (titulos == null || descripcion == null || dias == null || cliente == null)
            throw new IllegalArgumentException("Datos nulos.");

        if (titulos.length != descripcion.length || titulos.length != dias.length)
            throw new IllegalArgumentException("Listas de tareas inconsistentes.");

        Cliente c = new Cliente(cliente[0], cliente[1], cliente[2]);
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaEstimadaFin = LocalDate.parse(fin);

        Proyecto p = new Proyecto(contadorProyectos++, c, domicilio, fechaInicio, fechaEstimadaFin);

        for (int i = 0; i < titulos.length; i++) {
            Tarea t = new Tarea(titulos[i], descripcion[i], (int) dias[i], null);
            p.agregarTarea(t);
        }

        proyectos.put(p.getId(), p);
    }

    // ============================================================
    // ASIGNACIÓN Y GESTIÓN DE TAREAS
    // ============================================================

    @Override
    public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
        Proyecto p = proyectos.get(numero);
        if (p == null) throw new Exception("Proyecto inexistente.");

        Tarea t = p.buscarTareaPorTitulo(titulo);
        if (t == null) throw new Exception("Tarea no encontrada.");
        if (t.estaFinalizado()) throw new Exception("Tarea ya finalizada.");

        // Buscar el primer empleado disponible
        for (Empleado e : empleados.values()) {
            if (!e.isAsignado()) {
                p.asignarEmpleadoATarea(titulo, e);
                e.asignar();
                return;
            }
        }
        throw new Exception("No hay empleados disponibles.");
    }

    @Override
    public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
        Proyecto p = proyectos.get(numero);
        if (p == null) throw new Exception("Proyecto inexistente.");

        Empleado mejor = null;
        int min = Integer.MAX_VALUE;
        for (Empleado e : empleados.values()) {
            if (!e.isAsignado() && e.getCantidadRetrasos() < min) {
                mejor = e;
                min = e.getCantidadRetrasos();
            }
        }
        if (mejor == null) throw new Exception("No hay empleados disponibles.");

        p.asignarEmpleadoATarea(titulo, mejor);
        mejor.asignar();
    }

    @Override
    public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias)
            throws IllegalArgumentException {
        Proyecto p = proyectos.get(numero);
        if (p == null) throw new IllegalArgumentException("Proyecto inexistente.");
        Tarea t = p.buscarTareaPorTitulo(titulo);
        if (t == null) throw new IllegalArgumentException("Tarea no encontrada.");
        t.registrarRetraso();
    }

    @Override
    public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias)
            throws IllegalArgumentException {
        Proyecto p = proyectos.get(numero);
        if (p == null) throw new IllegalArgumentException("Proyecto inexistente.");

        Tarea t = new Tarea(titulo, descripcion, (int) dias, null);
        p.agregarTarea(t);
    }

    @Override
    public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {
        Proyecto p = proyectos.get(numero);
        if (p == null)
            throw new IllegalArgumentException("Proyecto no encontrado");
            
        Tarea t = p.buscarTareaPorTitulo(titulo);
        if (t == null)
            throw new IllegalArgumentException("Tarea no encontrada");
            
        if (!t.tieneEmpleadoAsignado())
            throw new Exception("La tarea no tiene empleado asignado");
            
        Empleado nuevoEmpleado = empleados.get(legajo);
        if (nuevoEmpleado == null)
            throw new IllegalArgumentException("Empleado no encontrado");
            
        if (nuevoEmpleado.isAsignado())
            throw new Exception("El empleado ya está asignado");
            
        Empleado empleadoAnterior = t.getEmpleadoResponsable();
        empleadoAnterior.liberar();
        t.asignarEmpleado(nuevoEmpleado);
        nuevoEmpleado.asignar();
    }

    @Override
    public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {
        Proyecto p = proyectos.get(numero);
        if (p == null)
            throw new IllegalArgumentException("Proyecto no encontrado");
            
        Tarea t = p.buscarTareaPorTitulo(titulo);
        if (t == null)
            throw new IllegalArgumentException("Tarea no encontrada");
            
        if (!t.tieneEmpleadoAsignado())
            throw new Exception("La tarea no tiene empleado asignado");
            
        Empleado empleadoAnterior = t.getEmpleadoResponsable();
        
        Empleado mejor = null;
        int minRetrasos = Integer.MAX_VALUE;
        
        for (Empleado e : empleados.values()) {
            if (!e.isAsignado() && e.getCantidadRetrasos() < minRetrasos) {
                mejor = e;
                minRetrasos = e.getCantidadRetrasos();
            }
        }
        
        if (mejor == null)
            throw new Exception("No hay empleados disponibles");
            
        empleadoAnterior.liberar();
        t.asignarEmpleado(mejor);
        mejor.asignar();
    }

    @Override
    public void finalizarTarea(Integer numero, String titulo) throws Exception {
        Proyecto p = proyectos.get(numero);
        if (p == null) throw new Exception("Proyecto inexistente.");
        Tarea t = p.buscarTareaPorTitulo(titulo);
        if (t == null) throw new Exception("Tarea no encontrada.");
        if (t.estaFinalizado()) throw new Exception("Tarea ya finalizada.");
        t.finalizarTarea();
    }

    @Override
    public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
        Proyecto p = proyectos.get(numero);
        if (p == null) throw new IllegalArgumentException("Proyecto inexistente.");
        p.finalizarProyecto();
    }

    // ============================================================
    // CONSULTAS
    // ============================================================

    @Override
    public double costoProyecto(Integer numero) {
        Proyecto p = proyectos.get(numero);
        return p != null ? p.calcularCostoFinal() : 0;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosFinalizados() {
        List<Tupla<Integer, String>> lista = new ArrayList<>();
        for (Proyecto p : proyectos.values()) {
            if (p.getEstado() == EstadoProyecto.FINALIZADO)
                lista.add(new Tupla<>(p.getId(), p.getDomicilio()));
        }
        return lista;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosPendientes() {
        List<Tupla<Integer, String>> lista = new ArrayList<>();
        for (Proyecto p : proyectos.values()) {
            if (p.getEstado() == EstadoProyecto.EN_CURSO)
                lista.add(new Tupla<>(p.getId(), p.getDomicilio()));
        }
        return lista;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosActivos() {
        List<Tupla<Integer, String>> lista = new ArrayList<>();
        for (Proyecto p : proyectos.values()) {
            if (p.getEstado() == EstadoProyecto.EN_CURSO)
                lista.add(new Tupla<>(p.getId(), p.getDomicilio()));
        }
        return lista;
    }

    @Override
    public Object[] empleadosNoAsignados() {
        List<Integer> libres = new ArrayList<>();
        for (Empleado e : empleados.values()) {
            if (!e.isAsignado()) libres.add(e.getLegajo());
        }
        return libres.toArray();
    }

    @Override
    public boolean estaFinalizado(Integer numero) {
        Proyecto p = proyectos.get(numero);
        return p != null && p.getEstado() == EstadoProyecto.FINALIZADO;
    }

    @Override
    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        Empleado e = empleados.get(legajo);
        return e != null ? e.getCantidadRetrasos() : 0;
    }

    @Override
    public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
        Proyecto p = proyectos.get(numero);
        if (p == null) return List.of();
        List<Tupla<Integer, String>> lista = new ArrayList<>();
        for (Empleado e : p.getHistorialEmpleados()) {
            lista.add(new Tupla<>(e.getLegajo(), e.getNombre()));
        }
        return lista;
    }

    @Override
    public Object[] tareasProyectoNoAsignadas(Integer numero) {
        Proyecto p = proyectos.get(numero);
        if (p == null) return new Object[0];
        List<String> sinAsignar = new ArrayList<>();
        for (Tarea t : p.getTareas()) {
            if (t.getEmpleadoResponsable() == null) sinAsignar.add(t.getTitulo());
        }
        return sinAsignar.toArray();
    }

    @Override
    public Object[] tareasDeUnProyecto(Integer numero) {
        Proyecto p = proyectos.get(numero);
        if (p == null) return new Object[0];
        List<String> titulos = new ArrayList<>();
        for (Tarea t : p.getTareas()) {
            titulos.add(t.getTitulo());
        }
        return titulos.toArray();
    }

    @Override
    public String consultarDomicilioProyecto(Integer numero) {
        Proyecto p = proyectos.get(numero);
        return p != null ? p.getDomicilio() : "";
    }

    @Override
    public boolean tieneRestrasos(Integer legajo) {
        Empleado e = empleados.get(legajo);
        return e != null && e.tuvoRetrasos();
    }

    @Override
    public List<Tupla<Integer, String>> empleados() {
        List<Tupla<Integer, String>> lista = new ArrayList<>();
        for (Empleado e : empleados.values()) {
            lista.add(new Tupla<>(e.getLegajo(), e.getNombre()));
        }
        return lista;
    }

    @Override
    public String consultarProyecto(Integer numero) {
        Proyecto p = proyectos.get(numero);
        return p != null ? p.toString() : "";
    }
}
