package entidades;

public class EmpleadoContratado extends Empleado {
    private double valorHora;


    public EmpleadoContratado(String nombre, double valorHora) {
        super(nombre);
        this.valorHora = valorHora;
    }

    @Override
    public double calcularPago(double horas) {
        return 0;
    }
}
