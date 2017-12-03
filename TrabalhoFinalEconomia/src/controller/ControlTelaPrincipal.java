/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Amortizacao;
import model.EnumPeriodoTaxa;
import model.EnumTipoAmortizacao;
import model.EnumTipoSerie;
import static model.EnumTipoSerie.ANTECIPADO;

/**
 *
 * @author Wagner
 */
public class ControlTelaPrincipal {

    Amortizacao a = new Amortizacao();
    CalculoPrincipal p;

    view.TelaPrincipal tela = null;

    public ControlTelaPrincipal() {

        tela = new view.TelaPrincipal();
        ligaEventos();
    }

    public void chamaTela() {
        tela.setVisible(true);

    }

    private void fecharTela() {
        System.exit(0);

    }

    private void ligaEventos() {
        tela.cbTaxa.setModel(new DefaultComboBoxModel(EnumPeriodoTaxa.values()));
        tela.cbAmortizacao.setModel(new DefaultComboBoxModel(EnumTipoAmortizacao.values()));
        tela.cbSerie.setModel(new DefaultComboBoxModel(EnumTipoSerie.values()));
        tela.btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTela();
            }
        });

        tela.btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });

        tela.edValor.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == '.')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        tela.edTaxa.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == '.')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        tela.edDesconto.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == '.')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        tela.edQtdParcelas.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
           tela.btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

    }
    
    private void limpar(){
        tela.edDesconto.setText(null);
        tela.edNomeProduto.setText(null);
        tela.edQtdParcelas.setText(null);
        tela.edTaxa.setText(null);
        tela.edValor.setText(null);
    }

    private void calcular() {
        try{
        a.setNomeProduto(tela.edNomeProduto.getText());
        a.setVp(Double.parseDouble(tela.edValor.getText()));
        a.setDesconto(Double.parseDouble(tela.edDesconto.getText()));
        a.setTaxa(Double.parseDouble(tela.edTaxa.getText()));
        a.setParcelas(Integer.parseInt(tela.edQtdParcelas.getText()));       
        EnumPeriodoTaxa enumPTaxa = (EnumPeriodoTaxa) tela.cbTaxa.getSelectedItem();
        EnumTipoAmortizacao enumTAmortizacao = (EnumTipoAmortizacao) tela.cbAmortizacao.getSelectedItem();
        EnumTipoSerie enumTSerie = (EnumTipoSerie) tela.cbSerie.getSelectedItem();
        p = new CalculoPrincipal(a);
        p.selecionaPeriodoTaxa(enumPTaxa.getValor());
        p.selecionaTipoAmortizacao(enumTAmortizacao.getValorAmort());
        p.selecionaTipoSerie(enumTSerie.getValorSerie());
        p.calculoTaxa();
        p.calculoValorPrincipal();
        p.verificaTipoDeSerie();
        p.calculoAmortizacao();
        tela.taTabela.setText(p.calculoAmortizacao());
         }catch(Exception e){
            JOptionPane.showMessageDialog(tela, "Você precisa informar todos os campos");
        }
    }

}
