package entidades;

public class EmpleadoPlanta extends Empleado {
    private double valorDia;
    private Categoria categoria;
    public EmpleadoPlanta(String nombre, int legajo, double valorDia, Categoria categoria) {
        super(nombre, legajo);
        this.valorDia = valorDia;
        this.categoria = categoria;
    }
    public double calcularPago(double horas){
        double base = (horas/8.0)*valorDia;
        switch (categoria) {
            case TECNICO -> base *= 1.2;
            case EXPERTO -> base *= 1.5;
        }
        return base;
    }
}
