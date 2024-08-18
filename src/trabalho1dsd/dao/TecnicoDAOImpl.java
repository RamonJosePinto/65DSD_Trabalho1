package trabalho1dsd.dao;

import trabalho1dsd.model.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class TecnicoDAOImpl implements TecnicoDAO {
    private static List<Tecnico> tecnicos = new ArrayList<>();
    @Override
    public Tecnico encontrarPorCpf(String cpf) {
        for (Tecnico t : tecnicos) {
            if (t.getCpf().equalsIgnoreCase(cpf)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void salvar(Tecnico t) {
        tecnicos.add(t);
    }

    @Override
    public void excluir(Tecnico t) {
        tecnicos.remove(t);
    }

    @Override
    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }
}
