/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Matrix
 */
public class ControlTelaSobre {
        view.TelaSobre tela = null;

    public ControlTelaSobre() {

        tela = new view.TelaSobre(null, true);
        ligaEventos();
    }

    public void chamaTela() {
        tela.setVisible(true);

    }

    private void fecharTela() {
        tela.setVisible(false);

    }

    private void ligaEventos() {
        
        tela.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTela();
            }
        });
       
    }
    
    
}
