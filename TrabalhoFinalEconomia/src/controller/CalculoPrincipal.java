/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DecimalFormat;
import java.util.Scanner;
import model.Amortizacao;
import model.EnumPeriodoTaxa;
import model.EnumTipoAmortizacao;
import model.EnumTipoSerie;

/**
 *
 * @author Wagner
 */
public class CalculoPrincipal {
    
    private static Scanner s = new Scanner(System.in);
    private EnumTipoAmortizacao enumTipoAmortizacao;
    private EnumPeriodoTaxa periodoTaxa;
    private EnumTipoSerie tipoSerie;
    private Amortizacao a;
    private DecimalFormat fmt = new DecimalFormat("0.0000");
    private double juros;
    private double jurosTotal = 0;
    private double totalValorParcelas = 0;
    private double totalAmortizaca = 0;
    private double amortizacao;
    private String tabelaAmortizacao = "";
    
    public CalculoPrincipal(Amortizacao a) {
        this.a = a;
    }
    
    public CalculoPrincipal() {
    }
    
    public void selecionaTipoAmortizacao(int tipo) {
        switch (tipo) {
            case 1:
                enumTipoAmortizacao = EnumTipoAmortizacao.FRANCES;
                break;
            
            case 2:
                enumTipoAmortizacao = EnumTipoAmortizacao.PRICE;
                break;
            
            default:
                System.out.println("Nenhum tipo de amortização selecionado");
                break;
        }
        
    }
    
    public void selecionaTipoSerie(int tipo) {
        switch (tipo) {
            case 1:
                tipoSerie = EnumTipoSerie.ANTECIPADO;
                break;
            
            case 2:
                tipoSerie = EnumTipoSerie.POSTECIPADO;
                break;
            
            default:
                System.out.println("Nenhum tipo de taxa selecionado");
                break;
        }
    }
    
    public void selecionaPeriodoTaxa(int valor) {
        switch (valor) {
            case 1:
                periodoTaxa = EnumPeriodoTaxa.DIARIA;
                break;
            
            case 2:
                periodoTaxa = EnumPeriodoTaxa.MENSAL;
                break;
            
            case 3:
                periodoTaxa = EnumPeriodoTaxa.BIMESTRAL;
                break;
            case 4:
                periodoTaxa = EnumPeriodoTaxa.SEMESTRAL;
                break;
            
            case 5:
                periodoTaxa = EnumPeriodoTaxa.ANUAL;
                break;
            default:
                System.out.println("Nenhum período selecionado");
                break;
        }
        
    }
    
    public double calculoTaxa() {
        
        if (enumTipoAmortizacao.equals(EnumTipoAmortizacao.PRICE)) {
            return calcularNominal();
        } else {
            return calcularEquivalencia();
        }
    }
    
    public double verificaTipoDeSerie() {
        if (tipoSerie.equals(EnumTipoSerie.ANTECIPADO)) {
            
            return calcularParcelaAntecipado();
        } else {
            return calcularParcelaPostecipado();
        }
    }

    //parcela sem considerar juros da incidência
    public double calcularParcelaPostecipado() {
        a.setValorParcelas((a.getVp() * (a.getTaxa() / 100)) / (1 - Math.pow((1 + (a.getTaxa() / 100)), -a.getParcelas())));
        return a.getValorParcelas();
    }
    
    public double calcularParcelaAntecipado() {
        a.setValorParcelas((a.getVp() * (a.getTaxa() / 100)) / ((1 - Math.pow((1 + (a.getTaxa() / 100)), -a.getParcelas())) * (1 + (a.getTaxa() / 100))));
        return a.getValorParcelas();
    }
    
    public double calcularEquivalencia() {
        switch (periodoTaxa) {
            case DIARIA:
                a.setTaxa((Math.pow(((a.getTaxa() / 100) + 1), (double) 1 / 360) - 1) * 100);
                break;
            case MENSAL:
                System.out.println("Teste: " + a.getTaxa());
                a.setTaxa((Math.pow(((a.getTaxa() / 100) + 1), (double) 1 / 12) - 1) * 100);
                break;
            case BIMESTRAL:
                a.setTaxa((Math.pow(((a.getTaxa() / 100) + 1), (double) 6 / 12) - 1) * 100);
                break;
            case TRIMESTRAL:
                a.setTaxa((Math.pow(((a.getTaxa() / 100) + 1), (double) 4 / 12) - 1) * 100);
                break;
            case SEMESTRAL:
                a.setTaxa((Math.pow(((a.getTaxa() / 100) + 1), (double) 2 / 12) - 1) * 100);
                break;
        }
        return a.getTaxa();
    }
    
    public double calcularNominal() {
        switch (periodoTaxa) {
            case DIARIA:
                a.setTaxa(a.getTaxa() / 360);
                break;
            case MENSAL:
                a.setTaxa(a.getTaxa() / 12);
                break;
            case BIMESTRAL:
                a.setTaxa(a.getTaxa() / 6);
                break;
            case TRIMESTRAL:
                a.setTaxa((a.getTaxa()) / 4);
                break;
            case SEMESTRAL:
                a.setTaxa(a.getTaxa() / 2);
                break;
        }
        return a.getTaxa();
    }
    
    public double calculoValorPrincipal() {
        if ((a.getDesconto() != 0)) {
            a.setVp(a.getVp() - a.getVp() * (a.getDesconto() / 100));
            return a.getVp();
        } else {
            return a.getVp();
        }
    }
    
    public String calculoAmortizacao() {
        switch (tipoSerie) {
            case POSTECIPADO:
                return calcularPostecipado();
            case ANTECIPADO:
                return calcularAntecipado();
        }
        return null;
    }
    
    public String calcularPostecipado() {        
        a.setSaldoDevedor(a.getVp());
        tabelaAmortizacao = "PRODUTO A SER FINANCIADO: " + a.getNomeProduto().toUpperCase();
        tabelaAmortizacao = tabelaAmortizacao + "\n                                              TABELA " + enumTipoAmortizacao;
        tabelaAmortizacao = tabelaAmortizacao + "\nPÉRIODO" + "\tPARCELA   " + "\tJUROS" + "\tAMORTIZAÇÃO" + "\t    SALDO DEVEDOR";
        tabelaAmortizacao = tabelaAmortizacao + "\n    0" + "\t      -  " + "\t  -" + "\t     -" + "\t      " + a.getSaldoDevedor();        
        for (int i = 1; i <= a.getParcelas(); i++) {
            juros = (a.getTaxa() / 100) * a.getSaldoDevedor();
            amortizacao = a.getValorParcelas() - juros;
            a.setSaldoDevedor(a.getSaldoDevedor() - amortizacao);
            jurosTotal = jurosTotal + juros;
            totalAmortizaca = totalAmortizaca + amortizacao;
            totalValorParcelas = totalValorParcelas + a.getValorParcelas();
            tabelaAmortizacao = tabelaAmortizacao + "\n    " + i + "\t  " + fmt.format(a.getValorParcelas()) + "\t " + fmt.format(juros) + "\t " + fmt.format(amortizacao) + "\t   " + fmt.format(a.getSaldoDevedor());
        }
        tabelaAmortizacao = tabelaAmortizacao + "\n TOTAL\t" + fmt.format(totalValorParcelas) + "\t " + fmt.format(jurosTotal) + "\t " + fmt.format(totalAmortizaca);
        return tabelaAmortizacao;
    }
    
    public String calcularAntecipado() {
        a.setSaldoDevedor(a.getVp());
        tabelaAmortizacao = "PRODUTO A SER FINANCIADO: " + a.getNomeProduto().toUpperCase();
        tabelaAmortizacao = tabelaAmortizacao + "\n                                              TABELA " + enumTipoAmortizacao;
        tabelaAmortizacao = tabelaAmortizacao + "\nPÉRIODO" + "\tPARCELA   " + "\tJUROS" + "\tAMORTIZAÇÃO" + "\t    SALDO DEVEDOR";
        tabelaAmortizacao = tabelaAmortizacao + "\n     " + "\t         " + "\t   " + "\t      " + "\t      " + a.getSaldoDevedor();        
        a.setSaldoDevedor(a.getSaldoDevedor() - a.getValorParcelas());
        tabelaAmortizacao = tabelaAmortizacao + "\n    0" + "\t  " + fmt.format(a.getValorParcelas()) + "\t    0" + "\t0" + "\t   " + fmt.format(a.getSaldoDevedor());
        for (int i = 1; i <= a.getParcelas() - 1; i++) {
            juros = (a.getTaxa() / 100) * a.getSaldoDevedor();
            amortizacao = a.getValorParcelas() - juros;
            a.setSaldoDevedor(a.getSaldoDevedor() - amortizacao);
            jurosTotal = jurosTotal + juros;
            totalAmortizaca = totalAmortizaca + amortizacao;
            totalValorParcelas = totalValorParcelas + a.getValorParcelas();
            tabelaAmortizacao = tabelaAmortizacao + "\n    " + i + "\t  " + fmt.format(a.getValorParcelas()) + "\t " + fmt.format(juros) + "\t " + fmt.format(amortizacao) + "\t   " + fmt.format(a.getSaldoDevedor());
        }
        tabelaAmortizacao = tabelaAmortizacao + "\n TOTAL\t" + fmt.format(totalValorParcelas) + "\t " + fmt.format(jurosTotal) + "\t " + fmt.format(totalAmortizaca);
        return tabelaAmortizacao;
    }
}
