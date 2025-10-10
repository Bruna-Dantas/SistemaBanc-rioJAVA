import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static int proximoNumero = 101;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n========= BANCO DEUS ACUDA =========");
            System.out.println("1. Criar Conta");
            System.out.println("2. Realizar Depósito");
            System.out.println("3. Realizar Saque");
            System.out.println("4. Realizar Transferência");
            System.out.println("5. Listar Contas");
            System.out.println("6. Calcular Total de Tributos");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> criarConta(sc);
                case 2 -> realizarDeposito(sc);
                case 3 -> realizarSaque(sc);
                case 4 -> realizarTransferencia(sc);
                case 5 -> listarContas();
                case 6 -> calcularTributos();
                case 7 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 7);

        sc.close();
    }

    private static void criarConta(Scanner sc) {
        System.out.print("Nome do cliente: ");
        String nome = sc.nextLine();

        System.out.println("Tipo de conta: ");
        System.out.println("1 - Corrente");
        System.out.println("2 - Poupança");
        int tipo = sc.nextInt();

        Conta novaConta;
        if (tipo == 1) {
            novaConta = new ContaCorrente(proximoNumero, nome);
        } else {
            novaConta = new ContaPoupanca(proximoNumero, nome);
        }

        contas.add(novaConta);
        System.out.println("Conta criada com sucesso! Número: " + proximoNumero);
        proximoNumero++;
    }

    private static void realizarDeposito(Scanner sc) {
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();
        System.out.print("Valor do depósito: ");
        double valor = sc.nextDouble();

        Conta conta = buscarConta(numero);
        if (conta != null) {
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    private static void realizarSaque(Scanner sc) {
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();
        System.out.print("Valor do saque: ");
        double valor = sc.nextDouble();

        Conta conta = buscarConta(numero);
        if (conta != null) {
            conta.sacar(valor);
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    private static void realizarTransferencia(Scanner sc) {
        System.out.print("Conta de origem: ");
        int origem = sc.nextInt();
        System.out.print("Conta de destino: ");
        int destino = sc.nextInt();
        System.out.print("Valor da transferência: ");
        double valor = sc.nextDouble();

        Conta contaOrigem = buscarConta(origem);
        Conta contaDestino = buscarConta(destino);

        if (contaOrigem != null && contaDestino != null) {
            contaOrigem.transferir(contaDestino, valor);
        } else {
            System.out.println("Conta(s) não encontrada(s).");
        }
    }

    private static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (Conta conta : contas) {
                System.out.println(conta);
            }
        }
    }

    private static void calcularTributos() {
        double totalTributos = 0.0;

        for (Conta conta : contas) {
            if (conta instanceof ITributavel tributavel) {
                totalTributos += tributavel.calculaTributos();
            }
        }

        System.out.println("\n========================================");
        System.out.println("Total de tributos a recolher: R$ " + String.format("%.2f", totalTributos));
        System.out.println("========================================");
    }

    private static Conta buscarConta(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }
}
