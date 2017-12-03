/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TextElementArray;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    String stringTableTelaP;

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
        
        tela.btnGerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirPDF();
            }
        });

    }

    private void limpar() {
        tela.edDesconto.setText(null);
        tela.edNomeProduto.setText(null);
        tela.edQtdParcelas.setText(null);
        tela.edTaxa.setText(null);
        tela.edValor.setText(null);
    }

    private void setDados() {
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar todos os campos");
        }
    }

    public void calcular() {
        setDados();
        tela.taTabela.setText(p.calculoAmortizacao());
    }

    public void imprimirPDF() {
   
            setDados();
            Document document = new Document();
    try {
           PdfWriter.getInstance(document, new FileOutputStream("amortizacao.pdf"));
           
           document.open();
           document.add(new Phrase(p.calculoAmortizacao()));
           

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(tela, "Erro ao gerar o arquivo " + ex);
        }
        finally{
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("amortizacao.pdf"));
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(tela, "Não foi possível abrir o arquivo PDF. Erro: " + ex);
        }
    }

}
