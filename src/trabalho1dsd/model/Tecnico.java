/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1dsd.model;

/**
 *
 * @author Ramon
 */
public class Tecnico extends Pessoa {
    private String vigenciaInicio;
    private String vigenciaTermino;

    public Tecnico(String cpf, String nome, String endereco, String vigenciaInicio, String vigenciaTermino, Time time) {
        super(cpf, nome, endereco);
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaTermino = vigenciaTermino;
    }

    public String getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(String vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public String getVigenciaTermino() {
        return vigenciaTermino;
    }

    public void setVigenciaTermino(String vigenciaTermino) {
        this.vigenciaTermino = vigenciaTermino;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + vigenciaInicio + ";" + vigenciaTermino;
    }
}
