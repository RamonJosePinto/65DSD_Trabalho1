package trabalho1dsd.dao;

import trabalho1dsd.model.Jogador;

import java.util.List;

public interface JogadorDAO {
    Jogador encontrarPorCpf(String cpf);
    void salvar(Jogador j);
    void excluir(Jogador j);
    List<Jogador> getJogadores();
}
