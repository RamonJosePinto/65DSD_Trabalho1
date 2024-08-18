/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho1dsd;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 10068610920
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int opc = 0;
        while (opc != 13) {
            System.out.println("" +
                    " 1 - Cadastrar pessoa        | 2 - Alterar pessoa " +
                    "\n 3 - Recuperar pessoa        | 4 - Excluir pessoa " +
                    "\n 5 - Listar pessoas          | 6 - Cadastrar time " +
                    "\n 7 - Alterar time            | 8 - Recupera time " +
                    "\n 9 - Excluir time            | 10 - Listar times  " +
                    "\n 11 - Inserir pessoa em time | 12 - Excluir pessoa do time " +
                    "\n 13 - Terminar");
            opc = s.nextInt();

            switch (opc) {
                //1 - cadastrar pessoa
                case 1: {
                    System.out.println("* Cadastrar pessoa *");

                    try (Socket conn = createSocketConnection()) {

                        OutputStream out = conn.getOutputStream();

                        System.out.println("Informe o cpf");
                        String cpf = s.next();
                        System.out.println("Informe o nome");
                        s.nextLine();
                        String nome = s.nextLine();
                        System.out.println("Informe o endereço");
                        String endereco = s.nextLine();

                        String mensagem = "INSERT;" + cpf + ";" + nome + ";" + endereco;
                        out.write(mensagem.getBytes());

                        conn.close();
                    }
                    break;
                }
                //2 - alterar pessoa
                case 2: {
                    System.out.println("* Alterar pessoa *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o cpf da pessoa a ser alterada");
                        String cpf = s.next();
                        System.out.println("Informe o nome");

                        s.nextLine();
                        String nome = s.nextLine();
                        System.out.println("Informe o endereço");
                        String endereco = s.nextLine();

                        String mensagem = "UPDATE;" + cpf + ";" + nome + ";" + endereco;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //3 - recuperar pessoa
                case 3: {
                    System.out.println("* Recuperar pessoa *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o cpf da pessoa a ser encontrada");
                        String cpf = s.next();

                        String mensagem = "GET;" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //4 - excluir pessoa
                case 4: {
                    System.out.println("* Excluir pessoa *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o cpf da pessoa a ser removida");
                        String cpf = s.next();

                        String mensagem = "DELETE;" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //5 - listar pessoas
                case 5: {
                    System.out.println("* Listar pessoas *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        String mensagem = "LIST";
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null && !resposta.equals("0")) {
                            int numPessoas = Integer.parseInt(resposta);
                            System.out.println(numPessoas);
                            for (int i = 0; i < numPessoas; i++) {
                                String pessoa = in.readLine();
                                System.out.println(pessoa);
                            }
                        } else {
                            System.out.println("Nenhuma pessoa cadastrada.");
                        }
                        conn.close();
                    }
                    break;
                }
                //6 - cadastrar time
                case 6: {
                    System.out.println("* Cadastrar time *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);

                        System.out.println("Informe o nome do time");
                        s.nextLine();
                        String nome = s.nextLine();

                        System.out.println("Informe o endereço do time");

                        String endereco = s.nextLine();

                        System.out.println("Informe o país do time");

                        String pais = s.nextLine();

                        System.out.println("Informe o ano de fundação do time");

                        int ano = s.nextInt();

                        String mensagem = "INSERT_TIME;" + nome + ";" + endereco + ";" + pais + ";" + ano;
                        out.println(mensagem);

                        conn.close();
                    }
                    break;
                }
                //7 - alterar time
                case 7: {
                    System.out.println("* Alterar time *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o nome do time");
                        s.nextLine();
                        String nome = s.nextLine();

                        System.out.println("Informe o endereço do time");

                        String endereco = s.nextLine();

                        System.out.println("Informe o país do time");

                        String pais = s.nextLine();

                        System.out.println("Informe o ano de fundação do time");

                        int ano = s.nextInt();

                        String mensagem = "UPDATE_TIME;" + nome + ";" + endereco + ";" + pais + ";" + ano;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //8 - recuperar time
                case 8: {
                    System.out.println("* Recuperar time *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o nome do time");
                        s.nextLine();
                        String nome = s.nextLine();

                        String mensagem = "GET_TIME;" + nome;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //9 - excluir time
                case 9: {
                    System.out.println("* Excluir time *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o nome do time");
                        s.nextLine();
                        String nome = s.nextLine();

                        String mensagem = "DELETE_TIME;" + nome;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //10 - listar times
                case 10: {
                    System.out.println("* Listar times *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        String mensagem = "LIST_TIME";
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null && !resposta.equals("0")) {
                            int numTimes = Integer.parseInt(resposta);
                            System.out.println("Número de times cadastrados: " + numTimes);
                            for (int i = 0; i < numTimes; i++) {
                                String time = in.readLine();
                                System.out.println(time);
                            }
                        } else {
                            System.out.println("Nenhum time cadastrado.");
                        }
                        conn.close();
                    }
                    break;
                }
                case 11: {
                    // Inserir pessoa time
                    System.out.println("* Inserir pessoa em time *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        s.nextLine();
                        System.out.println("Informe o nome do time para inserir uma pessoa");
                        String nome = s.nextLine();

                        System.out.println("Informe o cpf da pessoa a ser inserida no time");
                        String cpf = s.next();

                        String mensagem = "INSERT_PESSOA_TIME;" + nome + ";" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                case 12: {
                    // Excluir pessoa time
                    System.out.println("* Excluir pessoa em time *");

                    try (Socket conn = createSocketConnection()) {

                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        s.nextLine();
                        System.out.println("Informe o nome do time para retirar uma pessoa");
                        String nome = s.nextLine();

                        System.out.println("Informe o cpf da pessoa a ser retirada do time");
                        String cpf = s.next();

                        String mensagem = "DELETE_PESSOA_TIME;" + nome + ";" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //12 - terminar
                case 13: {
                    System.out.println("* Terminar *");
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
    }

    private static Socket createSocketConnection() throws IOException {
        return new Socket("10.15.120.53", 80);
    }
}
