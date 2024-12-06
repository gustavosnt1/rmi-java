package com.gustavo.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private final List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return contas.stream()
                .filter(c -> c.getNumero().equals(conta))
                .findFirst()
                .map(Conta::getSaldo)
                .orElse(0.0);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String numero, double saldo) throws RemoteException {
        contas.add(new Conta(numero, saldo));
    }

    @Override
    public Conta pesquisarConta(String numero) throws RemoteException {
        return contas.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removerConta(String numero) throws RemoteException {
        return contas.removeIf(c -> c.getNumero().equals(numero));
    }
}
