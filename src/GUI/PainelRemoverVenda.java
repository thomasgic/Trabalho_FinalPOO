package GUI;

import aplicacao.ACMETech;

import javax.swing.*;

public class PainelRemoverVenda extends JDialog {
    private JPanel principal;
    private JPanel instrucao;
    private JTextField digitaNumeroTF;
    private JLabel instrucaoLabel;
    private JTextArea textArea1;
    private JPanel mensagens;
    private JButton confirmarButton;
    ACMETech acmeTech;


    public PainelRemoverVenda (ACMETech acmeTech) {
        setContentPane(principal);
        setModal(true);
        this.acmeTech = acmeTech;
    }
}
