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

        while (true) {
            System.out.println("" +
                    " 1 - Cadastrar jogador   | 2 - Alterar jogador " +
                    "\n 3 - Recuperar jogador | 4 - Excluir jogador " +
                    "\n 5 - Listar jogador    | 6 - Cadastrar tecnico " +
                    "\n 7 - Alterar tecnico   | 8 - Recupera tecnico " +
                    "\n 9 - Excluir tecnico   | 10 - Listar tecnico  " +
                    "\n 11 - Cadastrar time   | 12 - Alterar time " +
                    "\n 13 - Recuperar time   | 14 - Excluir time " +
                    "\n 15 - Listar time      | 16 - Inserir jogador no time " +
                    "\n 17 - Inserir Tecnico no time");
            int opc = s.nextInt();

            switch (opc) {
                //1 - cadastrar pessoa
                case 1: {
                    System.out.println("* Cadastrar jogador *");

                    System.out.println("Informe o cpf");
                    String cpf = s.next();
                    System.out.println("Informe o nome");
                    s.nextLine();
                    String nome = s.nextLine();
                    System.out.println("Informe o endereço");
                    String endereco = s.nextLine();
                    System.out.println("Posição do Jogador:");
                    String posicao = s.nextLine();
                    System.out.println("Nacionalidade do Jogador:");
                    String nacionalidade = s.nextLine();
                    System.out.println("Número do Jogador:");
                    int numero = s.nextInt();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        out.println("INSERT_JOGADOR;" + cpf + ";" + nome + ";" + endereco + ";" + posicao + ";" + nacionalidade + ";" + numero);

                        conn.close();
                    }

                    break;
                }
                //2 - alterar pessoa
                case 2: {
                    System.out.println("* Alterar jogador *");

                    System.out.println("Informe o cpf do jogador a ser alterado");
                    String cpf = s.next();
                    System.out.println("Informe o nome");
                    s.nextLine();
                    String nome = s.nextLine();
                    System.out.println("Informe o endereço");
                    String endereco = s.nextLine();
                    System.out.println("Posição do Jogador:");
                    String posicao = s.nextLine();
                    System.out.println("Nacionalidade do Jogador:");
                    String nacionalidade = s.nextLine();
                    System.out.println("Número do Jogador:");
                    int numero = s.nextInt();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        out.println("UPDATE_JOGADOR;" + cpf + ";" + nome + ";" + endereco + ";" + posicao + ";" + nacionalidade + ";" + numero);

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
                    System.out.println("* Recuperar jogador *");

                    System.out.println("Informe o cpf do jogador a ser encontrado");
                    String cpf = s.next();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("GET_JOGADOR;" + cpf);

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

                    System.out.println("Informe o cpf do jogador a ser removido");
                    String cpf = s.next();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("DELETE_JOGADOR;" + cpf);

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

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("LIST_JOGADOR");

                        String resposta = in.readLine();
                        if (resposta != null && !resposta.equals("0")) {
                            int numPessoas = Integer.parseInt(resposta);
                            System.out.println(numPessoas);
                            for (int i = 0; i < numPessoas; i++) {
                                String pessoa = in.readLine();
                                System.out.println(pessoa);
                            }
                        } else {
                            System.out.println("Nenhum jogador cadastrado");
                        }
                        conn.close();
                    }
                    break;
                }
                case 6: {
                    System.out.println("* Cadastrar tecnico *");

                    System.out.println("Informe o cpf");
                    String cpf = s.next();
                    System.out.println("Informe o nome");
                    s.nextLine();
                    String nome = s.nextLine();
                    System.out.println("Informe o endereço");
                    String endereco = s.nextLine();
                    System.out.println("Informe o inicio da vigencia:");
                    String vigenciaInicio = s.nextLine();
                    System.out.println("Informe o fim da vigencia:");
                    String vigenciaFim = s.nextLine();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("INSERT_TECNICO;" + cpf + ";" + nome + ";" + endereco + ";" + vigenciaInicio + ";" + vigenciaFim);

                        conn.close();
                    }
                    break;

                }
                //2 - alterar pessoa
                case 7: {
                    System.out.println("* Alterar tecnico *");

                    System.out.println("Informe o cpf do tecnico a ser alterado");
                    String cpf = s.next();
                    System.out.println("Informe o nome");
                    s.nextLine();
                    String nome = s.nextLine();
                    System.out.println("Informe o endereço");
                    String endereco = s.nextLine();
                    System.out.println("Informe o inicio da vigencia:");
                    String vigenciaInicio = s.nextLine();
                    System.out.println("Informe o fim da vigencia:");
                    String vigenciaFim = s.nextLine();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("UPDATE_TECNICO;" + cpf + ";" + nome + ";" + endereco + ";" + vigenciaInicio + ";" + vigenciaFim);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //3 - recuperar pessoa
                case 8: {
                    System.out.println("* Recuperar tecnico *");

                    System.out.println("Informe o cpf do jogador a ser encontrado");
                    String cpf = s.next();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("GET_TECNICO;" + cpf);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //4 - excluir pessoa
                case 9: {
                    System.out.println("* Excluir tecnico *");

                    System.out.println("Informe o cpf do tecnico a ser removido");
                    String cpf = s.next();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("DELETE_TECNICO;" + cpf);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                //5 - listar pessoas
                case 10: {
                    System.out.println("* Listar pessoas *");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        out.println("LIST_TECNICO");

                        String resposta = in.readLine();
                        if (resposta != null && !resposta.equals("0")) {
                            int numPessoas = Integer.parseInt(resposta);
                            System.out.println(numPessoas);
                            for (int i = 0; i < numPessoas; i++) {
                                String pessoa = in.readLine();
                                System.out.println(pessoa);
                            }
                        } else {
                            System.out.println("Nenhum tecnico cadastrado");
                        }
                        conn.close();
                    }
                    break;
                }
                //6 - cadastrar time
                case 11: {
                    System.out.println("* Cadastrar time *");

                    System.out.println("Informe o nome do time");
                    s.nextLine();
                    String nome = s.nextLine();

                    System.out.println("Informe o endereço do time");

                    String endereco = s.nextLine();

                    System.out.println("Informe o país do time");

                    String pais = s.nextLine();

                    System.out.println("Informe o ano de fundação do time");

                    int ano = s.nextInt();

                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String mensagem = "INSERT_TIME;" + nome + ";" + endereco + ";" + pais + ";" + ano;
                        out.println(mensagem);

                        conn.close();
                    }
                    break;
                }
                //7 - alterar time
                case 12: {
                    System.out.println("* Alterar time *");
                    System.out.println("Informe o nome do time");
                    s.nextLine();
                    String nome = s.nextLine();

                    System.out.println("Informe o endereço do time");

                    String endereco = s.nextLine();

                    System.out.println("Informe o país do time");

                    String pais = s.nextLine();

                    System.out.println("Informe o ano de fundação do time");

                    int ano = s.nextInt();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
                case 13: {
                    System.out.println("* Recuperar time *");

                    System.out.println("Informe o nome do time");
                    s.nextLine();
                    String nome = s.nextLine();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
                case 14: {
                    System.out.println("* Excluir time *");

                    System.out.println("Informe o nome do time");
                    s.nextLine();
                    String nome = s.nextLine();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
                case 15: {
                    System.out.println("* Listar times *");
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
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
                case 16: {
                    // Inserir pessoa time
                    System.out.println("* Inserir jogador em time *");


                    s.nextLine();
                    System.out.println("Informe o nome do time para inserir um jogador");
                    String nome = s.nextLine();

                    System.out.println("Informe o cpf do jogador a ser inserido no time");
                    String cpf = s.next();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String mensagem = "INSERT_JOGADOR_TIME;" + nome + ";" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                case 17: {
                    // Excluir pessoa time
                    System.out.println("* Excluir jogador do time *");

                    s.nextLine();
                    System.out.println("Informe o nome do time para retirar um jogador");
                    String nome = s.nextLine();

                    System.out.println("Informe o cpf do jogador a ser retirado do time");
                    String cpf = s.next();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String mensagem = "DELETE_JOGADOR_TIME;" + nome + ";" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                case 18: {
                    // Inserir pessoa time
                    System.out.println("* Inserir tecnico em time *");


                    s.nextLine();
                    System.out.println("Informe o nome do time para inserir um tecnico");
                    String nome = s.nextLine();

                    System.out.println("Informe o cpf do jogador a ser inserido no time");
                    String cpf = s.next();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String mensagem = "INSERT_TECNICO_TIME;" + nome + ";" + cpf;
                        out.println(mensagem);

                        String resposta = in.readLine();
                        if (resposta != null) {
                            System.out.println(resposta);
                        }
                        conn.close();
                    }
                    break;
                }
                case 19: {
                    // Excluir pessoa time
                    System.out.println("* Excluir tecnico do time *");

                    s.nextLine();
                    System.out.println("Informe o nome do time para retirar um tecnico");
                    String nome = s.nextLine();

                    System.out.println("Informe o cpf do tecnico a ser retirado do time");
                    String cpf = s.next();
                    try (Socket conn = new Socket("10.15.120.171", 80);) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String mensagem = "DELETE_TECNICO_TIME;" + nome + ";" + cpf;
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
                case 20: {
                    System.out.println("* Terminar *");
                    return;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
    }
}

