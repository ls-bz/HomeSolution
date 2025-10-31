package entidades;

public abstract class Empleado {
    private String nombre;
    private int legajo;
    private static int contador = 1;
    private boolean asignado;
    private int cantidadRetrasos;

    public Empleado(String nombre) {
        this.nombre = nombre;
        this.legajo = contador;
        contador++;
        this.asignado = false;
        this.cantidadRetrasos = 0;
    }

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
    public boolean isAsignado(){
        return this.asignado;
    }
    public int getLegajo(){
        return this.legajo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getCantidadRetrasos(){
        return this.cantidadRetrasos;
    }

}
