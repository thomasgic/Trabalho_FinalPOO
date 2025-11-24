package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelInicial {
    private JLabel instrucaoLabel;
    private JButton removeVendaButton;
    private JButton finalizarButton;
    private JButton consultaButton;
    private JButton cadastroButton;
    private JButton relatorioButton;
    private JButton alterarDadosButton;
    private JButton carregaDadosButton;
    private JButton salvaButton;
    private JPanel painelInicial;
    private JanelaPrincipal janelaPrincipal;

    public PainelInicial(JanelaPrincipal janelaPrincipal){
    this.janelaPrincipal = janelaPrincipal;
    tratamentoDeEventos();

    }


    public JPanel getPainelInicial(){
        return painelInicial;
    }

    public void tratamentoDeEventos(){
        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(1);
            }
        });

        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.dispose();
            }
        });
    }
}
