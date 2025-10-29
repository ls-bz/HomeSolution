package entidades;

abstract public class Empleado {
    private String nombre;
    private static int contador = 1; // contador
    private int legajo;
    private boolean asignado;
    private int cantidadRetrasos;


    public Empleado(String nombre) {
        this.nombre = nombre;
        this.asignado = false;
        this.cantidadRetrasos = 0;

        this.legajo = contador;
        contador++;
    }

    // Uso de polimorfismo
    public abstract double calcularPago(double horas);

    public void asignar(){
        this.asignado = true;
    }

    public void liberar(){
        this.asignado = false;
    }

    public void incrementarRetraso(){
        this.cantidadRetrasos += 1;
    }

    public boolean tuvoRetrasos(){
        return this.cantidadRetrasos > 0;
    }

    public int getCantidadRetrasos(){
        return this.cantidadRetrasos;
    }

    public int getLegajo() {
        return legajo;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public String getNombre() {
        return nombre;
    }



}
