/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.CalculoPrincipal;
import java.util.Scanner;

/**
 *
 * @author Wagner
 *
 */
public class Test {

    private static Scanner s = new Scanner(System.in);

    public static void mainejdfej(String[] args) {
        
       
//          System.out.println("Informe o tipo: [1] PRICE [2] FRANCES");
//          int tipoAmortizacao= s.nextInt();
//          p.selecionaTipoAmortizacao(tipoAmortizacao);
//          System.out.println("Informe o período da taxa: [1] diaria [2]mensal [3]bimestral [4]semestral [5]anual");       
//          int periodoTaxa= s.nextInt();
//          p.selecionaPeriodoTaxa(periodoTaxa);
//          System.out.println("Informe o tipo de série: [1] Antecipado [2]Postecipado");
//          int tipoSerie = s.nextInt();
//          p.selecionaTipoSerie(tipoSerie);
//          System.out.println("Informe o valor principal");
//          double vp = s.nextDouble();
//          System.out.println("Informe o desconto [zero caso não ter]");
//          double desconto = s.nextDouble();
//          a.setVp(p.calculoValorPrincipal(vp, desconto));
//          System.out.println("Informe A TAXA");
//          double taxa = s.nextDouble(); 
//          a.setTaxa(taxa);
//          System.out.println("Informe a quantidade de parcelas");
//          int quantidadeParc = s.nextInt();
//          a.setParcelas(quantidadeParc);
//          System.out.println(a.getParcelas());
//          System.out.println("Informe o que quer");
//          int quero = s.nextInt();
//          System.out.println("Infome o que tenho");
//          int tenho = s.nextInt();
//          a.setQuero(quero);
//          a.setTenho(tenho);

 Amortizacao a = new Amortizacao();
 CalculoPrincipal p;
a.setVp(3000);
a.setDesconto(0);
a.setTaxa(16.56);
a.setParcelas(12);
p = new CalculoPrincipal(a);
        p.selecionaTipoAmortizacao(2);
        p.selecionaPeriodoTaxa(2);
        p.calculoTaxa();
        p.calculoValorPrincipal();
        p.selecionaTipoSerie(2);
        p.verificaTipoDeSerie();     
       p.calculoAmortizacao();

    }
}
