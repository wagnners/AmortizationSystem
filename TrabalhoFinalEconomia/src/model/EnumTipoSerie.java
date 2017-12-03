/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Wagner
 */
public enum EnumTipoSerie {
    ANTECIPADO(1,"Antecipado"),POSTECIPADO(2, "Postecipado");
    private int valorSerie;
    private String tipoSerie;

    private EnumTipoSerie(int valorSerie, String tipoSerie) {
        this.valorSerie = valorSerie;
        this.tipoSerie = tipoSerie;
    }

    public int getValorSerie() {
        return valorSerie;
    }

    public void setValorSerie(int valorSerie) {
        this.valorSerie = valorSerie;
    }

    @Override
    public String toString() {
        return valorSerie +" - " + tipoSerie;
    }
    
    
}
