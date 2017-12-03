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
public enum EnumTipoAmortizacao{
    PRICE(1, "Price"), FRANCES (2, "Francês");
    private int valorAmort;
    private String tipoAmort;

    private EnumTipoAmortizacao(int valorAmort, String tipoAmort) {
        this.valorAmort = valorAmort;
        this.tipoAmort = tipoAmort;
    }

    public int getValorAmort() {
        return valorAmort;
    }

    @Override
    public String toString() {
        return valorAmort + " - "+ tipoAmort;
    }
    
    
      
}
