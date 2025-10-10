public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numero, String cliente) {
        super(numero, cliente);
    }

    @Override
    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
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
    public String toString() {
        return super.toString() + " | Tipo: Conta Poupança";
    }
}
