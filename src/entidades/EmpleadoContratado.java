package entidades;

public class EmpleadoContratado extends Empleado {
    private double valorHora;
    public EmpleadoContratado(String nombre, double valorHora) {
        super(nombre);
        this.valorHora = valorHora;
    }
    public double calcularPago(double horas){
        return horas * this.valorHora;
    }

    
}
