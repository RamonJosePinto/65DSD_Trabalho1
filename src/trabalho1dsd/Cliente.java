/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho1dsd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author 10068610920
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int opc = 0;
        while (opc != 6) {
            System.out.println("1 - Cadastrar pessoa \n 2 - Alterar pessoa \n 3 - Recuperar pessoa \n 4 - Excluir pessoa \n 5 - Listar pessoas \n 6 - Terminar");
            opc = s.nextInt();

            switch (opc) {
                //1 - cadastrar pessoa
                case 1: {
                    System.out.println("* Cadastrar pessoa *");
                    System.out.println("Criando conexão...");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        System.out.println("Conectado!");
                        OutputStream out = conn.getOutputStream();

                        System.out.println("Informe o cpf");
                        String cpf = s.next();
                        System.out.println("Informe o nome");
                        // TODO: é preciso disso por que senão o campo é pulado, tem haver com o scanner, talvez ver com o professor
                        s.nextLine();
                        //
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
                    s.nextLine();
                    System.out.println("* Alterar pessoa *");
                    System.out.println("* Cadastrar pessoa *");
                    System.out.println("Criando conexão...");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        System.out.println("Conectado!");
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        System.out.println("Informe o cpf da pessoa a ser alterada");
                        String cpf = s.next();
                        System.out.println("Informe o nome");
                        // TODO: é preciso disso por que senão o campo é pulado, tem haver com o scanner, talvez ver com o professor
                        s.nextLine();
                        //
                        String nome = s.nextLine();
                        System.out.println("Informe o endereço");
                        String endereco = s.nextLine();

                        String mensagem = "UPDATE;" + cpf + ";" + nome + ";" + endereco;
//                        out.write(mensagem.getBytes());
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
                    System.out.println("Criando conexão...");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        System.out.println("Conectado!");
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
                    System.out.println("Criando conexão...");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        System.out.println("Conectado!");
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
                    System.out.println("Criando conexão...");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        System.out.println("Conectado!");
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        String mensagem = "LIST";
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null && !resposta.equals("0")) {
                            int numPessoas = Integer.parseInt(resposta);
                            System.out.println("Número de pessoas cadastradas: " + numPessoas);
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
                //6 - terminar
                case 6: {
                    System.out.println("* Terminar *");
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
    }

}
