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
public enum EnumPeriodoTaxa {
    
    DIARIA(1, "Diária"),MENSAL(2,"Mensal"),BIMESTRAL(3, "Bimestral"),TRIMESTRAL(4,"Trimestral"),SEMESTRAL(5,"Semestral"),ANUAL(6,"Anual"); 

   private String periodo;
    private int valor;
    private EnumPeriodoTaxa(int valor, String periodo) {
        this.valor = valor;
        this.periodo = periodo;
    }
    
   

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor + " - " + periodo;
    }
    
    
    
    
    
}
