package trabalho1dsd.dao;

import trabalho1dsd.model.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeDAOImpl implements TimeDAO {
    private static List<Time> times = new ArrayList<Time>();

    @Override
    public Time encontrarPorNome(String nome) {
        for (Time t : times) {
            if (t.getNome().equalsIgnoreCase(nome)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void salvar(Time t) {
        times.add(t);
    }

    @Override
    public void excluir(Time t) {
        times.remove(t);
    }

    @Override
    public List<Time> getTimes() {
        return times;
    }
}
