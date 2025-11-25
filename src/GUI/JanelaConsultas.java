package GUI;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaConsultas extends JDialog{
    private JPanel principal;
    private JButton maiorValorTecButton;
    private JButton compradorMaisVendasButton;
    private JButton fornecedorMaisTecButton;
    private JButton vendaMaiorValorButton;
    private JTextArea resultadoConsultasTA;
    private JLabel consultasLabel;
    private JButton voltarButton;
    private ACMETech acmeTech;
    private JanelaPrincipal janelaPrincipal;

    public JanelaConsultas(JanelaPrincipal janelaPrincipal, ACMETech acmeTech){
        super();
        setContentPane(principal);
        setModal(true);
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
