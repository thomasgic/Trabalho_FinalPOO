package GUI;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCadastros {
    private JLabel mensagemLabel;
    private JButton CADASTRARVENDAButton;
    private JButton CADASTRARCOMPRADORButton;
    private JButton CADASTRARTECNOLOGIAButton;
    private JButton CADASTRARFORNECEDORButton;
    private JPanel painelCadastros;
    private ACMETech acmeTech;
    private JanelaPrincipal janelaPrincipal;

    public PainelCadastros(JanelaPrincipal janelaPrincipal, ACMETech acmeTech){
        this.acmeTech = acmeTech;
        tratamentoDeEventos();
    }

    public void tratamentoDeEventos(){
        CADASTRARFORNECEDORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JDialog dialog = new JDialog(janelaPrincipal, "Cadastro de Fornecedor", true);
                PainelFornecedor painel = new PainelFornecedor();
                dialog.setContentPane(painel.getPainel());
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setSize(800, 300);
                dialog.setVisible(true);
            }
        });

    }

    public JPanel getPainel(){
        return painelCadastros;
    }


}

