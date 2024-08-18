/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd.model;

/**
 * @author Ramon
 */
public class Pessoa {

    private String cpf;
    private String nome;
    private String endereco;
    private Time time;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Pessoa(String cpf, String nome, String endereco, Time time) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.time = time;
    }

    @Override
    public String toString() {
        return this.getCpf() + ";" + this.getNome() + ";" + this.getEndereco();
    }
}
