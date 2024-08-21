/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd.model;

/**
 *
 * @author Ramon
 */
public class Jogador extends Pessoa{
    private String posicao;
    private String nacionalidade;
    private int numero;

    public Jogador(String cpf, String nome, String endereco, String posicao, String nacionalidade, int numero, Time time) {
        super(cpf, nome, endereco);
        this.posicao = posicao;
        this.nacionalidade = nacionalidade;
        this.numero = numero;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + posicao + ";" + nacionalidade + ";" + numero;
    }
}
