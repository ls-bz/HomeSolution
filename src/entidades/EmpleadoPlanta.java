package entidades;

public class EmpleadoPlanta extends Empleado {
    private double valorDia;
    private Categoria categoria;

    EmpleadoPlanta(String nombre, double valorDia, Categoria cat) {
        super(nombre);
        this.valorDia = valorDia;
        this.categoria = cat;
    }

    @Override
    public double calcularPago(double horas) {
        double pago = this.valorDia;
        if (!tuvoRetrasos()) pago *= 1.02;
        return pago;
    }

    public double getValorDia() {
        return valorDia;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
