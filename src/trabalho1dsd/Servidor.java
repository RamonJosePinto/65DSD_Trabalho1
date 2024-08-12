/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 10068610920
 */
public class Servidor {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        ServerSocket server = new ServerSocket(80);
        server.setReuseAddress(true);
        //TODO: de primeiro momento vai ficar aqui, necessário criar um DAO ou algo do tipo para a lista de pessoas
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        while (true) {
            System.out.println("Aguardando conexão...");
            try (Socket conn = server.accept();) {
                System.out.println("Concectado com: " + conn.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);

                String mensagemRecebida = in.readLine();
                System.out.println(mensagemRecebida);
                String[] campos = mensagemRecebida.split(";");
                for (int i = 0; i < campos.length; i++) {
                    System.out.println(campos[i]);
                }

                switch (campos[0]) {
                    case "INSERT": {
                        Pessoa p = new Pessoa(campos[1], campos[2], campos[3]);
                        pessoas.add(p);
                        break;
                    }
                    case "UPDATE": {
                        Pessoa pessoa = encontrarPessoaPorCpf(pessoas, campos[1]);
                        if (pessoa != null) {
                            pessoa.setNome(campos[2]);
                            pessoa.setEndereco(campos[3]);
                            out.println("Pessoa atualizada com sucesso");
                        } else {
                            out.println("Pessoa não encontrada");
                        }
                        break;
                    }
                    case "GET": {
                        if (pessoas.size() == 0) {
                            out.println("Sem pessoas cadastradas");
                            break;
                        }
                        Pessoa p = encontrarPessoaPorCpf(pessoas, campos[1]);
                        if (p != null) {
                            out.println(p.getCpf() + ";" + p.getNome() + ";" + p.getEndereco());
                        } else {
                            out.println("Pessoa não encontrada");
                        }
                        break;
                    }
                    case "DELETE": {
                        if (pessoas.size() == 0) {
                            out.println("Sem pessoas cadastradas");
                            break;
                        }
                        Pessoa p = encontrarPessoaPorCpf(pessoas, campos[1]);
                        if (p != null) {
                            pessoas.remove(p);
                            out.println("Pessoa removida com sucesso");
                        } else {
                            out.println("Pessoa não encontrada");
                        }
                        break;
                    }
                    case "LIST": {
                        if (pessoas.size() == 0) {
                            out.println(0);
                            break;
                        }
                        out.println(pessoas.size());
                        for (Pessoa p : pessoas) {
                            out.println(p.getCpf() + ";" + p.getNome() + ";" + p.getEndereco());
                        }
                        break;
                    }
                    default:
                        throw new AssertionError();
                }

            }
        }
    }

    public static Pessoa encontrarPessoaPorCpf(List<Pessoa> pessoas, String cpf) {
        for (Pessoa p : pessoas) {
            if (p.getCpf().equalsIgnoreCase(cpf)) {
                return p;
            }
        }
        return null; 
    }
}
