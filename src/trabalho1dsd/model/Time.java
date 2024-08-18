/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-defaulthis.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ramon
 */
public class Time {
    private String nome;
    private String endereco;
    private String pais;
    private int anoFundacao;
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public Time(String nome, String endereco, String pais, int anoFundacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.pais = pais;
        this.anoFundacao = anoFundacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
        pessoa.setTime(this);
    }

    public void removePessoa(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
    }

    public Pessoa findPessoaByName(String name) {
        for (Pessoa p : pessoas) {
            if (p.getNome().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getNome() + ";" + this.getEndereco() + ";" + this.getPais() + ";" + this.getAnoFundacao() + ";[" + this.getPessoas().toString() + "]";
    }
}
