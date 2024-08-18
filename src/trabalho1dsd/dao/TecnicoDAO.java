package trabalho1dsd.dao;

import trabalho1dsd.model.Tecnico;

import java.util.List;

public interface TecnicoDAO {
    Tecnico encontrarPorCpf(String cpf);
    void salvar(Tecnico t);
    void excluir(Tecnico t);
    List<Tecnico> getTecnicos();
}
