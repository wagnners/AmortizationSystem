/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Amortizacao;
import model.EnumPeriodoTaxa;
import model.EnumTipoAmortizacao;
import model.EnumTipoSerie;

/**
 *
 * @author Wagner
 */
public class ControlTelaPrincipal {

    ControlTelaSobre telaSobre = null;

    Amortizacao a = new Amortizacao();
    CalculoPrincipal p;
    String stringTableTelaP;

    view.TelaPrincipal tela = null;

    public ControlTelaPrincipal() {
        telaSobre = new ControlTelaSobre();
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

        tela.jjmSobre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaSobre.chamaTela();
            }
        });

        tela.btnGerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    imprimirPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ControlTelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void limpar() {
        tela.edDesconto.setText(null);
        tela.edNomeProduto.setText(null);
        tela.edQtdParcelas.setText(null);
        tela.edTaxa.setText(null);
        tela.edValor.setText(null);
        tela.taTabela.setText("");
    }

    private boolean validouCampos() {
        boolean lbValidou = true;

        if (tela.edNomeProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar nome do produto!");
            lbValidou = false;
        }

        if (lbValidou && tela.edValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar valor!");
            lbValidou = false;
        }

        if (lbValidou && tela.edDesconto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar desconto!");
            lbValidou = false;
        }

        if (lbValidou && tela.edTaxa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar taxa!");
            lbValidou = false;
        }

        if (lbValidou && tela.edQtdParcelas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar quantidade de parcelas!");
            lbValidou = false;
        }

        return lbValidou;
    }

    private boolean setDados() {
        boolean lbValidouCampos = validouCampos();
        try {
            if (lbValidouCampos) {
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
                tela.taTabela.setText(p.calculoAmortizacao());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tela, "Você precisa informar todos os campos");
        }

        return lbValidouCampos;
    }

    public void calcular() {
        setDados();
    }

    public void imprimirPDF() throws Exception {

        if (setDados()) {

            Document doc = null;
            OutputStream os = null;
            try {
                doc = new Document(PageSize.A4, 5, 5, 50, 50);
                os = new FileOutputStream("amortizacao.pdf");
                PdfWriter.getInstance(doc, os);
                doc.open();
                p.calculoAmortizacaoPDF(doc);

            } finally {
                if (doc != null) {
                    doc.close();
                    if (os != null) {
                        os.close();
                    }

                    Desktop.getDesktop().open(new File("amortizacao.pdf"));
                }
            }
        }
    }
}
