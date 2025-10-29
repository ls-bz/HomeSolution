package entidades;

public class EmpleadoDePlanta extends Empleado {
    private double valorDia;
    private Categoria categoria;

    EmpleadoDePlanta(String nombre, double valorDia, Categoria cat) {
        super(nombre);
        this.valorDia = valorDia;
        this.categoria = cat;
    }

    @Override
    public double calcularPago(double horas) {
        return 0;
    }

}
