public class ContaCorrente extends Conta implements ITributavel {

    public ContaCorrente(int numero, String cliente) {
        super(numero, cliente);
    }

    @Override
    public boolean sacar(double valor) {
        double taxa = valor * 0.05;
        double total = valor + taxa;

        if (saldo >= total) {
            saldo -= total;
            System.out.println("Saque realizado com taxa de 5%. Valor total debitado: R$ " + total);
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
    }

    @Override
    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência concluída com sucesso!");
            return true;
        }
        return false;
    }

    @Override
    public double calculaTributos() {
        return saldo * 0.01;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Conta Corrente";
    }
}
