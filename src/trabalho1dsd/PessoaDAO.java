/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pichau
 */
public class PessoaDAO {

    private static List<Pessoa> pessoas = new ArrayList<>();

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void salvarPessoa(Pessoa p) {
        pessoas.add(p);
    }
}
