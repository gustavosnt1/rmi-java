package com.gustavo.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            menu();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println("Saldo: " + banco.saldo(conta));
                    break;
                }
                case 2: {
                    System.out.println("Quantidade de contas: " + banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da nova conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldo = entrada.nextDouble();
                    banco.adicionarConta(numero, saldo);
                    System.out.println("Conta adicionada com sucesso!");
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta para pesquisa:");
                    String numero = entrada.next();
                    Conta conta = banco.pesquisarConta(numero);
                    System.out.println(conta != null ? conta : "Conta não encontrada.");
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta para remoção:");
                    String numero = entrada.next();
                    boolean removido = banco.removerConta(numero);
                    System.out.println(removido ? "Conta removida com sucesso!" : "Conta não encontrada.");
                    break;
                }
                case 9: {
                    System.out.println("Saindo...");
                    break;
                }
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 9);
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI - GUSTAVO NASCIMENTO SANTOS BRITO ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }
}