package GUI;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaAlteraComprador {
    private JTextField textField1;
    private JButton confirmarButton;
    private JButton CancelarButton;
    private JPanel principal;
    private JPanel campoCod;
    private JPanel instrucao;
    private JPanel botoes;
    private JanelaPrincipal janelaPrincipal;
    private ACMETech acmeTech;
    public JanelaAlteraComprador(JanelaPrincipal janelaPrincipal, ACMETech acmeTech){
        super();
        this.janelaPrincipal = janelaPrincipal;
        this.acmeTech = acmeTech;
        tratamentoEventos();
    }

    private void tratamentoEventos(){
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {

                //fazer verificação se o codigo existe, se existe abre o dialog, senão mostra mensagem de erro.
                PainelAlteraCompradorAux painelComp = new PainelAlteraCompradorAux(acmeTech);
                painelComp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                painelComp.pack();
                painelComp.setLocationRelativeTo(null);
                painelComp.setVisible(true);
            }
        });

        CancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(0);
            }
        });
    }

    public JPanel getPrincipal () {
        return principal;
    }
}
