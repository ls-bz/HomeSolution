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
        double dias = horas / 8.0;
        return this.valorDia * dias;
    }

    public double getValorDia() {
        return valorDia;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
