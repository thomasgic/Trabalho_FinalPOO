package GUI;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaCadastros {
    private JLabel mensagemLabel;
    private JButton CADASTRARVENDAButton;
    private JButton CADASTRARCOMPRADORButton;
    private JButton CADASTRARTECNOLOGIAButton;
    private JButton CADASTRARFORNECEDORButton;
    private JButton VOLTARButton;
    private JPanel painelCadastros;
    private JanelaPrincipal janelaPrincipal;
    private ACMETech acmeTech;


    public JanelaCadastros(JanelaPrincipal janelaPrincipal, ACMETech acmeTech){
        this.acmeTech = acmeTech;
        this.janelaPrincipal = janelaPrincipal;
        tratamentoDeEventos();

    }

    public void tratamentoDeEventos(){
        CADASTRARFORNECEDORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                PainelFornecedor painelFornecedor = new PainelFornecedor(acmeTech);
                abrirDialog(painelFornecedor);
            }
        });

        CADASTRARTECNOLOGIAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                PainelTecnologia painelTecnologia = new PainelTecnologia(acmeTech);
                abrirDialog(painelTecnologia);
            }
        });

        CADASTRARCOMPRADORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                PainelComprador painelComprador = new PainelComprador(acmeTech);
                abrirDialog(painelComprador);
            }
        });

        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
            janelaPrincipal.mudaTela(0);
            }
        });

    }

    private void abrirDialog(JDialog dialog) {
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public JPanel getPainel(){
        return painelCadastros;
    }


}

