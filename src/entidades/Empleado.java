package entidades;

public abstract class Empleado {
    private String nombre;
    private int legajo;
    private boolean asignado;
    private int cantidadRetrasos;
    public Empleado(String nombre, int legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.asignado = false;
        this.cantidadRetrasos = 0;
    }
    public void asignar(){
        this.asignado = true;
    }
    public void liberar(){
        this.asignado = false;
    }
    public void incrementarRetraso(){
        this.cantidadRetrasos += 1;
    }
    public boolean isAsignado(){
        return this.asignado;
    }
    public int getCantidadRetrasos(){
        return this.cantidadRetrasos;
    }
    public boolean tuvoRetrasos(){
        return this.cantidadRetrasos > 0;
    }
    public abstract double calcularPago(double horas);
    public int getLegajo(){
        return this.legajo;
    }
    public String getNombre(){
        return this.nombre;
    }

}
