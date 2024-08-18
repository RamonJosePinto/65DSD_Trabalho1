package trabalho1dsd.dao;

import trabalho1dsd.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAOImpl implements JogadorDAO{
    private static List<Jogador> jogadores = new ArrayList<Jogador>();

    @Override
    public Jogador encontrarPorCpf(String cpf) {
        for (Jogador j : jogadores) {
            if (j.getCpf().equalsIgnoreCase(cpf)) {
                return j;
            }
        }
        return null;
    }

    @Override
    public void salvar(Jogador j) {
        jogadores.add(j);
    }

    @Override
    public void excluir(Jogador j) {
        jogadores.remove(j);
    }

    @Override
    public List<Jogador> getJogadores() {
        return jogadores;
    }


}
