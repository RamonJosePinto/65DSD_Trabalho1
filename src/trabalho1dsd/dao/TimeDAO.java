package trabalho1dsd.dao;

import trabalho1dsd.model.Time;

import java.util.List;

public interface TimeDAO {
    Time encontrarPorNome(String nome);
    void salvar(Time t);
    void excluir(Time t);
    List<Time> getTimes();
}
