public abstract class Conta {
    protected int numero;
    protected String cliente;
    protected double saldo;

    public Conta(int numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito. Tente novamente");
        }
    }

    public abstract boolean sacar(double valor);

    public abstract boolean transferir(Conta destino, double valor);

    @Override
    public String toString() {
        return "Número: " + numero + " | Cliente: " + cliente +
               " | Saldo: R$ " + String.format("%.2f", saldo);
    }
}
