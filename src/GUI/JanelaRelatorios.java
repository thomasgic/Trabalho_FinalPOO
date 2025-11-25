package GUI;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaRelatorios {
    private JButton tecnologiasButton;
    private JButton fornecedoresButton;
    private JButton compradoresButton;
    private JButton vendasButton;
    private JTextArea resultadoRelatorioTA;
    private JLabel relatoriosLabel;
    private JPanel principal;
    private JPanel painelBotoes;
    private JPanel painelMostraRelatorios;
    private JButton voltarButton;
    private JanelaPrincipal janelaPrincipal;
    private ACMETech acmeTech;

    public JanelaRelatorios(JanelaPrincipal janelaPrincipal, ACMETech acmeTech){
        super();
        this.acmeTech = acmeTech;
        this.janelaPrincipal = janelaPrincipal;
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(0);
            }
        });
    }

    public JPanel getPrincipal(){
        return principal;
    }
}
