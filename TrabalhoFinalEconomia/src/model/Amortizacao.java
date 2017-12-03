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
public class Amortizacao {
    private double vp;
    private double vf;
    private double taxa;
    private double juros;
    private int parcelas;
    private int quero;
    private int tenho;
    private double amortizacao;
    private double saldoDevedor;
    private int carencia;
    private double valorParcelas;
    private double desconto;
    private String nomeProduto;

    public Amortizacao(double vp, double vf, double taxa, double juros, int parcelas, int quero, int tenho, double amortizacao, double saldoDevedor, int carencia, double valorParcelas, double desconto, String nomeProduto) {
        this.vp = vp;
        this.vf = vf;
        this.taxa = taxa;
        this.juros = juros;
        this.parcelas = parcelas;
        this.quero = quero;
        this.tenho = tenho;
        this.amortizacao = amortizacao;
        this.saldoDevedor = saldoDevedor;
        this.carencia = carencia;
        this.valorParcelas = valorParcelas;
        this.desconto = desconto;
        this.nomeProduto = nomeProduto;
    }

    public Amortizacao() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

 
    
    public double getVp() {
        return vp;
    }

    public void setVp(double vp) {
        this.vp = vp;
    }

    public double getVf() {
        return vf;
    }

    public void setVf(double vf) {
        this.vf = vf;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getQuero() {
        return quero;
    }

    public void setQuero(int quero) {
        this.quero = quero;
    }

    public int getTenho() {
        return tenho;
    }

    public void setTenho(int tenho) {
        this.tenho = tenho;
    }

    public double getAmortizacao() {
        return amortizacao;
    }

    public void setAmortizacao(double amortizacao) {
        this.amortizacao = amortizacao;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public int getCarencia() {
        return carencia;
    }

    public void setCarencia(int carencia) {
        this.carencia = carencia;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
   
    
    
}
