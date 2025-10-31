package entidades;

public class EmpleadoContratado extends Empleado {
    private double valorHora;
    public EmpleadoContratado(String nombre,int legajo, double valorHora) {
        super(nombre, legajo);
        this.valorHora = valorHora;
    }
    public double calcularPago(double horas){
        return horas * this.valorHora;
    }

    
}
