package GUI;

import aplicacao.ACMETech;

import javax.swing.*;

public class PainelAlteraCompradorAux extends JDialog {
    private JPanel principal;
    private JPanel instrucoes;
    private JLabel instrucaoLabel;
    private JPanel dados;
    private JPanel botoes;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton confirmaButton;
    private JButton limpaButton;
    private JTextArea MensagensTA;
    private JPanel mensagens;
    private JLabel mensagensLabel;
    private ACMETech acmeTech;

    public PainelAlteraCompradorAux (ACMETech acmeTech) {
        super();
        this.acmeTech = acmeTech;
        setContentPane(principal);
        setModal(true);
    }


}
