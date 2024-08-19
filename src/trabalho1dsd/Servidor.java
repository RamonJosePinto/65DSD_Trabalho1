/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd;



import trabalho1dsd.dao.*;
import trabalho1dsd.model.Jogador;
import trabalho1dsd.model.Tecnico;
import trabalho1dsd.model.Time;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 10068610920
 */
public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        server.setReuseAddress(true);
        TimeDAO timeDAO = new TimeDAOImpl();
        JogadorDAO jogadorDAO = new JogadorDAOImpl();
        TecnicoDAO tecnicoDAO = new TecnicoDAOImpl();

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
                        if (jogadorDAO.encontrarPorCpf(campos[1]) == null) {
                            Jogador j = new Jogador(campos[1], campos[2], campos[3], campos[4], campos[5], Integer.parseInt(campos[6]), null);
                            jogadorDAO.salvar(j);
                            out.println("Jogador cadastrado com sucesso");
                        } else {
                            out.println("Já existe uma pessoa com este cpf cadastrado");
                        }
                        break;
                    }
                    case "UPDATE_JOGADOR": {
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
                        break;
                    }
                    case "GET_JOGADOR": {
                        if (jogadorDAO.getJogadores().isEmpty()) {
                            out.println("Sem jogadores cadastrados");
                            break;
                        }
                        Jogador j = jogadorDAO.encontrarPorCpf(campos[1]);
                        if (j != null) {
                            out.println(j.toString());
                        } else {
                            out.println("Jogador não encontrado");
                        }
                        break;
                    }
                    case "DELETE_JOGADOR": {
                        if (jogadorDAO.getJogadores().isEmpty()) {
                            out.println("Sem jogadores cadastrados");
                            break;
                        }
                        Jogador j = jogadorDAO.encontrarPorCpf(campos[1]);
                        if (j != null) {
                            jogadorDAO.excluir(j);
                            out.println("Jogador removido com sucesso");
                        } else {
                            out.println("Jogador não encontrado");
                        }
                        break;
                    }
                    case "LIST_JOGADOR": {
                        System.out.println(jogadorDAO.getJogadores().size());
                        if (jogadorDAO.getJogadores().isEmpty()) {
                            out.println(0);
                            break;
                        }
                        out.println(jogadorDAO.getJogadores().size());
                        for (Jogador j : jogadorDAO.getJogadores()) {
                            out.println(j.toString());
                        }
                        break;
                    }
                    case "INSERT_TECNICO": {
                        if(tecnicoDAO.encontrarPorCpf(campos[1]) == null){
                            Tecnico t = new Tecnico(campos[1], campos[2], campos[3], campos[4], campos[5], null);
                            tecnicoDAO.salvar(t);
                            out.println("Tecnico cadastrado com sucesso");
                        } else {
                            out.println("Já foi cadastrado uma pessoa com este cpf");
                        }
                        break;
                    }
                    case "UPDATE_TECNICO": {
                        Tecnico t = tecnicoDAO.encontrarPorCpf(campos[1]);
                        if (t != null) {
                            t.setNome(campos[2]);
                            t.setEndereco(campos[3]);
                            t.setVigenciaInicio(campos[4]);
                            t.setVigenciaTermino(campos[5]);
                            out.println("Tecnico atualizado com sucesso");
                        } else {
                            out.println("Tecnico não encontrado");
                        }
                        break;
                    }
                    case "GET_TECNICO": {
                        if (tecnicoDAO.getTecnicos().isEmpty()) {
                            out.println("Sem tecnicos cadastrados");
                            break;
                        }
                        Tecnico t = tecnicoDAO.encontrarPorCpf(campos[1]);
                        if (t != null) {
                            out.println(t.toString());
                        } else {
                            out.println("Tecnico não encontrado");
                        }
                        break;
                    }
                    case "DELETE_TECNICO": {
                        if (tecnicoDAO.getTecnicos().isEmpty()) {
                            out.println("Sem tecnicos cadastrados");
                            break;
                        }
                        Tecnico t = tecnicoDAO.encontrarPorCpf(campos[1]);
                        if (t != null) {
                            tecnicoDAO.excluir(t);
                            out.println("Tecnico removido com sucesso");
                        } else {
                            out.println("Tecnico não encontrado");
                        }
                        break;
                    }
                    case "LIST_TECNICO": {
                        if (tecnicoDAO.getTecnicos().isEmpty()) {
                            out.println(0);
                            break;
                        }
                        out.println(tecnicoDAO.getTecnicos().size());
                        for (Tecnico t : tecnicoDAO.getTecnicos()) {
                            out.println(t.toString());
                        }
                        break;
                    }
                    case "INSERT_TIME": {
                        if(timeDAO.encontrarPorNome(campos[1]) == null) {
                            Time t = new Time(campos[1], campos[2], campos[3], Integer.parseInt(campos[4]));
                            timeDAO.salvar(t);
                            out.println("Time cadastrado com sucesso");
                        } else {
                            out.println("Já existe um time com este nome cadastrado");
                        }
                        break;
                    }

                    case "UPDATE_TIME": {
                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        if (t != null) {
                            t.setEndereco(campos[2]);
                            t.setPais(campos[3]);
                            t.setAnoFundacao(Integer.parseInt(campos[4]));
                            out.println("Time atualizado com sucesso");
                        } else {
                            out.println("Time não encontrado");
                        }
                        break;
                    }
                    case "GET_TIME": {
                        if (timeDAO.getTimes().isEmpty()) {
                            out.println("Sem times cadastrados");
                            break;
                        }
                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        if (t != null) {
                            out.println(t.toString());
                        } else {
                            out.println("Time não encontrado");
                        }
                        break;
                    }
                    case "DELETE_TIME": {
                        if (timeDAO.getTimes().isEmpty()) {
                            out.println("Sem times cadastrados");
                            break;
                        }
                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        if (t != null) {
                            timeDAO.excluir(t);
                            out.println("Time removido com sucesso");
                        } else {
                            out.println("Time não encontrado");
                        }
                        break;
                    }
                    case "LIST_TIME": {
//                        if (timeDAO.getTimes().isEmpty()) {
//                            out.println(0);
//                            break;
//                        }
//                        out.println(timeDAO.getTimes().size());
//                        for (Time t : timeDAO.getTimes()) {
//                            out.println(t.toString());
//                        }
//                        break;
                        if (timeDAO.getTimes().isEmpty()) {
                            out.println(0);
                            break;
                        }
                        out.println(timeDAO.getTimes().size());
                        
                        String retorno = "";
                        
                        for (Time time : timeDAO.getTimes()) {
                            retorno += time.toString();
                            
                            for (Tecnico tec : tecnicoDAO.getTecnicos()) {
                                if (tec.getTime() != null) {
                                    String nomeTime = time.getNome();
                                    String timeTecnico = tec.getTime().getNome();
                                    
                                    System.out.println("?? " + nomeTime.equals(timeTecnico));
                                    
                                    if (nomeTime.equals(timeTecnico)) {
                                        System.out.println("É igual");
                                        retorno += " TECNICO: " + tec.toString();
                                        System.out.println(retorno);
                                    } else {
                                        System.out.println("Não é igual");
                                    }
                                }
                            }
                            
                            for (Jogador j : jogadorDAO.getJogadores()) {
                                if (j.getTime() != null) {
                                    String nomeTime = time.getNome();
                                    String timeJogador = j.getTime().getNome();
                                    
                                    System.out.println("?? " + nomeTime.equals(timeJogador));
                                    
                                    if (nomeTime.equalsIgnoreCase(timeJogador)) {
                                        System.out.println("É igual");
                                        retorno += " JOGADOR: " + j.toString();
                                        System.out.println(retorno);
                                    } else {
                                        System.out.println("Não é igual");
                                    }
                                }
                            }
                        }
                        
                        out.println(retorno);
                        break;
                    }
                    case "INSERT_JOGADOR_TIME": {
                        if (jogadorDAO.getJogadores().isEmpty()) {
                            out.println("Sem jogadores cadastrados");
                            break;
                        }

                        if (timeDAO.getTimes().isEmpty()) {
                            out.println("Sem time cadastrados");
                            break;
                        }

                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        
                        if (t != null) {
                            Jogador j = jogadorDAO.encontrarPorCpf(campos[2]);
                            if (j != null) {
//                                t.addPessoa(j);
                                j.setTime(t);
                                out.println("Jogador inserido no time com sucesso");
                            } else {
                                out.println("Jogador não encontrado");
                            }
                        } else {
                            out.println("Time não encontrado");
                        }
                        break;
                    }
                    case "DELETE_JOGADOR_TIME": {
                        if (jogadorDAO.getJogadores().isEmpty()) {
                            out.println("Sem jogadores cadastrados");
                            break;
                        }

                        if (timeDAO.getTimes().isEmpty()) {
                            out.println("Sem times cadastrados");
                            break;
                        }

                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        if (t != null) {
                            Jogador j = jogadorDAO.encontrarPorCpf(campos[2]);
                            if (j != null) {
//                                t.removePessoa(j);
                                j.setTime(null);
                                out.println("Jogador removido do time com sucesso");
                            } else {
                                out.println("Jogador não encontrado");
                            }
                        } else {
                            out.println("Time não encontrado");
                        }

                        break;
                    }
                    case "INSERT_TECNICO_TIME": {
                        if (tecnicoDAO.getTecnicos().isEmpty()) {
                            out.println("Sem tecnicos cadastrados");
                            break;
                        }

                        if (timeDAO.getTimes().isEmpty()) {
                            out.println("Sem time cadastrados");
                            break;
                        }


                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        if (t != null) {
                            Tecnico tec = tecnicoDAO.encontrarPorCpf(campos[2]);
                            if (tec != null) {
//                                t.addPessoa(tec);
                                tec.setTime(t);
                                out.println("Tecnico inserido no time com sucesso");
                            } else {
                                out.println("Tecnico não encontrado");
                            }
                        } else {
                            out.println("Time não encontrado");
                        }
                        break;
                    }
                    case "DELETE_TECNICO_TIME": {
                        if (tecnicoDAO.getTecnicos().isEmpty()) {
                            out.println("Sem tecnicos cadastrados");
                            break;
                        }

                        if (timeDAO.getTimes().isEmpty()) {
                            out.println("Sem times cadastrados");
                            break;
                        }

                        Time t = timeDAO.encontrarPorNome(campos[1]);
                        if (t != null) {
                            Tecnico tec = tecnicoDAO.encontrarPorCpf(campos[2]);
                            if (tec != null) {
//                                t.removePessoa(tec);
                                tec.setTime(null);
                                out.println("Tecnico removido do time com sucesso");
                            } else {
                                out.println("Tecnico não encontrado");
                            }
                        } else {
                            out.println("Time não encontrado");
                        }

                        break;
                    }
                    default:
                        out.println("Opção indisponível.");
                }

            }
        }
    }
}
