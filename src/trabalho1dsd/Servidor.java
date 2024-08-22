/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd;


import trabalho1dsd.dao.*;
import trabalho1dsd.model.Jogador;
import trabalho1dsd.model.Pessoa;
import trabalho1dsd.model.Tecnico;
import trabalho1dsd.model.Time;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 10068610920
 */
public class Servidor {
    private static TimeDAO timeDAO = new TimeDAOImpl();
    private static JogadorDAO jogadorDAO = new JogadorDAOImpl();
    private static TecnicoDAO tecnicoDAO = new TecnicoDAOImpl();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        server.setReuseAddress(true);

        while (true) {
            System.out.println("Aguardando conexão...");
            try (Socket conn = server.accept();) {
                System.out.println("Concectado com: " + conn.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);

                String mensagemRecebida = in.readLine();
                System.out.println(mensagemRecebida);
                String[] campos = mensagemRecebida.split(";");

                switch (campos[0]) {
                    case "INSERT_JOGADOR": {
                        insertJogador(campos, out);
                        break;
                    }
                    case "UPDATE_JOGADOR": {
                        updateJogador(campos, out);
                        break;
                    }
                    case "GET_JOGADOR": {
                        getJogador(campos, out);
                        break;
                    }
                    case "DELETE_JOGADOR": {
                        deleteJogador(campos, out);
                        break;
                    }
                    case "LIST_JOGADOR": {
                        listJogador(campos, out);
                        break;
                    }
                    case "INSERT_TECNICO": {
                        insertTecnico(campos, out);
                        break;
                    }
                    case "UPDATE_TECNICO": {
                        updateTecnico(campos, out);
                        break;
                    }
                    case "GET_TECNICO": {
                        getTecnico(campos, out);
                        break;
                    }
                    case "DELETE_TECNICO": {
                        deleteTecnico(campos, out);
                        break;
                    }
                    case "LIST_TECNICO": {
                        listTecnico(campos, out);
                        break;
                    }
                    case "INSERT_TIME": {
                        insertTime(campos, out);
                        break;
                    }
                    case "UPDATE_TIME": {
                        updateTime(campos, out);
                        break;
                    }
                    case "GET_TIME": {
                        getTime(campos, out);
                        break;
                    }
                    case "DELETE_TIME": {
                        deleteTime(campos, out);
                        break;
                    }
                    case "LIST_TIME": {
                        listTime(campos, out);
                        break;
                    }
                    case "INSERT_JOGADOR_TIME": {
                        insertJogadorTime(campos, out);
                        break;
                    }
                    case "DELETE_JOGADOR_TIME": {
                        deleteJogadorTime(campos, out);
                        break;
                    }
                    case "INSERT_TECNICO_TIME": {
                        insertTecnicoTime(campos, out);
                        break;
                    }
                    case "DELETE_TECNICO_TIME": {
                        deleteTecnicoTime(campos, out);
                        break;
                    }
                    default:
                        out.println("Opção indisponível.");
                }
            }
        }
    }

    private static void insertJogador(String[] campos, PrintWriter out) {
        if (jogadorDAO.encontrarPorCpf(campos[1]) == null) {
            Jogador j = new Jogador(campos[1], campos[2], campos[3], campos[4], campos[5], Integer.parseInt(campos[6]), null);
            jogadorDAO.salvar(j);
            out.println("Jogador cadastrado com sucesso");
        } else {
            out.println("Já existe um jogador com este cpf cadastrado");
        }
    }

    private static void updateJogador(String[] campos, PrintWriter out) {
        Jogador j = jogadorDAO.encontrarPorCpf(campos[1]);
        if (j != null) {
            j.setNome(campos[2]);
            j.setEndereco(campos[3]);
            j.setPosicao(campos[4]);
            j.setNacionalidade(campos[5]);
            j.setPosicao(campos[6]);
            out.println("Jogador atualizado com sucesso");
        } else {
            out.println("Jogador não encontrado");
        }
    }

    private static void getJogador(String[] campos, PrintWriter out) {
        if (jogadorDAO.getJogadores().isEmpty()) {
            out.println("Sem jogadores cadastrados");
        } else {
            Jogador j = jogadorDAO.encontrarPorCpf(campos[1]);
            if (j != null) {
                out.println(j.toString());
            } else {
                out.println("Jogador não encontrado");
            }
        }
    }

    private static void deleteJogador(String[] campos, PrintWriter out) {
        if (jogadorDAO.getJogadores().isEmpty()) {
            out.println("Sem jogadores cadastrados");
        } else {
            Jogador j = jogadorDAO.encontrarPorCpf(campos[1]);
            if (j != null) {
                jogadorDAO.excluir(j);
                timeDAO.removerDeTodosTimes(campos[1]);
                out.println("Jogador removido com sucesso");
            } else {
                out.println("Jogador não encontrado");
            }
        }
    }

    private static void listJogador(String[] campos, PrintWriter out) {
        System.out.println(jogadorDAO.getJogadores().size());
        if (jogadorDAO.getJogadores().isEmpty()) {
            out.println(0);
        } else {
            out.println(jogadorDAO.getJogadores().size());
            for (Jogador j : jogadorDAO.getJogadores()) {
                out.println(j.toString());
            }
        }
    }


    private static void insertTecnico(String[] campos, PrintWriter out) {
        if (tecnicoDAO.encontrarPorCpf(campos[1]) == null) {
            Tecnico t = new Tecnico(campos[1], campos[2], campos[3], campos[4], campos[5], null);
            tecnicoDAO.salvar(t);
            out.println("Técnico cadastrado com sucesso");
        } else {
            out.println("Já foi cadastrado um técnico com este cpf");
        }
    }

    private static void updateTecnico(String[] campos, PrintWriter out) {
        Tecnico t = tecnicoDAO.encontrarPorCpf(campos[1]);
        if (t != null) {
            t.setNome(campos[2]);
            t.setEndereco(campos[3]);
            t.setVigenciaInicio(campos[4]);
            t.setVigenciaTermino(campos[5]);
            out.println("Técnico atualizado com sucesso");
        } else {
            out.println("Técnico não encontrado");
        }
    }

    private static void getTecnico(String[] campos, PrintWriter out) {
        if (tecnicoDAO.getTecnicos().isEmpty()) {
            out.println("Sem técnicos cadastrados");
        } else {
            Tecnico t = tecnicoDAO.encontrarPorCpf(campos[1]);
            if (t != null) {
                out.println(t.toString());
            } else {
                out.println("Técnico não encontrado");
            }
        }
    }

    private static void deleteTecnico(String[] campos, PrintWriter out) {
        if (tecnicoDAO.getTecnicos().isEmpty()) {
            out.println("Sem técnicos cadastrados");
        } else {
            Tecnico t = tecnicoDAO.encontrarPorCpf(campos[1]);
            if (t != null) {
                tecnicoDAO.excluir(t);
                timeDAO.removerDeTodosTimes(campos[1]);
                out.println("Técnico removido com sucesso");
            } else {
                out.println("Técnico não encontrado");
            }
        }
    }

    private static void listTecnico(String[] campos, PrintWriter out) {
        if (tecnicoDAO.getTecnicos().isEmpty()) {
            out.println(0);
        } else {
            out.println(tecnicoDAO.getTecnicos().size());
            for (Tecnico t : tecnicoDAO.getTecnicos()) {
                out.println(t.toString());
            }
        }
    }

    private static void insertTime(String[] campos, PrintWriter out) {
        if (timeDAO.encontrarPorNome(campos[1]) == null) {
            Time t = new Time(campos[1], campos[2], campos[3], Integer.parseInt(campos[4]));
            timeDAO.salvar(t);
            out.println("Time cadastrado com sucesso");
        } else {
            out.println("Já existe um time com este nome cadastrado");
        }
    }

    private static void updateTime(String[] campos, PrintWriter out) {
        Time t = timeDAO.encontrarPorNome(campos[1]);
        if (t != null) {
            t.setEndereco(campos[2]);
            t.setPais(campos[3]);
            t.setAnoFundacao(Integer.parseInt(campos[4]));
            out.println("Time atualizado com sucesso");
        } else {
            out.println("Time não encontrado");
        }
    }

    private static void getTime(String[] campos, PrintWriter out) {
        if (timeDAO.getTimes().isEmpty()) {
            out.println("Sem times cadastrados");
        } else {
            Time t = timeDAO.encontrarPorNome(campos[1]);
            if (t != null) {
                out.println(t.toString());
            } else {
                out.println("Time não encontrado");
            }
        }
    }

    private static void deleteTime(String[] campos, PrintWriter out) {
        if (timeDAO.getTimes().isEmpty()) {
            out.println("Sem times cadastrados");
        } else {
            Time t = timeDAO.encontrarPorNome(campos[1]);
            if (t != null) {
                timeDAO.excluir(t);
                out.println("Time removido com sucesso");
            } else {
                out.println("Time não encontrado");
            }
        }
    }

    private static void listTime(String[] campos, PrintWriter out) {
        if (timeDAO.getTimes().isEmpty()) {
            out.println(0);
        } else {
            out.println(timeDAO.getTimes().size());
            for (Time t : timeDAO.getTimes()) {
                out.println(t.toString());
            }
        }
    }

    private static void insertJogadorTime(String[] campos, PrintWriter out) {
        if (jogadorDAO.getJogadores().isEmpty()) {
            out.println("Sem jogadores cadastrados");
        } else {

            if (timeDAO.getTimes().isEmpty()) {
                out.println("Sem time cadastrados");
            } else {

                Time t = timeDAO.encontrarPorNome(campos[1]);

                if (t != null) {
                    Jogador j = jogadorDAO.encontrarPorCpf(campos[2]);
                    if (j != null) {
                        Pessoa pessoaExistente = t.buscarPessoaPorCpf(campos[2]);
                        if (pessoaExistente != null && j.getCpf().equalsIgnoreCase(pessoaExistente.getCpf())) {
                            out.println("Há um jogador com o mesmo cpf já inserido no time");
                        } else {
                            t.addPessoa(j);
                            out.println("Jogador inserido no time com sucesso");
                        }
                    } else {
                        out.println("Jogador não encontrado");
                    }
                } else {
                    out.println("Time não encontrado");
                }
            }
        }
    }

    private static void deleteJogadorTime(String[] campos, PrintWriter out) {
        if (jogadorDAO.getJogadores().isEmpty()) {
            out.println("Sem jogadores cadastrados");
        } else {

            if (timeDAO.getTimes().isEmpty()) {
                out.println("Sem times cadastrados");
            } else {

                Time t = timeDAO.encontrarPorNome(campos[1]);
                if (t != null) {
                    Pessoa j = t.buscarPessoaPorCpf(campos[2]);
                    if (j != null) {
                        t.removePessoa(j);
                        out.println("Jogador removido do time com sucesso");
                    } else {
                        out.println("Jogador não encontrado");
                    }
                } else {
                    out.println("Time não encontrado");
                }
            }
        }
    }

    private static void insertTecnicoTime(String[] campos, PrintWriter out) {
        if (tecnicoDAO.getTecnicos().isEmpty()) {
            out.println("Sem técnicos cadastrados");
        } else {

            if (timeDAO.getTimes().isEmpty()) {
                out.println("Sem time cadastrados");
            } else {

                Time t = timeDAO.encontrarPorNome(campos[1]);
                if (t != null) {
                    Tecnico tec = tecnicoDAO.encontrarPorCpf(campos[2]);
                    if (tec != null) {
                        Pessoa pessoaExistente = t.buscarPessoaPorCpf(campos[2]);
                        if (pessoaExistente != null && tec.getCpf().equalsIgnoreCase(pessoaExistente.getCpf())) {
                            out.println("Há um técnico com o mesmo cpf já inserido no time");
                        } else {
                            t.addPessoa(tec);
                            out.println("Técnico inserido no time com sucesso");
                        }
                    } else {
                        out.println("Técnico não encontrado");
                    }
                } else {
                    out.println("Time não encontrado");
                }
            }
        }
    }

    private static void deleteTecnicoTime(String[] campos, PrintWriter out) {
        if (tecnicoDAO.getTecnicos().isEmpty()) {
            out.println("Sem técnicos cadastrados");
        } else {

            if (timeDAO.getTimes().isEmpty()) {
                out.println("Sem times cadastrados");
            } else {

                Time t = timeDAO.encontrarPorNome(campos[1]);
                if (t != null) {
                    Pessoa tec = t.buscarPessoaPorCpf(campos[2]);
                    if (tec != null) {
                        t.removePessoa(tec);
                        out.println("Técnico removido do time com sucesso");
                    } else {
                        out.println("Técnico não encontrado");
                    }
                } else {
                    out.println("Time não encontrado");
                }
            }
        }
    }
}
