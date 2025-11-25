package GUI;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelVenda extends JDialog {
    private JPanel principal;
    private JPanel painelDados;
    private JTextField numeroVendaTF;
    private JTextField dataVendaTF;
    private JComboBox CompradoresCB;
    private JComboBox tecnologiaCB;
    private JPanel painelInfos;
    private JButton confirmarButton;
    private JButton mostrarButton;
    private JButton limparButton;
    private JButton cancelarButton;
    private JTextArea mensagensTA;
    private JPanel painelMensagens;
    private JPanel painelBotoes;
    private JLabel compradoresLabel;
    private JLabel tecnologiaLabel;
    private JLabel numeroVendaLabel;
    private JLabel dataVendaLabel;
    private JLabel mensagensLabel;
    private ACMETech acmeTech;

    public PainelVenda (ACMETech acmeTech) {
        super();
        this.acmeTech = acmeTech;
        setContentPane(principal);
        setModal(true);

    }

    private void tratamentoEventos(){
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {

            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {

            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                dataVendaTF.setText("");
                numeroVendaTF.setText("");
                mensagensTA.setText("");
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose();
            }
        });

    }
}
